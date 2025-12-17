package com.tcsr.framework.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcsr.framework.common.utils.AssertUtils;
import com.tcsr.framework.common.utils.BeanUtils;
import com.tcsr.framework.common.utils.HttpServletUtils;
import com.tcsr.framework.mybatis.api.Page;
import com.tcsr.framework.mybatis.api.PageResult;
import com.tcsr.framework.mybatis.dto.BaseDTO;
import com.tcsr.framework.mybatis.entity.BaseEntity;
import com.tcsr.framework.mybatis.execute.ExecuteDescriptor;
import com.tcsr.framework.mybatis.execute.ExecuteDescriptorForAdd;
import com.tcsr.framework.mybatis.execute.ExecuteDescriptorForDelete;
import com.tcsr.framework.mybatis.execute.ExecuteDescriptorForEdit;
import com.tcsr.framework.mybatis.execute.ExecuteResult;
import com.tcsr.framework.mybatis.mapper.BaseMapper;
import com.tcsr.framework.mybatis.service.IBaseService;
import com.tcsr.framework.mybatis.utils.WrapperUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * @author tangzhong
 * @date   2025-10-28 10:54
 * @since  V1.0.0
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements IBaseService<T> {

    protected LambdaQueryWrapper<T> wrapper(){
        return WrapperUtils.of(getEntityClass());
    }

    protected T getByCondition(SFunction<T, ?> function, Object value) {
        return getByConditions(wrapper().eq(function, value));
    }

    protected T getByConditions(Wrapper<T> wrapper) {
        return getBaseMapper().selectOne(wrapper);
    }

    protected List<T> listByCondition(SFunction<T, ?> function, Object value) {
        return listByConditions(wrapper().eq(function, value));
    }

    protected List<T> listByConditions(Wrapper<T> wrapper) {
        return baseMapper.selectList(wrapper);
    }

    protected boolean supportForceDelete(){
        return Boolean.FALSE;
    }

    @SuppressWarnings("all")
    protected ExecuteResult<T> execute(ExecuteDescriptor executeDescriptorDTO) {
        if(executeDescriptorDTO instanceof ExecuteDescriptorForDelete deleteExecuteDescriptor){
            return this.doDelete(deleteExecuteDescriptor);
        } else if(executeDescriptorDTO instanceof ExecuteDescriptorForEdit editExecuteDescriptor){
            return this.doEdit(editExecuteDescriptor);
        } else if(executeDescriptorDTO instanceof ExecuteDescriptorForAdd addExecuteDescriptor){
            return this.doAdd(addExecuteDescriptor);
        }
        return ExecuteResult.of(Boolean.FALSE);
    }

    protected <E extends BaseDTO> ExecuteResult<T> doAdd(ExecuteDescriptorForAdd<E> executeDescriptor){
        E executeDTO = executeDescriptor.getExecuteDTO();
        Optional.ofNullable(executeDescriptor.getBizHandlerBeforeExecute()).ifPresent(handler -> handler.accept(executeDTO));
        T entity = BeanUtils.copyBeanByTag(executeDTO, super.getEntityClass(), executeDescriptor.getCopyTags());
        boolean success = super.save(entity);
        return ExecuteResult.of(success, entity);
    }

    protected <E extends BaseDTO> ExecuteResult<T> doEdit(ExecuteDescriptorForEdit<E> executeDescriptor){
        E executeDTO = executeDescriptor.getExecuteDTO();
        Optional.ofNullable(executeDescriptor.getBizHandlerBeforeExecute()).ifPresent(handler -> handler.accept(executeDTO));
        T stock = super.getById(executeDTO.getId());
        BeanUtils.copyPropertiesByTag(executeDTO, stock, executeDescriptor.getCopyTags());
        boolean success = super.updateById(stock);
        return ExecuteResult.of(success, stock);
    }

    protected ExecuteResult<T> doDelete(ExecuteDescriptorForDelete executeDescriptor){
        //业务端是否指定强制删除
        boolean isAppointForceDelete = HttpServletUtils.getParameter("forceDelete", Boolean.class, Boolean.FALSE);;
        //校验：当前功能是否支持强制删除
        AssertUtils.predicate(supportForceDelete -> supportForceDelete && isAppointForceDelete, supportForceDelete(), "当前功能不支持强制删除，请联系管理员！");
        //判断服务端删除策略
        boolean forceDelete = supportForceDelete() && isAppointForceDelete;
        //获取服务端在删除之前需执行的Handler
        Consumer<List<Long>> bizHandler = forceDelete ? executeDescriptor.getForceDeleteHandler() : executeDescriptor.getOrdinaryHandler();
        //执行删除之前需执行的Handler
        Optional.ofNullable(bizHandler).ifPresent(handler -> handler.accept(executeDescriptor.getIds()));
        //执行删除
        boolean success = super.removeByIds(executeDescriptor.getIds());
        return ExecuteResult.of(success);
    }

    @Override
    public T getById(Long id) {
        return super.getById(id);
    }

    @Override
    public <R> PageResult<R> pageList(Page<R> page) {
        List<R> list = getBaseMapper().pageList(page);
        return PageResult.of(page.getTotal(), list);
    }

}