package com.aman.proxy.jdk2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 定义一个处理器
 *
 * @author gnehcgnaw
 * @date 2018/11/5 19:26
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 因为需要处理真实角色，所以要把真实角色传进来
     */
    Subject realSubject;

    public MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }

    /**
     * @param proxy  代理类
     * @param method 正在调用的方法
     * @param args   方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("【调用代理类】");
        if (method.getName().equals("sellBooks")) {
            System.out.println("---> 调用的是【卖书】的方法begin");
            int invoke = (int) method.invoke(realSubject, args);
            System.out.println("---> 调用的是【卖书】的方法end");
            return invoke;
        } else if (method.getName().equals("speak")) {
            System.out.println("---> 调用的是【说话】的方法begin");
            String invoke = (String) method.invoke(realSubject, args);
            System.out.println("---> 调用的是【说话】的方法end");
            return invoke;
        } else {
            System.out.println("**** 没有特殊 走公共 ****");
        }
        return method.invoke(realSubject, args);
    }

}
