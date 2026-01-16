package com.tcsr.framework.common.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Api 统一返回结果
 *
 * @author tangzhong
 * @date   2024-01-12
 * @since  V1.0.0
 */
@Getter
@Setter
public class Response<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 398081545062394563L;

    private Response(){}

    /** 响应码：{@link ResponseCodes} */
    private Integer code;

    /** 响应信息 */
    private String message;

    /** 响应数据 */
    private T data;

    private static <T> Response<T> build(Integer code, String message, T data){
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> success(){
        return Response.success(null);
    }

    public static <T> Response<T> success(T data){
        return Response.success("操作成功", data);
    }

    public static <T> Response<T> success(String message, T data){
        return build(ResponseCodes.SUCCESS, message, data);
    }
    
    public static <T> Response<T> fail(String message){
        return fail(ResponseCodes.FAIL_SYS, message);
    }

    public static <T> Response<T> fail(Integer code, String message){
        return build(code, message, null);
    }

}
