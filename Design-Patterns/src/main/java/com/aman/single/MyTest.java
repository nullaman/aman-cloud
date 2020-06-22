package com.aman.single;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单例
 *
 * @author aman
 * @date 2020/06/19
 */
// 建议使用的单例
public class MyTest {
    //1、2
    private MyTest() {
    }
    /// 1 安全
//    private static final MyTest INSTANCE = new MyTest();
//    public static MyTest getInstance() {
//        return INSTANCE;
//    }

    /// 2 安全
//    private static class MyTestHolder {
//        private final static MyTest INSTANCE = new MyTest();
//    }
//    public static MyTest getInstance() {
//        return MyTestHolder.INSTANCE;
//    }

    /// 3 安全
    private static volatile MyTest INSTANCE;

    public static MyTest getInstance() {
        if (INSTANCE == null) {
            synchronized (MyTest.class) {
                if (INSTANCE == null)
                    INSTANCE = new MyTest();
            }
        }
        return INSTANCE;
    }


    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        long start = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue(10), new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 100000; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("当前核心线程: " + threadPoolExecutor.getCorePoolSize()
                        + ";当前运行线程: " + threadPoolExecutor.getPoolSize()
                        + ";当前队列大小: " + threadPoolExecutor.getQueue().size()
                        + ";当前线程名称: " + Thread.currentThread().getName()
                        + "\t单例地址: " + MyTest.getInstance() /// 1 、 2 、3
                );
            });
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
        }
        System.out.println("=====================全部执行完成:耗时" + (System.currentTimeMillis() - start) + "=====================");

    }
}
