package com.manhattan.java.minproject.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href="mailto:zhuangxuenan.com">庄学南</a>
 * @version V0.0.1
 * @date 2020/3/18
 */

@Data
public class PageParam<T> implements Serializable {

    private static final long serialVersionUID = -7248374800878487522L;
    /**
     * <p>当前页</p>
     */
    private int pageNum = 1;
    /**
     * <p>每页记录数</p>
     */
    private int pageSize = 10;
    /**
     * <p>分页外的其它参数</p>
     */
    private T param;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize >= 500 ? 500 : pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum <= 0 ? 1 : pageNum;
    }
}
