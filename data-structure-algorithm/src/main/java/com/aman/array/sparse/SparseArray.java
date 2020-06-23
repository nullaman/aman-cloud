package com.aman.array.sparse;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        /**
         * 初始化数组（棋盘）
         */
        System.out.println("==================初始化数组（棋盘）==================");
        int[][] init = new int[10][11];
        init[2][3] = 1;
        init[3][6] = 2;
        init[5][8] = 2;
        System.out.println("数字行：" + init.length);
        System.out.println("数字列：" + init[0].length);
        for (int i = 0; i < init.length; i++) {
            for (int j = 0; j < init[0].length; j++) {
                System.out.print(init[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====================================");

        System.out.println("=================记录数据个数===================");
        int count = 0;
        for (int i = 0; i < init.length; i++) {
            for (int j = 0; j < init[0].length; j++) {
                if (init[i][j] != 0) {
                    count++;
                }
            }
        }
        System.out.println("个数 ：" + count);
        System.out.println("====================================");

        System.out.println("==================稀疏数组==================");
        // 稀疏数组 sparse
        int[][] sparse = new int[count + 1][3];
        sparse[0][0] = init.length;
        sparse[0][1] = init[0].length;
        sparse[0][2] = count;
        int index = 0;
        for (int i = 0; i < init.length; i++) {
            for (int j = 0; j < init[0].length; j++) {
                if (init[i][j] != 0) {
                    index++;
                    sparse[index][0] = i;
                    sparse[index][1] = j;
                    sparse[index][2] = init[i][j];
                }
            }
        }
        // 输出
        for (int i = 0; i < sparse.length; i++) {
            for (int j = 0; j < sparse[0].length; j++) {
                System.out.print(sparse[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====================================");

        System.out.println("==================稀疏数组->还原数组==================");
        // 还原数组
        int[][] ago = null;
        for (int i = 0; i < sparse.length; i++) {
            int h = sparse[i][0];
            int z = sparse[i][1];
            int v = sparse[i][2];
            System.out.println("横:" + h + ",纵:" + z + ",值:" + v);
            if (i == 0) {
                ago = new int[sparse[0][0]][sparse[0][1]];
                continue;
            }
            ago[h][z] = v;
        }
        for (int i = 0; i < ago.length; i++) {
            for (int j = 0; j < ago[0].length; j++) {
                System.out.print(ago[i][j] + " ");
            }
            System.out.println();
        }


    }


}

