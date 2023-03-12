package com.redis.demo.common;

/**
 * @author lequal
 * @date 2023/03/12 19:42
 * @Description 常量池,使用interface，其天然自带static final属性
 */
public interface Constant {

    /** 假如是一个接受手机验证码的业务， 键: code:13088888888 */
    String CODE = "code:";

    String STUDENT = "student:";
}
