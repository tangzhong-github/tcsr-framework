package com.tcsr.framework.web.handler;

import com.tcsr.framework.common.exception.BizException;
import com.tcsr.framework.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author tangzhong
 * @date   2025-08-28 15:17
 * @since  V1.0.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

   // @Autowired
    //private MessageUtils messageUtils;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String messageCd = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String message = getMessage(messageCd);
        log.error("参数校验异常：{}", message, e);
        return R.fail(message);
    }

    @ExceptionHandler(BizException.class)
    public R<String> handleBizException(Exception e) {
        String message = getMessage(e.getMessage());
        log.error("系统业务异常：{}", message, e);
        return R.fail(message);
    }

    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e) {
        log.error("系统服务异常：", e);
        return R.fail(getMessage("framework.mybatis.web.ServerException"));
    }

    private String getMessage(String messageCd) {
        //return messageUtils.getMessage(messageCd);
        return messageCd;
    }

}