package com.dsdl.eidea.core.dto;

import com.dsdl.eidea.util.Page;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Getter
public class EasyUIResult<T> implements Serializable {
    private int page = 1; // 当前页号 currentPage->当前第几页，从1开始: page
    private int limit = 20; // 每页显示记录数numPerPage ->每页显示数: limit
    private int total = 0; // 总记录数
    private int start = 0; //记录开始
    /**
     * 必须是list
     */
    private List<T> rows;

    private EasyUIResult() {

    }

    public static <T> EasyUIResult<T> pagination(List<T> data) {
        return pagination(data);
    }


    public static <T> EasyUIResult<T> pagination(List<T> data, Page pager) {
        EasyUIResult<T> paginationResult = new EasyUIResult<>();
        paginationResult.page = pager.getPage();
        paginationResult.total = pager.getTotalCount();
        paginationResult.limit=pager.getLimit();
        paginationResult.start = pager.getStart();
        paginationResult.rows = data;
        return paginationResult;
    }
}
