package com.lfd.yunwei.framework.db.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ldf.yunwei.framework.pojo.entity.base.AbstractBaseDomain;
import com.lfd.yunwei.framework.db.base.IBaseCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Hagon.Gh
 * @projectName lfd-yunwei
 * @className: BaseCrudRepository
 * @description: 通用的业务实现
 */
@Transactional(readOnly = true)
public class BaseCrudRepository<T extends AbstractBaseDomain<T>,M extends BaseMapper<T>> implements IBaseCrudRepository<T> {

    @Autowired(required = false)
    protected  M mapper;

    // 获取泛型的class
//    private Class<T> entityClass=(Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public T insert(T domain) {
       return mapper.insert(domain) > 0 ? domain : null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int delete(QueryWrapper<T> queryWrapper) {
        return mapper.delete(queryWrapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int update(T domain, QueryWrapper<T> queryWrapper) {
            return mapper.update(domain,queryWrapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int updateById(T domain) {
        return mapper.updateById(domain);
    }

    @Override
    public int count(QueryWrapper<T> queryWrapper) {
        return mapper.selectCount(queryWrapper);
    }

    @Override
    public T selectOne(QueryWrapper<T> queryWrapper) {
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public List<T> getList(QueryWrapper<T> queryWrapper) {
        return mapper.selectList(queryWrapper);
    }


}
