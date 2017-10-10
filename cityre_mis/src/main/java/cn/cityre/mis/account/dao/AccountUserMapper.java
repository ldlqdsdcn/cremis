package cn.cityre.mis.account.dao;

import cn.cityre.mis.account.entity.query.AccountUserQuery;
import cn.cityre.mis.account.model.AccountUser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AccountUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountUser record);

    int insertSelective(AccountUser record);

    AccountUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountUser record);

    int updateByPrimaryKey(AccountUser record);

    AccountUser selectByUnionUid(@Param("value") String unionUid);
    List<AccountUser> selectList(AccountUserQuery param);

    Page<AccountUser> selectList(AccountUserQuery param, RowBounds rowBounds);


}