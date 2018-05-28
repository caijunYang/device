package com.itplayer.core.base.page;

import java.util.Collections;
import java.util.List;

/**
 * @param <E>
 * @author create by caijun.yang on 2017/727/24. 分页对象
 */
public class PageUtils<E extends Object> implements java.io.Serializable {
    private static final long serialVersionUID = 3201523020686506768L;

    /**
     * 每页条数Rows
     */
    private int pageSize = 10;
    /**
     * 当前页page
     */
    private int currentPage = 1;
    /**
     * 数据集合
     */
    private List<E> rows = Collections.emptyList();

    private int totalCount = 0;

    private int start;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            return 1;
        }
        if (currentPage > getTotalPage()) {
            return getTotalPage();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        int totalPage1 = (int) Math.ceil(totalCount * 1.0 / pageSize);
        if (totalPage1 == 0) {
            totalPage1 = 1;
        }
        return totalPage1;
    }


    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        this.start = (this.getCurrentPage() - 1) * this.getPageSize();
        if (this.start < 0) {
            this.start = 0;
        }

        return this.start;
    }
}
