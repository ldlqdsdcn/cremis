package cn.cityre.mis.core.city.dao.jdbc;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 刘大磊 on 2017/7/6 16:53.
 */
public class JdbcConnection {
    private static Map<String,String> cityMap=new HashMap<>();
    private static Properties cityListProperties;
    static {
        cityMap.put("370200","qingdao");
        cityMap.put("370100","jinan");
        cityMap.put("370700","weifang");
        cityMap.put("350200","xiamen");
        Resource fileRource = new ClassPathResource("city_database.properties");
        cityListProperties=new Properties();
        try {
            cityListProperties.load(fileRource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(String cityid)
    {
        String cityValue=cityMap.get(cityid);
        String databaseDriverKey="jdbc."+cityValue+".connection.driver_class";
        String databaseDriver=cityListProperties.getProperty(databaseDriverKey);
        String urlKey="jdbc."+cityValue+".connection.url";
        String url=cityListProperties.getProperty(urlKey);
        String usernameKey="jdbc."+cityValue+".connection.username";
        String username=cityListProperties.getProperty(usernameKey);
        String passwordKey="jdbc."+cityValue+".connection.password";
        String password=cityListProperties.getProperty(passwordKey);
        //com.mysql.jdbc.Driver
        try {
            Class.forName(databaseDriver);
            Connection conn=DriverManager.getConnection(url,username,password);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
