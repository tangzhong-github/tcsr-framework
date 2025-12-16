package com.tcsr.framework.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tangzhong
 * @since  2025-12-15 16:03
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz, fields::add);
        return fields;
    }

}