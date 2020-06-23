package com.aman.prototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 */
public class MyTest {
    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        prototype.setFileId(1);
        prototype.setFileName("文件1");

        Map<String, Double> scores = new HashMap<>();
        scores.put("cx", 60D);
        scores.put("ys", 99.5);
        scores.put("yjc", 99.9);
        prototype.setScores(scores);

        DeepCopy deepCopy = new DeepCopy("cx", 1, 18);
        prototype.setDeepCopy(deepCopy);

        List<DeepCopy> deepCopyList = new ArrayList<>();
        DeepCopy deepCopy1 = new DeepCopy("cx", 1, 18);
        DeepCopy deepCopy2 = new DeepCopy("ys", 0, 21);
        DeepCopy deepCopy3 = new DeepCopy("yjc", 0, 20);
        deepCopyList.add(deepCopy1);
        deepCopyList.add(deepCopy2);
        deepCopyList.add(deepCopy3);
        prototype.setDeepCopyList(deepCopyList);

        Map<Integer, DeepCopy> deepCopyMap = new HashMap<>();
        DeepCopy deepCopyH1 = new DeepCopy("cx", 1, 18);
        DeepCopy deepCopyH2 = new DeepCopy("ys", 0, 20);
        DeepCopy deepCopyH3 = new DeepCopy("yjc", 0, 19);
        deepCopyMap.put(1, deepCopyH1);
        deepCopyMap.put(2, deepCopyH2);
        deepCopyMap.put(3, deepCopyH3);
        prototype.setDeepCopyMap(deepCopyMap);

        System.out.println("初始:" + prototype);
        System.out.println("======================================");

        // 测试
        Prototype clone = prototype.clone();
        // 可
        clone.setFileId(22);

        // map
        Map<String, Double> scores1 = clone.getScores();
        scores1.remove("cx");

        // 若没加深拷贝，地址引用同一个
        DeepCopy deepCopy4 = clone.getDeepCopy();
        deepCopy4.setAge(1000);
        clone.setDeepCopy(deepCopy4);
        System.out.println(prototype.getDeepCopy() == clone.getDeepCopy()); // 没加深拷贝为true，地址引用同一个

        List<DeepCopy> deepCopyList1 = clone.getDeepCopyList();
        deepCopyList1.remove(1);

        Map<Integer, DeepCopy> deepCopyMap1 = clone.getDeepCopyMap();
        deepCopyMap1.remove(2);

        System.out.println("原型:" + prototype);
        System.out.println("======================================");
        System.out.println("拷贝:" + clone);
        System.out.println("======================================");
        System.out.println("原型==拷贝:" + (prototype == clone));
    }
}
