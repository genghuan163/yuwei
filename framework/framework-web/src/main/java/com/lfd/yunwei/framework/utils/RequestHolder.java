//package com.kodai.mall.framework.utils;
//
//
//import com.kodai.mall.framework.pojo.dto.user.UserDto;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author Hogon.Gh
// * @description: ThreadLocal 操作类
// */
//public class RequestHolder {
//  private static final ThreadLocal<UserDto> userHolder = new ThreadLocal<>();
//
//  private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
//
//  public static synchronized void add(UserDto token) {
//    userHolder.set(token);
//  }
//
//  public static synchronized void add(HttpServletRequest request) {
//    requestHolder.set(request);
//  }
//
//  public static UserDto getCurrentUser() {
//    return userHolder.get();
//  }
//
//  public static HttpServletRequest getHttpServletRequest() {
//    return requestHolder.get();
//  }
//
//  public static HttpServletRequest getCurrentRequest() {
//    return requestHolder.get();
//  }
//
//  public static synchronized void remove() {
//    userHolder.remove();
//    requestHolder.remove();
//  }
//}
