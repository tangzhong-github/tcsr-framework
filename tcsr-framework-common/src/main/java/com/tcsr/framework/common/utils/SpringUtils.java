package com.tcsr.framework.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author tangzhong
 * @date   2025-09-28 15:50
 * @since  V1.0.0.0
 */
@Primary
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    @SuppressWarnings("NullableProblems")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * 获取 ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 通过类型获取 Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return APPLICATION_CONTEXT.getBean(clazz);
    }

    /**
     * 通过类型获取 Bean
     */
    public static <T> T getBean(Class<T> clazz, String beanName) {
        return APPLICATION_CONTEXT.getBean(beanName, clazz);
    }

}