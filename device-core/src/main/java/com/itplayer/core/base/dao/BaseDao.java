package com.itplayer.core.base.dao;


import com.itplayer.core.base.entity.BaseEntity;
import com.itplayer.core.base.query.BaseQueryModel;

import java.util.List;

/**
 * Created by caijun.yang on 2017/7/26.
 */
public interface BaseDao<T extends BaseEntity, PK> {

    /**
     * 新增
     *
     * @param t 对象
     * @return
     * @
     */
    int create(T t);

    /**
     * 更新
     *
     * @param t
     * @return
     * @
     */
    int update(T t);

    /**
     * 删除
     *
     * @param id
     * @return
     * @
     */
    int deleteByPrimaryKey(PK id);


    int deleteByEntity(T t);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     * @
     */
    T getByPrimaryKey(PK id);

    /**
     * 分页查询
     *
     * @param qm
     * @return
     * @
     */
    List<T> queryPage(BaseQueryModel qm);

    /**
     * 查询所有
     *
     * @return
     * @
     */
    List<T> findAll();

    /**
     * 删除多个
     */
    int batchDelete(PK[] ids);

    Long count(BaseQueryModel qm);

    List<T> query(BaseQueryModel qm);
}
