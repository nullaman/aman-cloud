package com.aman.proxy.jdk1;


/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
        /**
         * 静态代理
         */
//        System.out.println("==========静态代理===========");
//        //目标对象
//        UserDao target = new UserDao();
//        //代理对象,把目标对象传给代理对象,建立代理关系
//        UserDaoProxy proxy = new UserDaoProxy(target);
//        proxy.save();//执行的是代理的方法
//        System.out.println("============================");

        /**
         * 动态代理
         */
        System.out.println("==============动态代理Proxy.newProxyInstance==============");
        // 目标对象
        IUserDao target2 = new UserDao();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println(target2.getClass());

        // 给目标对象，创建代理对象
        IUserDao proxy2 = (IUserDao) new ProxyFactory(target2).getProxyInstance();
        // 【内存中动态生成的代理对象 class com.sun.proxy.$Proxy0】
        System.out.println(proxy2.getClass());

        // 执行方法   【代理对象】
        proxy2.save();

    }
}