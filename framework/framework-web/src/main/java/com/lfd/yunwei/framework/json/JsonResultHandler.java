package com.lfd.yunwei.framework.json;

import com.lfd.yunwei.framework.annotation.JsonResult;
import com.lfd.yunwei.framework.constants.WebConstant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.expression.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Hogon.Gh
 * @description: 返回值拦截器
 */
public class JsonResultHandler implements HandlerMethodReturnValueHandler, BeanPostProcessor {
    private static volatile JsonResultHandler jsonResultHandler;
    private JsonResultHandler() {
        if (jsonResultHandler != null) {
            throw new ParseException(500, "拦截异常");
        }
    }
    public static JsonResultHandler getInstance() {
        if (jsonResultHandler == null)  {
            synchronized (JsonResultHandler.class) {
                if (jsonResultHandler == null) {
                    jsonResultHandler = new JsonResultHandler();
                }
            }
        }
        return jsonResultHandler;
    }
    public Object readResolve() {
        return this;
    }

    private List<ResponseBodyAdvice<Object>> advices = new ArrayList<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ResponseBodyAdvice) {
            advices.add((ResponseBodyAdvice<Object>) bean);
        } else if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter handlerAdapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(handlerAdapter.getReturnValueHandlers());

            JsonResultHandler jsonHandler = null;
            for (int i = handlers.size() - 1; i >= 0; i--) {
                HandlerMethodReturnValueHandler handler = handlers.get(i);
                if (handler instanceof JsonResultHandler) {
                    jsonHandler = (JsonResultHandler) handlers.remove(i);
                    break;
                }
            }

            // 调整 JSON Handler 顺序
            if (jsonHandler != null) {
                handlers.add(0, jsonHandler);
                handlerAdapter.setReturnValueHandlers(handlers);
            }
        }
        return bean;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getMethodAnnotation(JsonResult.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        for (ResponseBodyAdvice<Object> advice : advices) {
            if (advice.supports(returnType, null)) {
                returnValue = advice.beforeBodyWrite(returnValue, returnType, MediaType.APPLICATION_JSON_UTF8, null,
                        new ServletServerHttpRequest(Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class))),
                        new ServletServerHttpResponse(Objects.requireNonNull(webRequest.getNativeResponse(HttpServletResponse.class))));
            }
        }

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Annotation[] annotations = returnType.getMethodAnnotations();
        CustomerJsonSerializer jsonSerializer = new CustomerJsonSerializer();

        boolean shortDateFormat = false;
        for (Annotation annotation : annotations) {
            if (annotation instanceof JsonResult) {
                JsonResult annotation1 = (JsonResult) annotation;
                jsonSerializer.filter(annotation1);
                shortDateFormat = annotation1.shortDateFormat();
                break;
            }
        }

        response.setContentType(WebConstant.MEDIA_TYPE);
        response.getWriter().write(jsonSerializer.toJson(returnValue,shortDateFormat));
    }
}
