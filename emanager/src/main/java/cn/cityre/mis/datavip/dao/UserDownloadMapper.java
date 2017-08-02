package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserDownload;

public interface UserDownloadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDownload record);

    int insertSelective(UserDownload record);

    UserDownload selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDownload record);

    int updateByPrimaryKey(UserDownload record);
}