package com.tcsr.framework.common.utils;

import com.tcsr.framework.common.exception.BizException;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 断言工具类
 * @author tangzhong
 * @date   2025-08-28 16:19
 * @since  V1.0.0.0
 */
public class AssertUtils {

    private AssertUtils(){}

    public static <T> void predicate(Predicate<T> predicate, T t, String message){
        predicate(predicate, t, () -> message);
    }

    public static <T> void predicate(Predicate<T> predicate, T t, Supplier<String> messageSupplier){
        if(predicate.test(t)){
            throw new BizException(messageSupplier.get());
        }
    }

}