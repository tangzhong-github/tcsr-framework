package com.tcsr.framework.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 跳过返回值封装标识：标识有该注解的方法，则返回值将不会被统一封装
 * @author tangzhong
 * @date   2025-08-28 15:41
 * @since  V1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipResponseWrapper {

}
