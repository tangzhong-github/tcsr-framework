package com.tcsr.framework.common.exception;

import com.tcsr.framework.common.response.ResponseCodes;
import lombok.Getter;

/**
 * 业务异常
 * @author tangzhong
 * @since  2025-08-26 17:32
 */
@Getter
public class BizException extends RuntimeException{

    private final Integer errorCode;

    public BizException(String message) {
        super(message);
        this.errorCode = ResponseCodes.FAIL_BIZ;
    }

}