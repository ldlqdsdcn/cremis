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
 * Created by 刘大磊 on 2017/8/23 14:10.
 * 中心库数据源设置
 */
@Configuration
@MapperScan(basePackages = {"cn.cityre.mis.center.dao"}, sqlSessionFactoryRef = "centerSqlSessionFactory")
public class CenterDaoConfig {
    private static final Logger log = LoggerFactory.getLogger(MisDaoConfig.class);
    @Value("${jdbc.center.connection.driver_class}")
    private String driverName;
    @Value("${jdbc.center.connection.url}")
    private String url;
    @Value("${jdbc.center.connection.username}")
    private String username;
    @Value("${jdbc.center.connection.password}")
    private String password;

    /**
     * 中心账户库jndi
     *
     * @return
     */
    @Bean(name = "centerDataSource")
    @ConditionalOnJndi("jdbc/center")
    public DataSource centerDataSourceJNDI() {
        log.info("Load accountDataSource from JNDI");
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/center");
        return dataSource;
    }

    /**
     * 中心账户库 jdbc
     *
     * @return
     */
    @Bean(name = "centerDataSource", destroyMethod = "close")
    @ConditionalOnMissingBean(name = "centerDataSource")
    public DataSource centerDataSource() {
        log.info("Create centerDataSource");
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
    @Bean(name = "centerSqlSessionFactory")
    public SqlSessionFactory centerSqlSessionFactory(@Qualifier("centerDataSource") DataSource accountDataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(accountDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:cn/cityre/mis/center/mapper/*Mapper.xml"));
        return sqlSessionFactory.getObject();
    }
}
