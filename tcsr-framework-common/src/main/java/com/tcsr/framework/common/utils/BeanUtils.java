package com.tcsr.framework.common.utils;

import com.tcsr.framework.common.annotation.BeanCopyTag;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tangzhong
 * @since  2025-08-28 16:25
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static void copyPropertiesByTag(Object source, Object target, String...tags){
        copyProperties(source, target, getIgnorePropertiesByTag(source, tags));
    }

    public static <T> T copyBean(Object source, Class<T> targetClazz, String...ignoreProperties) {
        if(source == null){
            return null;
        }
        T targetObj;
        try {
            targetObj = targetClazz.getDeclaredConstructor().newInstance();
            copyProperties(source, targetObj, ignoreProperties);
        } catch (Exception e) {
            return null;
        }
        return targetObj;
    }

    public static <T> T copyBeanByTag(Object source, Class<T> targetClazz, String...tags){
        if(source == null){
            return null;
        }
        return copyBean(source, targetClazz, getIgnorePropertiesByTag(source, tags));
    }

    public static <T> List<T> copyBeanList(List<?> sourceList, Class<T> targetClazz, String...ignoreProperties){
        if(sourceList == null){
            return null;
        }
        return sourceList.stream()
                .map(src -> copyBean(src, targetClazz, ignoreProperties))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static String[] getIgnorePropertiesByTag(Object source, String...tags){
        List<String> ignoreProperties = new ArrayList<>();
        List<String> tagList = List.of(tags);
        if(!CollectionUtils.isEmpty(tagList)){
            for (Field field : ReflectionUtils.getAllFields(source.getClass())) {
                if (!field.isAnnotationPresent(BeanCopyTag.class)) {
                    ignoreProperties.add(field.getName());
                }else {
                    String[] identityTags = field.getAnnotation(BeanCopyTag.class).value();
                    if(Arrays.stream(identityTags).noneMatch(tagList::contains)){
                        ignoreProperties.add(field.getName());
                    }
                }
            }
        }
        return ignoreProperties.toArray(new String[0]);
    }

}