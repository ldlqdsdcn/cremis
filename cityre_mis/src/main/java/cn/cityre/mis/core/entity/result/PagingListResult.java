package cn.cityre.mis.core.entity.result;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/29 10:49.
 * 返回分页查询
 */
public class PagingListResult<E>
{   //总记录数
    private long total;
    private List<E> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
