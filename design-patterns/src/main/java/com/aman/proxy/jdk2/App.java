package com.aman.proxy.jdk2;

import java.lang.reflect.Proxy;

/**
 * 调用类
 */
public class App {
    public static void main(String[] args) {
        //真实对象
        Subject realSubject = new RealSubject();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象
        Subject proxyClass = (Subject) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Subject.class},
                myInvocationHandler
        );

        int i = proxyClass.sellBooks();
        System.out.println("=======================" + i);
        String speak = proxyClass.speak();
        System.out.println("=======================" + speak);
        String eat = proxyClass.eat();
        System.out.println("=======================" + eat);

    }
}
