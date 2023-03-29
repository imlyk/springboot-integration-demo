package jdbc.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import jdbc.entity.Category;
import jdbc.mapper.CategoryMapper;
import jdbc.util.IDKit;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: JdbcTemplateTest
 * @Author: lequal
 * @Date: 2023/03/29
 * @Description: 测试批量性能
 */
@SpringBootTest
public class JdbcTemplateTest extends AbstractJunitTest {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private CategoryService categoryService;
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 每次:1000 条, MP自带批量插入：10000 条数据，花费：2954 毫秒
     */


    /**
     * 批量插入的数据量大小
     */
    private final Integer BATCH_SIZE = 10000;

    @Test()
    public void saveBatchByMP() {
        // 1、获取数据
        List<List<Category>> list = generateEntity(BATCH_SIZE);
        // 清空表
        categoryService.clearTable();
        // 2、开始批量
        // 2.1、开始几时
        TimeInterval timer = DateUtil.timer();
        // 2.2、执行插入
        for (int i = 0; i < list.size(); i++) {
            categoryService.saveBatch(list.get(i));
        }
        System.out.println("每次:" + DEFAULT_LIST_SIZE + " 条, MP自带批量插入：" + BATCH_SIZE + " 条数据，花费：" + timer.interval() + " 毫秒");
    }

    /**
     * 插入性能最弱鸡
     * @throws SQLException
     */
    @Test
    public void saveBatchByJdbcTemplate() throws SQLException {
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        // 关闭自动提交事务
        connection.setAutoCommit(false);

        // 1、获取数据
        List<List<Category>> list = generateEntity(BATCH_SIZE);

        // 清空数据表
        jdbcTemplate.execute(CLEAR_TABLE);

        try {
            TimeInterval timer = DateUtil.timer();
            // 2、执行插入
            for (List<Category> categoryList : list) {
                if (!CollectionUtils.isEmpty(categoryList)) {
                    jdbcTemplate.batchUpdate(SAVE_CATEGORY_SQL, new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setLong(1, categoryList.get(i).getCategoryId());
                            ps.setLong(2, categoryList.get(i).getParentId());
                            ps.setString(3, categoryList.get(i).getDepth());
                            ps.setString(4, categoryList.get(i).getCategoryName());
                            ps.setString(5, categoryList.get(i).getIcon());
                            ps.setInt(6, categoryList.get(i).getLevel());
                            ps.setBoolean(7, categoryList.get(i).getLeaf());
                            ps.setInt(8, categoryList.get(i).getCategoryType());
                            ps.setString(9, categoryList.get(i).getRemark());
                            ps.setDate(10, Date.valueOf(categoryList.get(i).getCreateTime().toLocalDate()));
                            ps.setDate(11, Date.valueOf(categoryList.get(i).getUpdateTime().toLocalDate()));
                        }

                        @Override
                        public int getBatchSize() {
                            return categoryList.size();
                        }
                    });
                    connection.commit();
                }
            }

            System.out.println("每次:" + DEFAULT_LIST_SIZE + " 条, jdbcTemplate批量插入：" + BATCH_SIZE + " 条数据，花费：" + timer.interval() + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 事务回滚
                if (null != connection) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (null != connection) {
                    // 关闭连接
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author: lequal
     * @Description 插入性能最高
     * @Date 2023/03/29 17:53
     */
    @Test
    void saveBatchByMybatis() {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

        List<List<Category>> list = generateEntity(BATCH_SIZE);

        try {
            TimeInterval timer = DateUtil.timer();
            for (List<Category> categoryList : list) {
                if (!CollectionUtils.isEmpty(categoryList)) {
                    // 调用批量插入
                    mapper.batchSaveCategory(categoryList);

                    // 每次1000条就提交事务
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
            System.out.println("每次:" + DEFAULT_LIST_SIZE + " 条, foreach拼接参数批量插入：" + BATCH_SIZE + " 条数据，花费：" + timer.interval() + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }


    /**
     * @param nums 需要生成的数量
     * @return java.util.List<jdbc.entity.Category>
     * @Author: lequal
     * @Description 批量生成实体对象
     * @Date 2023/03/29 12:58
     */
    private List<List<Category>> generateEntity(int nums) {
        LocalDateTime now = LocalDateTime.now();

        Category category;
        TimeInterval timer = DateUtil.timer();
        System.out.println("开始生成数据...");
        // 小集合，每次1000个，或者用集合工具把大集合再分片
        List<Category> categoryList = new ArrayList<>();
        for (int i = 1; i <= nums; i++) {
            long id = IDKit.nextId();
            category = new Category(id, 0L, "0/" + id, "分类名称:" + i, "", 1, true, 0, "备注信息", now, now);
            categoryList.add(category);
        }
        List<List<Category>> split = CollectionUtil.split(categoryList, DEFAULT_LIST_SIZE);
        System.out.println("生成数据完成，花费：" + timer.interval() + " 毫秒");
        return split;
    }

    /**
     * @param nums 需要生成的数量
     * @return java.util.List<jdbc.entity.Category>
     * @Author: lequal
     * @Description 批量生成实体对象，集合手动分片
     * @Date 2023/03/29 12:58
     */
    @Deprecated
    private List<List<Category>> generateEntity1(int nums) {
        LocalDateTime now = LocalDateTime.now();
        int listSize = nums % DEFAULT_LIST_SIZE == 0 ? nums / DEFAULT_LIST_SIZE : nums / DEFAULT_LIST_SIZE + 1;
        // 大集合
        List<List<Category>> list = new ArrayList<>(listSize);

        Category category;
        TimeInterval timer = DateUtil.timer();
        System.out.println("开始生成数据...");
        // 小集合，每次1000个，或者用集合工具把大集合再分片
        List<Category> categoryList = new ArrayList<>(DEFAULT_LIST_SIZE);
        for (int i = 1; i <= nums; i++) {

            long id = IDKit.nextId();
            category = new Category(id, 0L, "0/" + id, "分类名称:" + i, "", 1, true, 0, "备注信息", now, now);
            categoryList.add(category);

            if (categoryList.size() % DEFAULT_LIST_SIZE == 0) {
                list.add(categoryList);
                categoryList = new ArrayList<>(DEFAULT_LIST_SIZE);
            }
        }

        if (nums % DEFAULT_LIST_SIZE != 0) {
            list.add(categoryList);
        }
        System.out.println("生成数据完成，花费：" + timer.interval() + " 毫秒");
        return list;
    }

    /*    @Test
    void testProduce() {
        List<List<Category>> lists = generateEntity1(10);
        System.out.println(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }*/
}
