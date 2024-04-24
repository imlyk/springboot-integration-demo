package com.lyk.jdkproxy.proxy;

import com.lyk.jdkproxy.service.IUserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lyk
 * @version 1.0
 * @date 2024/4/24 17:08
 * @description
 */
public class UserServiceProxyHandler implements InvocationHandler {

    // 需要代理的真实接口
    private IUserService userService;

    public UserServiceProxyHandler(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        System.out.println("前置处理一些事情");

        // 调用目标类中的方法
        result = method.invoke(userService, args);

        System.out.println("目标对象运行结果: " + result);

        System.out.println("后置处理一些事情");

        return result;
    }
}
