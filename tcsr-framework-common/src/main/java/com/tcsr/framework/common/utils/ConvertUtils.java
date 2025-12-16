package com.tcsr.framework.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 类型转换工具类
 * 支持String与其他基本类型之间的相互转换
 * @author tangzhong
 * @since  2025-10-14 11:07
 */
public class ConvertUtils {
    
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 将String转换为其他类型
     * 
     * @param value 字符串值
     * @param targetType 目标类型
     * @param <T> 泛型类型
     * @return 转换后的值
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert(String value, Class<T> targetType) {
        if (value == null) {
            return null;
        }
        
        if (targetType == String.class) {
            return (T) value;
        }
        
        try {
            if (targetType == Integer.class || targetType == int.class) {
                return (T) Integer.valueOf(value);
            } else if (targetType == Long.class || targetType == long.class) {
                return (T) Long.valueOf(value);
            } else if (targetType == Double.class || targetType == double.class) {
                return (T) Double.valueOf(value);
            } else if (targetType == Float.class || targetType == float.class) {
                return (T) Float.valueOf(value);
            } else if (targetType == Boolean.class || targetType == boolean.class) {
                return (T) Boolean.valueOf(value);
            } else if (targetType == Byte.class || targetType == byte.class) {
                return (T) Byte.valueOf(value);
            } else if (targetType == Short.class || targetType == short.class) {
                return (T) Short.valueOf(value);
            } else if (targetType == BigDecimal.class) {
                return (T) new BigDecimal(value);
            } else if (targetType == BigInteger.class) {
                return (T) new BigInteger(value);
            } else if (targetType == LocalDate.class) {
                return (T) DateUtils.parseLocalDate(value);
            }else if (targetType == LocalDateTime.class) {
                return (T) DateUtils.parseLocalDateTime(value);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("无法将字符串 '" + value + "' 转换为 " + targetType.getSimpleName(), e);
        }
        
        throw new IllegalArgumentException("不支持的目标类型: " + targetType.getSimpleName());
    }
    
    /**
     * 将其他类型转换为String
     * 
     * @param value 待转换的值
     * @return 转换后的字符串
     */
    public static String convertToString(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof LocalDate localDate) {
            return DateUtils.formateLocalDate(localDate);
        }
        return value.toString();
    }

}
