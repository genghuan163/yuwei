package com.lfd.yunwei.framework.annotation;

import java.lang.annotation.*;

/**
 * @author Hagon.Gh
 * @projectName lfd-yunwei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonResult {
    /**
     * JSON 序列化的对象
     */
    Class<?> type();

    /**
     * JSON 返回时包含的字段
     */
    String[] include() default {};

    /**
     * JSON 返回时排除的字段
     */
    String[] exclude() default {};

    /**
     * 是否短日期格式, 默认 false
     * true: yyyy-MM-dd
     * false: yyyy-MM-dd HH:mm:ss
     */
    boolean shortDateFormat() default false;
}
