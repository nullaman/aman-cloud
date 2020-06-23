package com.aman.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 深拷贝测试对象
 */
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class DeepCopy implements Cloneable {
    private String name;
    private Integer sex;
    private Integer age;

    protected DeepCopy clone() {
        DeepCopy deepCopy = null;
        try {
            // 浅拷贝：基本类型的克隆
            deepCopy = (DeepCopy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return deepCopy;
    }

}
