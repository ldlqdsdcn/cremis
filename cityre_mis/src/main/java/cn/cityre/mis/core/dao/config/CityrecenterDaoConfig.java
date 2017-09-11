package cn.cityre.mis.core.dao.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Created by 刘大磊 on 2017/8/23 14:11.
 * 城市中心库数据源mybatis配置
 */
@Configuration
@MapperScan(basePackages = {"cn.cityre.mis.cityre_center.dao"}, sqlSessionFactoryRef = "cityre_centerSqlSessionFactory")
public class CityrecenterDaoConfig {
    private static final Logger log = LoggerFactory.getLogger(CityrecenterDaoConfig.class);
    @Value("${jdbc.cityre_center.connection.driver_class}")
    private String driverName;
    @Value("${jdbc.cityre_center.connection.url}")
    private String url;
    @Value("${jdbc.cityre_center.connection.username}")
    private String username;
    @Value("${jdbc.cityre_center.connection.password}")
    private String password;

    /**
     * 中心账户库jndi
     *
     * @return
     */
    @Bean(name = "cityre_centerDataSource")
    @ConditionalOnJndi("jdbc/cityre_center")
    public DataSource cityre_centerDataSourceJNDI() {
        log.info("Load cityre_centerDataSource from JNDI");
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/cityre_center");
        return dataSource;
    }

    /**
     * 中心账户库 jdbc
     *
     * @return
     */
    @Bean(name = "cityre_centerDataSource", destroyMethod = "close")
    @ConditionalOnMissingBean(name = "cityre_centerDataSource")
    public DataSource cityre_centerDataSource() {
        log.info("Create cityre_centerDataSource");
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(driverName);
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
        dataSource.setInitSQL("SET NAMES 'utf8'");
        return dataSource;
    }
    @Bean(name = "cityre_centerSqlSessionFactory")
    public SqlSessionFactory cityre_centerSqlSessionFactory(@Qualifier("cityre_centerDataSource") DataSource accountDataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(accountDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:cn/cityre/mis/cityre_center/mapper/*Mapper.xml"));
        return sqlSessionFactory.getObject();
    }
}
