package com.lfd.yunwei.framework.annotation;


import java.lang.annotation.*;

/**
 * @author Hogon.Gh
 * @description: 权限注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authenticate {

    /**
     * 认证策略
     */
    AuthPolicy value() default AuthPolicy.REQUIRED;
}
