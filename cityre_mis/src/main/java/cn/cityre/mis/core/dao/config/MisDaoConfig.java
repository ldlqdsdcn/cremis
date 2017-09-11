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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Created by 刘大磊 on 2017/8/23 14:10.
 */
@Configuration
@MapperScan(basePackages = {"cn.cityre.mis.sys.dao"}, sqlSessionFactoryRef = "misSqlSessionFactory")
public class MisDaoConfig {
    private static final Logger log = LoggerFactory.getLogger(MisDaoConfig.class);
    @Value("${jdbc.mis.connection.driver_class}")
    private String driverName;
    @Value("${jdbc.mis.connection.url}")
    private String url;
    @Value("${jdbc.mis.connection.username}")
    private String username;
    @Value("${jdbc.mis.connection.password}")
    private String password;
    /**
     * mis 库 jndi
     *
     * @return
     */
    @Bean(name = "misDataSource")
    @ConditionalOnJndi("jdbc/mis")
    @Primary
    public DataSource misDataSourceJNDI() {
        log.info("Load misDataSource from JNDI");
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/mis");
        return dataSource;
    }
    /**
     * mis库 jdbc
     *
     * @return
     */
    @Bean(name = "misDataSource", destroyMethod = "close")
    @Primary
    @ConditionalOnMissingBean(name = "misDataSource")
    public DataSource misDataSource() {
        log.info("Create misDataSource");
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
        dataSource.setInitSQL("SET NAMES 'utf8mb4'");
        return dataSource;
    }

    @Bean(name = "misSqlSessionFactory")
    @Primary
    public SqlSessionFactory supportSqlSessionFactory(@Qualifier("misDataSource") DataSource misDataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(misDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:cn/cityre/mis/sys/mapper/*Mapper.xml"));
        return sqlSessionFactory.getObject();
    }
    /**
     * 配置事务管理器
     */
    @Bean(name = "misTransaction")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("misDataSource") DataSource dateSource) throws Exception {
        return new DataSourceTransactionManager(dateSource);
    }
}
