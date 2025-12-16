package com.tcsr.framework.mybatis.interceptor;

import com.tcsr.framework.common.metadata.MetadataProviderFactory;
import com.tcsr.framework.common.metadata.MetadataTypeConstants;
import com.tcsr.framework.common.utils.ReflectionUtils;
import com.tcsr.framework.mybatis.annotation.Metadata;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 元数据处理拦截器
 * @author tangzhong
 * @since  2025-10-24 11:48
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
})
public class MetadataHandleInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取方法参数
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = args[1];
        // 在执行前处理逻辑
        handleMetadataBeforeInvoke(sqlCommandType, parameter);
        // 执行原方法
        Object result = invocation.proceed();
        // 在执行后处理逻辑
        handleMetadataAfterInvoke(result);
        return result;
    }

    private void handleMetadataBeforeInvoke(SqlCommandType sqlCommandType, Object parameter) {
        Class<?> targetClazz;
        List<?> targetObjList;
        if(parameter instanceof List<?> list){
            targetClazz = list.get(0).getClass();
            targetObjList = list;
        }else{
            targetClazz = parameter.getClass();
            targetObjList = Collections.singletonList(parameter);
        }
        this.initMetadataValue(targetClazz, sqlCommandType, targetObjList);
    }

    private void initMetadataValue(Class<?> targetClazz, SqlCommandType sqlCommandType, List<?> targetObjList){
        Map<Field, Object> metadataValueMap = new HashMap<>();
        List<Field> fields = ReflectionUtils.getAllFields(targetClazz);
        for (Field field : fields) {
            if(field.isAnnotationPresent(Metadata.class)){
                Metadata metadataAnnotation = field.getAnnotation(Metadata.class);
                if(Arrays.asList(metadataAnnotation.commandTypes()).contains(sqlCommandType)){
                    String metadataType = metadataAnnotation.dataType();
                    Object value;
                    if(MetadataTypeConstants.CONSTANT.equals(metadataType)){
                        value = metadataAnnotation.value();
                    }else{
                        value = MetadataProviderFactory.get(metadataType).value();
                    }
                    metadataValueMap.put(field, value);
                }
            }
        }
        if(!CollectionUtils.isEmpty(metadataValueMap)){
            for (Object targetObj : targetObjList) {
                metadataValueMap.forEach((field, value)->{
                    ReflectionUtils.setField(field, targetObj, value);
                });
            }
        }
    }

    private void handleMetadataAfterInvoke(Object result) {

    }

}