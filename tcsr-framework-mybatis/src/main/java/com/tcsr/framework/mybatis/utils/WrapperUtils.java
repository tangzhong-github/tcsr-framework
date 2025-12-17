package com.tcsr.framework.mybatis.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.tcsr.framework.mybatis.entity.BaseEntity;

/**
 *
 * @author tangzhong
 * @date   2025-08-28 16:11
 * @since  V1.0.0.0
 */
public class WrapperUtils {

    private WrapperUtils(){}

    public static <T extends BaseEntity> LambdaQueryWrapper<T> of(Class<T> clazz){
        return new LambdaQueryWrapper<>(clazz);
    }

    public static <T extends BaseEntity> LambdaQueryWrapper<T> of(SFunction<T, ?> function, Object value){
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(function, value);
        return wrapper;
    }

}