package com.tcsr.framework.mybatis.execute;

import com.tcsr.framework.mybatis.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * @author tangzhong
 * @date   2025-10-14 11:57
 * @since  V1.0.0.0
 */
@Getter
@Setter
public class ExecuteDescriptorForEdit<E extends BaseDTO> extends ExecuteDescriptorForAdd<E> {

    private ExecuteDescriptorForEdit(){}

    public static <E extends BaseDTO> ExecuteDescriptorForEdit<E> of(E executeDTO, Consumer<E> bizHandlerBeforeExecute, String...copyTags){
        ExecuteDescriptorForEdit<E> executeDescriptor = new ExecuteDescriptorForEdit<>();
        executeDescriptor.setExecuteDTO(executeDTO);
        executeDescriptor.setBizHandlerBeforeExecute(bizHandlerBeforeExecute);
        executeDescriptor.setCopyTags(copyTags);
        return executeDescriptor;
    };

}