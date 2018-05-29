package com.itplayer.core.base.service;


import com.itplayer.core.base.entity.BaseEntity;
import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.page.PageUtils;
import com.itplayer.core.base.query.BaseQueryModel;

import java.util.List;

/**
 * Created by caijun.yang on 2017/7/25.
 */
public interface BaseService<T extends BaseEntity, PK> {

    int create(T t);

    int update(T t);

    int deleteByPrimaryKey(PK id);

    T getByPrimaryKey(PK id);

    int deleteByEntity(T t);

    PageResult<T> queryPage(BaseQueryModel qm);

    int batchDelete(PK[] ids);

    List<T> findAll();
    
    List<T> findByEntity(T t);

}
