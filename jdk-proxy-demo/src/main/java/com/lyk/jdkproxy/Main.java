package com.lyk.jdkproxy;

import com.lyk.jdkproxy.proxy.UserServiceProxyHandler;
import com.lyk.jdkproxy.service.IUserService;
import com.lyk.jdkproxy.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author lyk
 * @version 1.0
 * @date 2024/4/24 17:11
 * @description
 */
public class Main {


    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxyHandler userServiceProxyHandler = new UserServiceProxyHandler(userService);
        // 创建代理对象
        IUserService userServiceProxy =
                (IUserService)Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class[]{IUserService.class}, userServiceProxyHandler);

        // 代理对象调用
        userServiceProxy.sayHello();
    }
}
