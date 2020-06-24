package com.aman.array.queue;


import java.util.Scanner;

/**
 * 数组实现单项列表：
 * bug：只能用一次？
 * 修改：查看环形队列实现
 *
 * @author aman
 * @date 2020/06/24
 */
public class SingleArrayQueue {
    public static void main(String[] args) {

        boolean flag = true;
        ArrayQueue queue = new ArrayQueue(4);
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


    public static class ArrayQueue {
        // 指向头部
        int front;
        // 指向尾部
        int rear;
        // 数据数量
        int maxSize;
        // 数据
        int[] array;

        ArrayQueue(int maxSize) {
            this.front = -1;
            this.rear = -1;
            this.maxSize = maxSize;
            this.array = new int[maxSize];
        }

        /**
         * 判断队列是否为空
         *
         * @return
         */
        public boolean isEmpty() {
            if (rear == -1) {
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
            if (rear + 1 == maxSize) {
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
            rear++; // 指针后移
            array[rear] = var;
        }

        /**
         * 弹出第一个
         *
         * @return
         */
        public int get() {
            if (isEmpty() || rear == front) {
                throw new RuntimeException("~~ 队列为空，获取异常 ~~");
            }
            front++;
            return array[front];
        }

        /**
         * 打印队列
         */
        public void print() {
            for (int i : array) {
                System.out.println(i);
            }
        }
    }

}
