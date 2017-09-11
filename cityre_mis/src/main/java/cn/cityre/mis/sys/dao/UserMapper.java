package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.model.User;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.pagination.dto.PageMyBatis;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据条件查询用户记录
     * @param param
     * @return
     */
    User selectByExample(Map<String, Object> param);

    List<User> selectList(Map<String, Object> param);

    /**
     * 分页查询
     *
     * @param param
     * @param rowBounds 查找范围
     * @return
     */
    PageMyBatis<User> selectList(Map<String, Object> param, RowBounds rowBounds);

}