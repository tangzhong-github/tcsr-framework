package com.tcsr.framework.mybatis.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页
 * @author tangzhong
 * @date   2025-09-11 16:08
 * @since  v1.0
 */
@Getter
@Setter
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {

    private Page(){}

    public static <T> Page<T> of(QueryCondition condition) {
        Page<T> page = new Page<>();
        //page.setCurrent(HttpServletUtils.getParameter(HttpServletConstants.PAGE_NUMBER, Long.class, 1L))
                //.setSize(HttpServletUtils.getParameter(HttpServletConstants.PAGE_SIZE, Long.class, 10L));
        page.setCurrent(1);
        page.setSize(10);
        page.setCondition(condition);
        return page;
    }

    /** 查询条件 */
    private QueryCondition condition;

    /** 数据权限 */
    private String dataScope;

}