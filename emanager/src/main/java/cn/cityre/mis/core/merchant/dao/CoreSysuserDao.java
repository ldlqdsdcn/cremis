package cn.cityre.mis.core.merchant.dao;


import org.springframework.stereotype.Repository;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface CoreSysuserDao {
    String selectNameFromUser(String uid);
}
