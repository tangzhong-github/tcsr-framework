package com.tcsr.framework.mybatis.execute;

import com.tcsr.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务执行结果
 * @author tangzhong
 * @date   2025-10-14 11:57
 * @since  V1.0.0.0
 */
@Getter
@Setter
public class ExecuteResult<T extends BaseEntity> {

    protected ExecuteResult(){}

    private boolean success;

    private T entity;

    public static <T extends BaseEntity> ExecuteResult<T> of(boolean success){
        return of(success, null);
    }

    public static <T extends BaseEntity> ExecuteResult<T> of(boolean success, T entity){
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        executeResult.setSuccess(success);
        if(success && entity != null){
            executeResult.setEntity(entity);
        }
        return executeResult;
    }

}