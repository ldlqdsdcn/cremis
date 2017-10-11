package cn.cityre.mis.core.dao.config;

import cn.cityre.edi.supporting.db.DynamicDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Created by 刘大磊 on 2017/8/24 10:00. 300多个城市库动态数据源
 */
@Configuration
@MapperScan(basePackages = { "cn.cityre.mis.city.dao" }, sqlSessionFactoryRef = "citySqlSessionFactory")
public class CityDaoConfig {
    private static final Logger log = LoggerFactory.getLogger(CityDaoConfig.class);
    private static final String CITY_DATABASE_CONFIG_FILE = "city.properties";

    @Autowired
    Environment env;

    @Bean(name = { "cityDataSource" }, destroyMethod = "")
    @ConditionalOnJndi("jdbc/dynamiccity")
    public DataSource cityDataSourceJNDI() {
        log.info("Load cityDataSource from JNDI");
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/dynamiccity");
        return dataSource;
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean(name = "cityDataSource")
    public DataSource cityDataSource() {
        String filename = env.getProperty("cities.db.filename", CITY_DATABASE_CONFIG_FILE);
        log.info("Create cityDataSource filename {}", filename);
        return DynamicDataSource.createDataSource(filename);
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
}
