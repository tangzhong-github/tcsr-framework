package com.tcsr.framework.mybatis.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应结果对象
 * @author tangzhong
 * @date   2025-09-11 16:36
 * @since  V1.0.0.0
 */
@Getter
@Setter
public class PageResult<T> implements Serializable {

    private PageResult(){}

    public static <T> PageResult<T> of(long total, List<T> list){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.total = total;
        pageResult.list = list;
        return pageResult;
    }

    /** 总数 */
    private long total;

    /** 分页查询响应数据 */
    private List<T> list;

}