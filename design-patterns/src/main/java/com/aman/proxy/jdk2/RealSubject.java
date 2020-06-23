package com.aman.proxy.jdk2;

public class RealSubject implements Subject {

    @Override
    public int sellBooks() {
        System.out.println("卖书");
        return 1;
    }

    @Override
    public String speak() {
        System.out.println("说话");
        return "张三";
    }

    @Override
    public String eat() {
        System.out.println("吃东西");
        return "biaji~biaji~biaji";
    }

}