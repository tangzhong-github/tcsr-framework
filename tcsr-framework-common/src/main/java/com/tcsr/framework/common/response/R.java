package com.tcsr.framework.common.response;

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
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 398081545062394563L;

    private R(){}

    /** 响应码：{@link ResponseCodes} */
    private Integer code;

    /** 响应信息 */
    private String message;

    /** 响应数据 */
    private T data;

    private static <T> R<T> build(Integer code, String message, T data){
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static <T> R<T> success(){
        return R.success(null);
    }

    public static <T> R<T> success(T data){
        return R.success("操作成功", data);
    }

    public static <T> R<T> success(String message, T data){
        return build(ResponseCodes.SUCCESS, message, data);
    }
    
    public static <T> R<T> fail(String message){
        return fail(ResponseCodes.FAIL_SYS, message);
    }

    public static <T> R<T> fail(Integer code, String message){
        return build(code, message, null);
    }

}
