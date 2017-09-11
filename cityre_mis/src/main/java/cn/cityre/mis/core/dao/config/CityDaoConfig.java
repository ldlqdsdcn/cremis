package cn.cityre.mis.core.dao.config;

import cn.cityre.mis.core.dao.db.DynamicDataSource;
import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by 刘大磊 on 2017/8/24 10:00.
 * 300多个城市库动态数据源
 */
@Configuration
@MapperScan(basePackages = {"cn.cityre.mis.city.dao"}, sqlSessionFactoryRef = "citySqlSessionFactory")
public class CityDaoConfig {
    private static final Logger log = LoggerFactory.getLogger(CityDaoConfig.class);
    private static final String CITY_DATABASE_CONFIG_FILE = "city.properties";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    @Bean(name = {"cityDataSource"})
    @ConditionalOnJndi("jdbc/dynamiccity-readonly")
    public DataSource cityDataSourceJNDI() {
        log.info("Load cityDataSource from JNDI");
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/dynamiccity-readonly");
        return dataSource;
    }

    @Bean(name = "cityDataSource")
    @ConditionalOnMissingBean(name = "cityDataSource")
    public DataSource cityDataSource() {
        log.info("------------创建 dynamic datasource");
        Resource fileResource = new ClassPathResource(CITY_DATABASE_CONFIG_FILE);
        Properties properties = new Properties();
        try {
            InputStream inputStream = fileResource.getInputStream();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("读取城市数据源配置文件发生错误", e);
        }
        Set keys = properties.keySet();
        Map<Object, Object> targetDataSources = new LinkedHashMap<>();
        for (Object keyObj : keys) {
            String key = keyObj.toString();
            Object value = properties.getProperty(key);
            String[] dbArray = key.split("\\.");
            String dbname = dbArray[0];
            String[] sdiArray = value.toString().split("\\|");
            String url = sdiArray[0];
            String usernameExpression = sdiArray[1];
            String passwordExpression = sdiArray[2];
            String userName = usernameExpression.split("=")[1];
            String password = passwordExpression.split("=")[1];
            DataSource dataSource = createDataSource(url, userName, password);
            targetDataSources.put(dbname, dataSource);
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }
    @Bean(name = "citySqlSessionFactory")
    public SqlSessionFactory citySqlSessionFactory(@Qualifier("cityDataSource") DataSource cityDataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(cityDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:cn/cityre/mis/city/mapper/*Mapper.xml"));
        return sqlSessionFactory.getObject();
    }
    private DataSource createDataSource(String url, String username, String password) {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(DRIVER_CLASS);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setIdleConnectionTestPeriodInMinutes(60);
        dataSource.setIdleMaxAgeInMinutes(240);
        dataSource.setMaxConnectionsPerPartition(10);
        dataSource.setMinConnectionsPerPartition(1);
        dataSource.setPartitionCount(2);
        dataSource.setAcquireIncrement(5);
        dataSource.setStatementsCacheSize(100);
        dataSource.setInitSQL("SELECT 1");
        return dataSource;
    }
}
