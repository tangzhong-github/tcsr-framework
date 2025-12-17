package com.tcsr.framework.mybatis.mapper;

import com.tcsr.framework.mybatis.api.Page;
import com.tcsr.framework.mybatis.entity.BaseEntity;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2025-09-11 16:31
 * @since  V1.0.0.0
 */
public interface BaseMapper<T extends BaseEntity> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    <R> List<R> pageList(Page<R> page);

}