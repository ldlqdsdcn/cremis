package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserDownloadHistory;

public interface UserDownloadHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDownloadHistory record);

    int insertSelective(UserDownloadHistory record);

    UserDownloadHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDownloadHistory record);

    int updateByPrimaryKey(UserDownloadHistory record);
}