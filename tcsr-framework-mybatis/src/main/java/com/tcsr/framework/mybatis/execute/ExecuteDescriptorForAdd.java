package com.tcsr.framework.mybatis.execute;

import com.tcsr.framework.mybatis.dto.ExecuteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * @author tangzhong
 * @since  2025-10-14 11:57
 */
@Getter
@Setter
public class ExecuteDescriptorForAdd<E extends ExecuteDTO> extends ExecuteDescriptor {

    private E executeDTO;

    private Consumer<E> bizHandlerBeforeExecute;

    /** 操作时的属性拷贝标识 */
    private String[] copyTags;

    protected ExecuteDescriptorForAdd(){};

    public static <E extends ExecuteDTO> ExecuteDescriptorForAdd<E> of(E executeDTO, Consumer<E> bizHandlerBeforeExecute, String...copyTags){
        ExecuteDescriptorForAdd<E> executeDescriptor = new ExecuteDescriptorForAdd<>();
        executeDescriptor.setExecuteDTO(executeDTO);
        executeDescriptor.setBizHandlerBeforeExecute(bizHandlerBeforeExecute);
        executeDescriptor.setCopyTags(copyTags);
        return executeDescriptor;
    };

}