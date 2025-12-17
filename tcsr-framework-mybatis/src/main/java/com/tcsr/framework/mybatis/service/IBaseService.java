package com.tcsr.framework.mybatis.service;

import com.tcsr.framework.mybatis.api.Page;
import com.tcsr.framework.mybatis.api.PageResult;
import com.tcsr.framework.mybatis.entity.BaseEntity;

/**
 *
 * @author tangzhong
 * @date   2025-10-28 10:51
 * @since  V1.0.0
 */
public interface IBaseService<T extends BaseEntity> {

    T getById(Long id);

    <R> PageResult<R> pageList(Page<R> page);

}
