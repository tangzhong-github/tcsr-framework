package com.tcsr.framework.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 *
 * @author tangzhong
 * @date   2025-10-29 14:39
 * @since  V1.0.0.0
 */
public class HttpServletUtils {

    private HttpServletUtils(){}

    public static HttpServletRequest getRequest() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest)
                .orElse(null);
    }

    public static String getParameter(String name) {
        return getParameter(name, null);
    }

    public static String getParameter(String name, String defaultValue) {
        return getParameter(name, String.class, defaultValue);
    }

    public static <T> T getParameter(String name, Class<T> clazz, T defaultValue) {
        return Optional.ofNullable(getRequest())
                .map(e -> ConvertUtils.convert(e.getParameter(name), clazz))
                .orElse(defaultValue);
    }

    public static String getHeader(String name) {
        return Optional.ofNullable(getRequest())
                .map(request-> request.getHeader(name))
                .orElse(null);
    }
}