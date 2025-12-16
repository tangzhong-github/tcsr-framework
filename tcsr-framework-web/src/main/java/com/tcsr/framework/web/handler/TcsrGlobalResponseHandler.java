package com.tcsr.framework.web.handler;

import com.tcsr.framework.common.response.R;
import com.tcsr.framework.web.annotation.SkipResponseWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 全局返回结果统一封装处理器
 * @author tangzhong
 * @since  2025-08-28 15:21
 */
@RestControllerAdvice
@SuppressWarnings("NullableProblems")
public class TcsrGlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //判断是否需要处理该响应
        return !Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(SkipResponseWrapper.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //如果已经是ApiResponse类型，则直接返回（一般为被异常处理器拦截处理完）
        if(body instanceof R<?>){
            return body;
        }
        //统一封装返回结果
        return R.success(body);
    }

}