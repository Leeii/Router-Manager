package com.leeiidesu.lib.router.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解， 跳转携带的参数
 * Created by liyi on 2018/2/24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface With {
    String value() default "";
}
