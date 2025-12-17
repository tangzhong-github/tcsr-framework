package com.tcsr.framework.mybatis.execute;

import com.tcsr.framework.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * @author tangzhong
 * @date   2025-10-14 11:57
 * @since  V1.0.0
 */
@Getter
@Setter
public class ExecuteDescriptorForAdd<E extends BaseDTO> extends ExecuteDescriptor {

    private E executeDTO;

    private Consumer<E> bizHandlerBeforeExecute;

    /** 操作时的属性拷贝标识 */
    private String[] copyTags;

    protected ExecuteDescriptorForAdd(){};

    public static <E extends BaseDTO> ExecuteDescriptorForAdd<E> of(E executeDTO, Consumer<E> bizHandlerBeforeExecute, String...copyTags){
        ExecuteDescriptorForAdd<E> executeDescriptor = new ExecuteDescriptorForAdd<>();
        executeDescriptor.setExecuteDTO(executeDTO);
        executeDescriptor.setBizHandlerBeforeExecute(bizHandlerBeforeExecute);
        executeDescriptor.setCopyTags(copyTags);
        return executeDescriptor;
    };

}