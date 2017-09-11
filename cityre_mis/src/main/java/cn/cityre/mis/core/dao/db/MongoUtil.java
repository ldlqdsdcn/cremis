package cn.cityre.mis.core.dao.db;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 刘大磊 on 2017/9/11 8:14.
 */
public class MongoUtil {
    private static ConcurrentHashMap<String, MongoTemplate> mapDbTemplate = null;

    public static void load(ConcurrentHashMap<String, MongoTemplate> mapDbTemplate) {
        if(null == MongoUtil.mapDbTemplate){
            MongoUtil.mapDbTemplate = mapDbTemplate;
        }
    }


    /**
     * 获取tem
     * @param cityCode 城市码
     * @return 返回对应城市的mongdb数据库连接
     */
    public static MongoTemplate get(String cityCode ) {
        return mapDbTemplate.get(cityCode);
    }
}
