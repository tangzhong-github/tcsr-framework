package com.tcsr.framework.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 国际化消息工具类
 * @author tangzhong
 * @date   2025-10-24 12:02
 * @since  V1.0.0
 */
//@Slf4j
@RequiredArgsConstructor
public class MessageUtils {

    private static MessageSource messageSource;

    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String getMessage(String code, Object... args) {
        String resultStr;
        try {
            if(messageSource == null){
                messageSource = SpringUtils.getBean(MessageSource.class);
            }
            resultStr = messageSource.getMessage(code, args,  LocaleContextHolder.getLocale());
        }catch (Exception e){
            //log.error("Un find the error message by the code:{}", code, e);
            resultStr = code;
        }
        return resultStr;
    }

}