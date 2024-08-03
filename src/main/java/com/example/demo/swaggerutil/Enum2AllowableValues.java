package com.example.demo.swaggerutil;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Enum2AllowableValues {
    Class<? extends Enum<?>> value();
    String method() default "name"; // 默认使用 name 方法
}