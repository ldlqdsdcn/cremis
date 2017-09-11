package cn.cityre.mis.core.entity.query;

/**
 * Created by 刘大磊 on 2017/8/28 17:50.
 * 查询参数基类
 */
public class BaseQuery {
    /**
     * 页码
     */
    private int page;
    /**
     * 查询行数
     */
    private int rows;
    /**
     * 排序方式
     */
    private String order;
    /**
     * 排序字段
     */
    private String sort;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    public int getStartRow()
    {
        return (page-1)*rows;
    }
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
