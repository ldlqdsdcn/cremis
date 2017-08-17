package cn.cityre.mis.center.merchant.dao;

import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface CenterUserListDao {
    Date selectRegtimeFromCenterDb(String uid);
}
