package com.dsdl.eidea.core.web.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class Page {

    // 查询条件集合
    protected List whereList;
    private int page = 1; // 当前页号 currentPage->当前第几页，从1开始: page
    private int limit = 10; // 每页显示记录数numPerPage ->每页显示数: limit
    private int totalCount = 0; // 总记录数
    private int start = 0; //记录开始
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    // 排序字符串
    private String sort;


    public Page(String etTableId, HttpServletRequest request) throws Exception {
        try {
            this.request = request;
            if (request.getParameter("limit") != null)
                this.setLimit(Integer.parseInt(request.getParameter("limit")));
            if (request.getParameter("start") != null)
                this.setStart(Integer.parseInt(request.getParameter("start")));
            if (request.getParameter("page") != null)
                this.setPage(Integer.parseInt(request.getParameter("page")));
            if (request.getParameter("rows") != null) {
                this.setLimit(Integer.parseInt(request.getParameter("rows")));
                Integer rows = Integer.parseInt(request.getParameter("rows"));
                Integer page = Integer.parseInt(request.getParameter("page"));
                this.setStart((page - 1) * rows);
            }

        } catch (Exception e) {
            // 100007 设置分页信息错误
            System.out.println(e);
        }
    }

    public Page(String etTableId) {
    }

    /**
     * 手机端分页查询
     *
     * @param limit
     * @param start
     * @param page
     * @throws
     */
    public Page(String limit, String start, String page) throws Exception {
        try {

            if (StringUtils.isNotBlank(limit))
                this.setLimit(Integer.parseInt(limit));
            if (StringUtils.isNotBlank(start))
                this.setStart(Integer.parseInt(start));
            if (StringUtils.isNotBlank(page))
                this.setPage(Integer.parseInt(page));

        } catch (Exception e) {
            throw new Exception();
        }

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @SuppressWarnings("unchecked")
    public List getWhereList() {
        return whereList;
    }

    @SuppressWarnings("unchecked")
    public void setWhereList(List whereList) {
        this.whereList = whereList;
    }

    /**
     * @return 返回查询的条件List集合
     */
    @SuppressWarnings("unchecked")
    public List getWhere() {
        return whereList;
    }

    /**
     * @param where 作为条件查询的条件串
     */
    @SuppressWarnings("unchecked")
    public void setWhere(String where) {
        if (StringUtils.isBlank(where) || StringUtils.isEmpty(where)) {
            return;
        }
        if (null == whereList) {
            whereList = new ArrayList();
        }
        whereList.add(where);

    }

    public void pushSort(String sort){
        if(StringUtils.isNotBlank(this.getSort())){
            this.setSort(this.getSort()+" , "+sort);
        }else{
            this.setSort(sort);
        }
    }
}

