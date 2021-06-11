//package com.kodai.mall.framework.aop;
//
//
//import com.kodai.mall.framework.annotation.AuthPolicy;
//import com.kodai.mall.framework.annotation.Authenticate;
//import com.kodai.mall.framework.common.constants.UserConstant;
//import com.kodai.mall.framework.pojo.dto.user.UserDto;
//import com.kodai.mall.framework.common.exception.AuthenticationException;
//import com.kodai.mall.framework.common.exception.code.ResponseCode;
//import com.kodai.mall.framework.utils.UserUtil;
//import com.kodai.mall.service.proxy.feign.user.UserProvider;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Objects;
//import java.util.Optional;
//
//
///**
// * @author Hogon.Gh
// * @description: 权限Aop
// */
//@Slf4j
//@Aspect
//@Component
//public class AuthenticateAspect {
//
//    @Pointcut("execution(public * com.kodai.mall.*.controller.*.*(..)) ||" +
//            " @annotation(com.kodai.mall.framework.annotation.Authenticate)")
//    public void authorityAop() {
//    }
//    @Resource
//    private UserProvider userProvider;
//
//    /**
//     * 1.注解加到Controller
//     * 2.注解加到Method  (1.默认方法必传token    2. 方法传token就拿用户信息 不传token 就不用管  3 。用户过滤token)
//     *
//     * @param point
//     * @return
//     * @throws Throwable
//     */
//    @Around("authorityAop()")
//    public Object authenticateAround(ProceedingJoinPoint point) throws Throwable {
//        //获取注解
//        MethodSignature signature = (MethodSignature) point.getSignature();
//
//        Method method = signature.getMethod();
//        Class<?> handlerClass = method.getDeclaringClass();
//        Authenticate authenticateAnnotation = method.getAnnotation(Authenticate.class);
//        if (Objects.isNull(authenticateAnnotation)) {
//            Authenticate annotation = handlerClass.getAnnotation(Authenticate.class);
//            if (Objects.isNull(annotation)) {
//                return point.proceed();
////                throw new AuthenticationException(ResponseCode.UserNotLogin);
//            }
//            authenticateAnnotation = annotation;
//        }
//
//        //过滤token
//        if (AuthPolicy.IGNORED_METHOD.equals(authenticateAnnotation.value())) {
//            return point.proceed();
//        }
//        //获取参数上得所有注解
//        HttpServletRequest request = ((ServletRequestAttributes)
//                RequestContextHolder.currentRequestAttributes()).getRequest();
//        String token = request.getHeader(UserConstant.LOGIN_USER_TOKEN);
//        //没有传token 放行
//        if (AuthPolicy.OPTIONAL.equals(authenticateAnnotation.value()) && StringUtils.isBlank(token)) {
//            return point.proceed();
//        }
//        if (AuthPolicy.REQUIRED.equals(authenticateAnnotation.value()) && StringUtils.isBlank(token)) {
//            Optional.ofNullable(token).orElseThrow(() -> new AuthenticationException(ResponseCode.UserTokenIsEmpty));
//        }
//        //用户信息
//        UserDto userInfo = userProvider.getUserByToken(token);
//
//        //用户没有登陆
//        if (AuthPolicy.REQUIRED.equals(authenticateAnnotation.value())) {
//            Optional.ofNullable(userInfo).orElseThrow(() -> new AuthenticationException(ResponseCode.UserNotLogin));
//        }
//        //传token 但是没有登陆 token 过期 放行
//        if (AuthPolicy.OPTIONAL.equals(authenticateAnnotation.value()) && Objects.isNull(userInfo)) {
//            return point.proceed();
//        }
//        if (AuthPolicy.REQUIRED.equals(authenticateAnnotation.value()) ||
//                AuthPolicy.OPTIONAL.equals(authenticateAnnotation.value())) {
//            UserUtil.add(userInfo,request);
//            //执行方法
//            Object proceed = point.proceed();
//            //移除
//            UserUtil.remove();
//            return proceed;
//        }
//        throw new AuthenticationException(ResponseCode.PrimaryIncorrect);
//    }
//}
