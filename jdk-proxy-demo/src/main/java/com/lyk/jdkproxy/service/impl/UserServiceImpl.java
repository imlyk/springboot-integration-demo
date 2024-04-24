package com.lyk.jdkproxy.service.impl;

import com.lyk.jdkproxy.service.IUserService;

/**
 * @author lyk
 * @version 1.0
 * @date 2024/4/24 17:07
 * @description
 */
public class UserServiceImpl implements IUserService {
    @Override
    public String sayHello() {
        return "Hello UserService~";
    }
}
