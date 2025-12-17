package com.tcsr.framework.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author tangzhong
 * @date   2025-10-15 11:49
 * @since  V1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class TreeUtils<T> {

    private TreeUtils(){}

    private Function<T, Object> idGetter;

    private Function<T, Object> parentIdGetter;

    private Function<T, List<T>> childrenGetter;

    /**
     * 构建树结构，默认判断根节点方式：parentId 为 null
     * @param sourceList 数据源
     * @return 树结构
     */
    public List<T> build(List<T> sourceList){
        return build(sourceList, t->Objects.isNull(parentIdGetter.apply(t)));
    }

    public List<T> build(List<T> sourceList, Predicate<T> rootPredicate){
        return build(sourceList, rootPredicate, null);
    }

    public List<T> build(List<T> sourceList, Predicate<T> rootPredicate, Consumer<T> dataHandler){
        if(sourceList==null || sourceList.isEmpty()){
            return Collections.emptyList();
        }
        return sourceList.stream()
                .filter(rootPredicate)
                .peek(root -> {
                    if(dataHandler != null){
                        dataHandler.accept(root);
                    }
                    handleChildren(root, sourceList, dataHandler);
                })
                .collect(Collectors.toList());

    }

    private void handleChildren(T root, List<T> sourceList, Consumer<T> dataHandler){
        for (T t : sourceList) {
            if(Objects.equals(parentIdGetter.apply(t), idGetter.apply(root))){
                if(dataHandler != null){
                    dataHandler.accept(t);
                }
                handleChildren(t, sourceList, dataHandler);
                Optional.ofNullable(childrenGetter.apply(root)).orElse(new ArrayList<>()).add(t);
            }
        }
    }

}