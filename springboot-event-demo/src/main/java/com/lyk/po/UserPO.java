package com.lyk.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lyk
 * @version 1.0
 * @date 2023/11/11 16:09
 * @description 用户实体对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPO implements Serializable {

    private static final long serialVersionUID = 216464961811756922L;

    private Long id;

    private String userName;

    private String password;
}
