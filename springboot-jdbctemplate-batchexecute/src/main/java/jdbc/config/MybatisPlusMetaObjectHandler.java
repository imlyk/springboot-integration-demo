package jdbc.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName: MybatisPlusMetaObjectHandler
 * @Author: lequal
 * @Date: 2022/11/22
 * @Description: MP配置自动插入字段配置
 */
@Slf4j
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 起始版本 3.3.3(推荐)
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createTime", () -> now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", () -> now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 必须用这个，因为源码中其它的几个方法是判断需要更新的对象原来的属性没有值才会进行填充
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
