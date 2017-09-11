package cn.cityre.mis.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 刘大磊 on 2017/9/11 8:09.
 */
public class FileUtil {
    public static Properties loadPropertiesFormClasspathFile(String fileName) {
        Resource fileResource = new ClassPathResource(fileName);
        Properties properties = new Properties();
        try {
            InputStream inputStream = fileResource.getInputStream();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("读取属性文件"+fileName+"发生错误", e);
        }
        return properties;
    }
}
