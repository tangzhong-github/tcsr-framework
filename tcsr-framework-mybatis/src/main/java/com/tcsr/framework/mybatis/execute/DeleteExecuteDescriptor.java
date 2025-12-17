package com.tcsr.framework.mybatis.execute;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author tangzhong
 * @date   2025-10-14 11:57
 * @since  V1.0.0
 */
@Getter
@Setter
public class DeleteExecuteDescriptor extends ExecuteDescriptor {

    private DeleteExecuteDescriptor(){}

    private List<Long> ids;

    private Consumer<List<Long>> ordinaryHandler;

    private Consumer<List<Long>> forceDeleteHandler;

    public static DeleteExecuteDescriptor of(List<Long> ids, Consumer<List<Long>> ordinaryBizHandler){
        return of(ids, ordinaryBizHandler, null);
    }

    public static DeleteExecuteDescriptor of(List<Long> ids, Consumer<List<Long>> ordinaryBizHandler, Consumer<List<Long>> forceDeleteBizHandler){
        DeleteExecuteDescriptor executeDescriptor = new DeleteExecuteDescriptor();
        executeDescriptor.setIds(ids);
        executeDescriptor.setOrdinaryHandler(ordinaryBizHandler);
        executeDescriptor.setForceDeleteHandler(forceDeleteBizHandler);
        return executeDescriptor;
    }

}