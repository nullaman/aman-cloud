package com.aman.cloud.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @version 1.0
 * @Description 自定义注解@HAStatusCheck  拦截service层   HAStatusCheckService
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HAStatusCheck {
    //自定义的方法描述信息
    String description() default "";
}

