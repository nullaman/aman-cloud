package com.aman.array.queue;

import java.util.Scanner;

/**
 * 数组实现 - 环形队列
 * 注意：创建队列时的传入大小非内部maxSize的大小
 * maxSize为5，实际储存为4，最后一位是约定为空的大小，
 * <p>
 * 参数：
 * front：永远指向第一个数据所在位置
 * rear：永远指向最后一个数据的【后一位】
 * <p>
 * 重点：'%'取模的使用
 *
 * @author nullaman
 * @date 2020/06/24
 */
public class CircularArrayQueue {
    public static void main(String[] args) {

        boolean flag = true;
        CircularQueue queue = new CircularQueue(4);
        Scanner scanner = new Scanner(System.in);

        while (flag) {
            System.out.println("======================");
            System.out.println("1:判断队列是否为空");
            System.out.println("2:判断队列是否已满");
            System.out.println("3:添加数据");
            System.out.println("4:弹出第一个");
            System.out.println("5:打印");
            System.out.println("6:退出");
            System.out.println("======================");
            int index = scanner.nextInt();
            switch (index) {
                case 1:
                    queue.isEmpty();
                    break;
                case 2:
                    queue.isFull();
                    break;
                case 3:
                    System.out.println("请输入添加数据：");
                    queue.add(scanner.nextInt());
                    break;
                case 4:
                    try {
                        int i = queue.get();
                        System.out.println("得到：i = " + i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    queue.print();
                    break;
                case 6:
                    flag = false;
                    scanner.close();
                    break;
            }
        }


    }

    /**
     * 环形队列
     */
    public static class CircularQueue {
        // 指向头部
        int front;
        // 指向尾部
        int rear;
        // 数据数量
        int maxSize;
        // 数据
        int[] array;

        CircularQueue(int maxSize) {
            // 初始头部指向第一个数
            this.front = 0;
            // 初始指向0，永远指向最后一个数的后一位
            this.rear = 0;
            this.maxSize = maxSize + 1;
            this.array = new int[this.maxSize];
        }

        /**
         * 判断队列是否为空
         *
         * @return
         */
        public boolean isEmpty() {
            if (rear == front) {
                System.out.println("~~ 队列为空 ~~");
                return true;
            }
            System.out.println("~~ 队列不为null ~~");
            return false;
        }

        /**
         * 判断队列是否满
         *
         * @return
         */
        public boolean isFull() {
            if (isEmpty()) {
                return false;
            }
            if ((rear + 1) % maxSize == front) {
                System.out.println("~~ 队列为已满 ~~");
                return true;
            }
            System.out.println("~~ 队列未满 ~~");
            return false;
        }

        /**
         * 队列添加数据
         *
         * @param var
         */
        public void add(int var) {
            if (isFull()) {
                System.out.println("~~ 无法加入 ~~");
                return;
            }
            // rear本身指向后一位
            array[rear] = var;
            rear++; // 指针后移
            rear = rear % maxSize;// 防止过大，取模 回归
        }

        /**
         * 弹出第一个
         *
         * @return
         */
        public int get() {
            if (isEmpty()) {
                throw new RuntimeException("~~ 队列为空，获取异常 ~~");
            }
            int var = array[front];
            front++; // 指针后移
            front = front % maxSize;// 防止过大，取模 回归
            return var;
        }

        /**
         * 从头头部打印队列
         */
        public void print() {
            if (isEmpty()) {
                return;
            }
            for (int i = front; i < ((rear + maxSize - front) % maxSize) + front; i++) {
                System.out.println(array[i % maxSize]);
            }
        }

    }

}
