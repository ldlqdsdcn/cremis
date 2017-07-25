package cn.cityre.edi.mis.base.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.*;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

/** 
 *  实现一个实现ApplicationContextAware和ApplicationListener接口的类DynamicDataSourceC3p0， 
 * 实现ApplicationContextAware是为了得到ApplicationContext， 
 * 实现了ApplicationListener是为了配置spring的加载事件。 
 * 
 */  
public class DynamicCreateDataSourceBean implements ApplicationContextAware,ApplicationListener{  
	private String filename= "db-config.properties";
    private ConfigurableApplicationContext app;  
  
    private DynamicDataSource dynamicDataSource;
      
    public void setDynamicDataSource(DynamicDataSource dynamicDataSource) {
        this.dynamicDataSource = dynamicDataSource;  
    }  
  
    public void setApplicationContext(ApplicationContext app) throws BeansException {  
        this.app = (ConfigurableApplicationContext)app;  
    }  
  
      
    public void onApplicationEvent(ApplicationEvent event) {  
        // 如果是容器刷新事件OR Start Event  
        if (event instanceof ContextRefreshedEvent) {  
            try {  
                regDynamicBean();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            //System.out.println(event.getClass().getSimpleName()+" 事件已发生！");  
        }  
          
    }  
  
    private void regDynamicBean() throws IOException {  
        // 解析属性文件，得到数据源Map  
        Map<String, DataSourceInfo> mapCustom = parsePropertiesFile();  
        // 把数据源bean注册到容器中  
        addSourceBeanToApp(mapCustom);  
    }  
  
    /** 
     * 功能说明：根据DataSource创建bean并注册到容器中 
     *  
     * @param
     * @param mapCustom 
     */  
    private void addSourceBeanToApp(Map<String, DataSourceInfo> mapCustom) {  
        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app  
                .getAutowireCapableBeanFactory();  
  
        //String DATASOURCE_BEAN_CLASS = "org.apache.tomcat.jdbc.pool.DataSource";  
       // BeanDefinitionBuilder bdb;  
          
        Iterator<String> iter = mapCustom.keySet().iterator();  
          
        Map<Object,Object> targetDataSources = new LinkedHashMap<Object, Object>();  
        BeanDefinitionBuilder bdb;
       
     
  
        // 根据数据源得到数据，动态创建数据源bean 并将bean注册到applicationContext中去  
        while (iter.hasNext()) {  
              
            //  bean ID  
            String beanKey = iter.next();  
            //  创建bean  
           // bdb = BeanDefinitionBuilder.rootBeanDefinition(DATASOURCE_BEAN_CLASS);  
            
            
            
            bdb = BeanDefinitionBuilder.rootBeanDefinition(DataSource.class);
            bdb.getBeanDefinition().setAttribute("id", beanKey);
            bdb.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
            bdb.addPropertyValue("url", mapCustom.get(beanKey).connUrl);
            bdb.addPropertyValue("username", mapCustom.get(beanKey).userName);
            bdb.addPropertyValue("password", mapCustom.get(beanKey).password);
            bdb.addPropertyValue("maxActive", 100);
            bdb.addPropertyValue("initialSize", 10);
            bdb.addPropertyValue("removeAbandonedTimeout", 60);
            bdb.addPropertyValue("minEvictableIdleTimeMillis", 30000);
            bdb.addPropertyValue("minIdle", 10);
            bdb.addPropertyValue("maxWait", 10000);
            bdb.addPropertyValue("removeAbandoned", true);
            bdb.addPropertyValue("validationInterval", 30000);
            bdb.addPropertyValue("timeBetweenEvictionRunsMillis", 30000);
            bdb.addPropertyValue("validationQuery", "SELECT 1");
            bdb.addPropertyValue("testOnBorrow", true);
            bdb.addPropertyValue("testOnReturn", true);
            bdb.addPropertyValue("testWhileIdle", true);

            //  注册bean  
            acf.registerBeanDefinition(beanKey, bdb.getBeanDefinition());  
              
            //  放入map中，注意一定是刚才创建bean对象  
//            Object obj = app.getBean(beanKey);
            targetDataSources.put(beanKey, app.getBean(beanKey));  
              
        }  
        //  将创建的map对象set到 targetDataSources；  
        dynamicDataSource.setTargetDataSources(targetDataSources);  
          
        //  必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效  
        dynamicDataSource.afterPropertiesSet();  
          
    }  
  
    /** 
     * 功能说明：GET ALL SM_STATIONS FROM DB1 
     *  
     * @return 
     * @throws IOException 
     */  
    private Map<String, DataSourceInfo> parsePropertiesFile()  
            throws IOException {  
          
         
        Map<String, DataSourceInfo> mds = new HashMap<String, DataSourceInfo>();  
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        InputStream in  = new FileInputStream(path+filename);
//        InputStream in =Enumeration.class.getResourceAsStream(filename);
        Properties p = new Properties();
        p.load(in);
        Iterator<Entry<Object, Object>> it = p.entrySet().iterator();  
        while (it.hasNext()) {  
            Entry<Object, Object> entry = it.next();  
            Object key = entry.getKey();  
            Object value = entry.getValue(); 
//           String[] array = key.toString().split(".");
//           String dbname = "dataSource_"+array[array.length-1];
            String dbname = (String)key;
            DataSourceInfo dsi = new DataSourceInfo();
            String[] sdiArray = value.toString().split("`");
         
            dsi.connUrl=sdiArray[0];
            dsi.userName=sdiArray[1].split("=")[1];
            dsi.password=sdiArray[2].split("=")[1];            
            mds.put(dbname, dsi);
        }  
       
        return mds;  
    }  
  
    //  自定义数据结构  
    private class DataSourceInfo{    
  
        public String connUrl;    
        public String userName;    
        public String password;    
            
        public String toString() {  
            return "(url:" + connUrl + ", username:" + userName + ", password:"  
                + password + ")";  
        }   
    }    
  
}  