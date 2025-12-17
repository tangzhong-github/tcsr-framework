package com.tcsr.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author tangzhong
 * @date   2025-10-14 9:52
 * @since  V1.0.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BeanCopyTag {

    String[] value() default {};

    interface Constants{
        String ADD = "add";
        String EDIT = "edit";
    }

}
