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
public class EditExecuteDescriptor<E extends BaseDTO> extends AddExecuteDescriptor<E> {

    private EditExecuteDescriptor(){}

    public static <E extends BaseDTO> EditExecuteDescriptor<E> of(E executeDTO, Consumer<E> bizHandlerBeforeExecute, String...copyTags){
        EditExecuteDescriptor<E> executeDescriptor = new EditExecuteDescriptor<>();
        executeDescriptor.setExecuteDTO(executeDTO);
        executeDescriptor.setBizHandlerBeforeExecute(bizHandlerBeforeExecute);
        executeDescriptor.setCopyTags(copyTags);
        return executeDescriptor;
    };

}