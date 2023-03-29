package jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdbc.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lequal
 * @since 2022-11-22
 */
public interface CategoryMapper extends BaseMapper<Category> {

    void batchSaveCategory(@Param("list") List<Category> list);


    void clearTable();
}
