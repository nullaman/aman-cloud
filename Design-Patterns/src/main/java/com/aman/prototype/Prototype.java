package com.aman.prototype;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原型模式
 * 1.浅拷贝只能拷贝基本类型，集合、对象无法拷贝（拷贝后使用的还是同一个内存地址的对象）
 * 2.需要使用深拷贝，见代码
 *
 * @author aman
 * @date 2020/06/22
 */
@Data
public class Prototype implements Cloneable {
    private Integer fileId;
    private String fileName;

    private Map<String, Double> scores;

    private DeepCopy deepCopy;
    private List<DeepCopy> deepCopyList;
    private Map<Integer, DeepCopy> deepCopyMap;

    protected Prototype clone() {
        Prototype prototype = null;

        try {
            // 浅拷贝：基本类型的克隆
            prototype = (Prototype) super.clone();
            // 深拷贝 --- 不加的话会和原型内对象引用同一个对象地址
            prototype.scores = (Map<String, Double>) ((HashMap) this.scores).clone();
            prototype.deepCopy = this.deepCopy.clone();
            prototype.deepCopyList = (List<DeepCopy>) ((ArrayList) this.deepCopyList).clone();
            prototype.deepCopyMap = (Map<Integer, DeepCopy>) ((HashMap) this.deepCopyMap).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}
