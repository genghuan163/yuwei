package com.lfd.yunwei.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chuan.jiang
 * @since 2017-07-15
 */
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypted {

    /**
     * 需要加密的字段
     */
    String value() default "";
}
