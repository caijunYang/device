package com.itplayer.core.base.query;


import com.itplayer.core.base.page.PageUtils;

import java.io.Serializable;


/**
 * Created by caijun.yang on 2017/7/25.
 */
public class BaseQueryModel<T> implements Serializable {

    // 当前页码
    private Integer currentPage = 1;
    // 一页显示的条数
    private Integer pageSize = 10;

    private Integer start;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public int getStart() {
        this.start = (this.getCurrentPage() - 1) * this.getPageSize();
        if (this.start < 0) {
            this.start = 0;
        }
        return this.start;
    }

    public void setPage(int page) {
        setCurrentPage(page);
    }

    public void setRows(int rows) {
        setPageSize(rows);
    }
}
