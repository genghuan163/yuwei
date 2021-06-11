package com.lfd.yunwei.framework.config;

import com.lfd.yunwei.framework.json.JsonResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Slf4j
@Aspect
@Configuration
@EnableTransactionManagement
public class CustomConfiguration extends WebMvcConfigurerAdapter implements ApplicationListener<ApplicationContextEvent> {

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(jsonResultHandler());
    }

    @Bean
    public JsonResultHandler jsonResultHandler() {
        return JsonResultHandler.getInstance();
    }
}
