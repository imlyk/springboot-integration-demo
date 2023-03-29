package jdbc.service;

/**
 * @ClassName: AbstractJunitTest
 * @Author: lequal
 * @Date: 2023/03/29
 * @Description:
 */
public abstract class AbstractJunitTest {

    /**
     * 插入Category的SQL语句
     */
    protected final String SAVE_CATEGORY_SQL =
            "INSERT INTO category(category_id, parent_id,depth,category_name, icon, level, is_leaf, category_type, remark, create_time, update_time) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * 清空表
     */
    protected final String CLEAR_TABLE = "TRUNCATE `category`";

    /**
     * 默认批量大小
     */
    protected final Integer DEFAULT_LIST_SIZE = 1000;
}
