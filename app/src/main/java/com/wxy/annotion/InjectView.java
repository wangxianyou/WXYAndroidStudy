package com.wxy.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IdRes;

/**
 * @author wxy
 * @description:
 * @date :2020/8/27 4:08 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectView {
    @IdRes int value();
}
