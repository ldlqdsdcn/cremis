package cn.cityre.mis.core.dao.db;

import cn.cityre.mis.util.FileUtil;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 刘大磊 on 2017/9/11 8:01.
 */
@Component
public class MongoDataSource {
    private static final Logger log = LoggerFactory.getLogger(MongoDataSource.class);

    @PostConstruct
    public void init() {

        // step1:加载配置文件
        Properties cityDsMap = FileUtil.loadPropertiesFormClasspathFile("mongocity.properties");
        log.info("load mongo db:{}", cityDsMap.size());
        ConcurrentHashMap<String, MongoTemplate> mapDbTemplate = new ConcurrentHashMap<>();
        for (Object ds : cityDsMap.keySet()) {
            String key=(String)ds;
            String cityCode = key.replaceAll("fyt", "").replaceAll(".url", "");
            String dsUrl = cityDsMap.getProperty(key);
            // step2:获取配置
            // fytbaisha.url=192.168.3.48|port=17018|dbname=fyt_stat_baisha|password=mongo
            String[] url = dsUrl.split("\\|");
            //System.out.println();
            String host = url[0];
            String port = url[1].replaceAll("port=", "");
            String db = url[2].replaceAll("dbname=", "");
            log.info("load mongo dsUrl:{},{},{}", host, port, db);
            // step3:加载mongoClient
            try {
                MongoClient mongoClient = new MongoClient(host, Integer.parseInt(port));
                MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, db);
                mapDbTemplate.put(cityCode, mongoTemplate);
                log.info("load mongo success:{},{}", cityCode, dsUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MongoUtil.load(mapDbTemplate);
    }
}
