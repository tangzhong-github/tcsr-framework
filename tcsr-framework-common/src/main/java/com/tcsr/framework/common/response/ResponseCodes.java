package com.tcsr.framework.common.response;

/**
 * 响应码
 * @author tangzhong
 * @date   2025-12-12 10:11
 * @since  V1.0.0
 */
public interface ResponseCodes {

    /** 成功 */
    Integer SUCCESS = 10000;

    /** 系统异常 */
    Integer FAIL_SYS = 10001;

    /** 业务异常 */
    Integer FAIL_BIZ = 10002;

}
