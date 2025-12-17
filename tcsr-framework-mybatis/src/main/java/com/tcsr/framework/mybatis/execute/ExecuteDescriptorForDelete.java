package com.tcsr.framework.mybatis.execute;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author tangzhong
 * @date   2025-10-14 11:57
 * @since  V1.0.0.0
 */
@Getter
@Setter
public class ExecuteDescriptorForDelete extends ExecuteDescriptor {

    private ExecuteDescriptorForDelete(){}

    private List<Long> ids;

    private Consumer<List<Long>> ordinaryHandler;

    private Consumer<List<Long>> forceDeleteHandler;

    public static ExecuteDescriptorForDelete of(List<Long> ids, Consumer<List<Long>> ordinaryBizHandler){
        return of(ids, ordinaryBizHandler, null);
    }

    public static ExecuteDescriptorForDelete of(List<Long> ids, Consumer<List<Long>> ordinaryBizHandler, Consumer<List<Long>> forceDeleteBizHandler){
        ExecuteDescriptorForDelete executeDescriptor = new ExecuteDescriptorForDelete();
        executeDescriptor.setIds(ids);
        executeDescriptor.setOrdinaryHandler(ordinaryBizHandler);
        executeDescriptor.setForceDeleteHandler(forceDeleteBizHandler);
        return executeDescriptor;
    }

}