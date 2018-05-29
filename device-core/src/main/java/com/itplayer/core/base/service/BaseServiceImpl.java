package com.itplayer.core.base.service;


import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.base.entity.BaseEntity;
import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.page.PageUtils;
import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.base.validate.ValidateUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by caijun.yang on 2017/4/1.
 */
@SuppressWarnings("all")
public abstract class BaseServiceImpl<T extends BaseEntity, PK> implements BaseService<T, PK> {

    protected BaseDao dao = null;

    protected void setDao(BaseDao dao) {
        this.dao = dao;
    }


    @Transactional(propagation = Propagation.NESTED)
    public int create(T t) {
        t.setCreateDate(new Date());
        ValidateUtils.validate(t);
        return dao.create(t);
    }

    @Transactional(propagation = Propagation.NESTED)
    public int update(T t) {
        t.setUpdateDate(new Date());
//        ValidateUtils.validate(t);
        int row = dao.update(t);
        return row;
    }

    public int deleteByPrimaryKey(PK id) {

        if (null == id) {
            throw new SystemException("删除失败，数据编号不存在！");
        }
        return dao.deleteByPrimaryKey(id);

    }


    public int deleteByEntity(T t) {
        return dao.deleteByEntity(t);
    }

    public T getByPrimaryKey(PK id) {
        return (T) dao.getByPrimaryKey(id);
    }

    public PageResult<T> queryPage(BaseQueryModel qm) {
        if (null == qm) {
            qm = new BaseQueryModel();
        }
        Long count = dao.count(qm);
        if (0 == count) {
            return new PageResult<T>();
        }
        final PageResult<T> pageResult = new PageResult<T>(count.intValue(), qm.getCurrentPage(),
                qm.getPageSize());
        qm.setCurrentPage(pageResult.getCurrentPage());

        List<T> list = dao.query(qm);
        pageResult.setRows(list);
        return pageResult;
    }

    public int batchDelete(PK[] ids) {
        if (ids == null || ids.length < 1) {
            throw new SystemException("请选择要删除的数据！");
        }
        return dao.batchDelete(ids);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public List<T> findByEntity(T t) {
        return dao.findByEntity(t);
    }

}
