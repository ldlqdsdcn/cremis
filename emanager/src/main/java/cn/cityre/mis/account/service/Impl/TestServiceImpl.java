package cn.cityre.mis.account.service.Impl;


import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.account.dao.TestDao;
import cn.cityre.mis.account.service.TestService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by cityre on 2017/7/10.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TestDao testDao;
    @Override
    public String select() {
        DataSourceContextHolder.setDbType("dataSource_center");
        String str_center = testDao.selectCenter();
        DataSourceContextHolder.setDbType("dataSource_bj");
        String str_city = testDao.selectCity();


        DBCollection conn=mongoTemplate.getDb().getCollection("plot");
        BasicDBObject obj = new BasicDBObject();
        obj.put("plot_code", "plot1358479716809990");
        DBCursor cur;
        cur=conn.find(obj);
        String mongo_str = "";
        while  (cur.hasNext()) {
            DBObject curobject=cur.next();
            mongo_str = curobject.get("city_code").toString();

        }



       return str_center +"--"+str_city+"--"+mongo_str;
    }

}
