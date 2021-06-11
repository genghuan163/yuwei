package com.lfd.yunwei.framework.annotation;

/**
 * @author Hogon.Gh
 * @description: 权限枚举
 */
public enum AuthPolicy {

    /**
     * 必传
     */
    REQUIRED,

    /**
     * 过滤方法
     */
    IGNORED_METHOD,
    /**
     * 非必传token
     */
    OPTIONAL,
}
