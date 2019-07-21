package com.taylor.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Repository
 * @Description 注解
 * @Author lidengtai
 * @Date 2019/7/7 14:24
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期
@Target(ElementType.TYPE)//标注位置
public @interface Repository {
    String value() default "";
}
