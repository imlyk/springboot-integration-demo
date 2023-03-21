package com.jedis.po;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Author: lequal
 * @Date: 2023/03/21
 * @Description: 用户对象
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;
}
