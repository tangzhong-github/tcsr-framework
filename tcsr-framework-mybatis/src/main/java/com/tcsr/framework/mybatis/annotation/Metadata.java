package com.tcsr.framework.mybatis.annotation;

import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author tangzhong
 * @since  2025-08-29 14:06
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Metadata {

    SqlCommandType[] commandTypes() default {SqlCommandType.INSERT};

    String dataType();

    String value() default "";

}
