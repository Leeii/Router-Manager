package com.leeiidesu.lib.router.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解  代表该参数是Activity启动的一个Flag
 * Created by liyi on 2018/2/24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Flags {
}
