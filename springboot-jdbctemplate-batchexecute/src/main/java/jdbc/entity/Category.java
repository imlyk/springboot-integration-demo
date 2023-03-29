package jdbc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lequal
 * @since 2022-11-22
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用分类表，分类id
     */
    @TableId(value = "category_id")
    private Long categoryId;

    /**
     * 分类父分类id，自己没有上级时，id是0
     */
    private Long parentId;

    /**
     * 分类id路径
     */
    private String depth;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类图标(图标存储路径)
     */
    private String icon;

    /**
     * 分类层级(1级、2级、3级)
     */
    private Integer level;

    /**
     * 是否叶子节点,0-否,1-是
     */
    @TableField("is_leaf")
    private Boolean leaf;

    /**
     * 分类类型:	0通用	1订制
     */
    private Integer categoryType;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
