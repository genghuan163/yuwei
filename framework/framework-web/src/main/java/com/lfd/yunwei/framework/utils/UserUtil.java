//package com.kodai.mall.framework.utils;
//
//import com.kodai.mall.framework.pojo.dto.user.UserDto;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//
///**
// * @author Hogon.Gh
// * @description: ThreadLocal 工具类
// */
//public class UserUtil {
//    public static String getToken(){
//        UserDto currentUser = RequestHolder.getCurrentUser();
//        return Objects.isNull(currentUser) ? null : currentUser.getUserToken();
//    }
//
//    public static Long getUserId(){
//        UserDto currentUser = RequestHolder.getCurrentUser();
//        return Objects.isNull(currentUser) ? null : currentUser.getUserId();
//    }
//
//    /**
//     * 添加
//     * @param userInfo
//     * @param request
//     */
//    public static void add(UserDto userInfo, HttpServletRequest request){
//        RequestHolder.add(userInfo);
//        RequestHolder.add(request);
//    }
////    /**
////     * 添加
////     * @param userInfo
////     */
////    public static void add(UserInfo userInfo){
////        RequestHolder.add(userInfo);
////    }
//
//    /**
//     * 获取用户信息
//     */
//    public static UserDto getUser(){
//        return RequestHolder.getCurrentUser();
//    }
//    /**
//     * 获取请求信息
//     */
//    public static HttpServletRequest getHttpServletRequest(){
//        return RequestHolder.getHttpServletRequest();
//    }
//
//    /**
//     * 删除信息
//     */
//    public static void remove(){
//        RequestHolder.remove();
//    }
//}
