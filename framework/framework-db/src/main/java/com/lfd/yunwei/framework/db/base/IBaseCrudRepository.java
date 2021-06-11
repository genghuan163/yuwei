package com.lfd.yunwei.framework.db.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ldf.yunwei.framework.pojo.entity.base.AbstractBaseDomain;

import java.util.List;

/**
 * @author Hagon.Gh
 * @projectName lfd-yunwei
 * @className: IBaseCrudRepository
 * @description: 通用的业务实现
 */
public interface IBaseCrudRepository<T extends AbstractBaseDomain<T>> {

    T insert(T domain);

    int delete(QueryWrapper<T> queryWrapper);

    int update(T domain, QueryWrapper<T> queryWrapper);

    int updateById(T domain);

    int count(QueryWrapper<T> queryWrapper);

    T selectOne(QueryWrapper<T> queryWrapper);

    List<T> getList(QueryWrapper<T> queryWrapper);

}