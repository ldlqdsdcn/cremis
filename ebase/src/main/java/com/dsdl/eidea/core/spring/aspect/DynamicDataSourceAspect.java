package com.dsdl.eidea.core.spring.aspect;

import com.dsdl.eidea.core.spring.annotation.DataSourceName;
import com.dsdl.eidea.core.util.DataSourceHolder;
import com.dsdl.eidea.core.util.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by cityre on 2017/7/25.
 */
@Aspect
@Order(1)
@Component
public class DynamicDataSourceAspect {
    @Before(value = "@annotation(com.dsdl.eidea.core.spring.annotation.DataSourceName)")
    public void testBefore(JoinPoint joinPoint){
        //获得当前访问的类
        Class<?> className= joinPoint.getTarget().getClass();

        DataSourceName dataSourceName = className.getAnnotation(DataSourceName.class);

        if (dataSourceName!=null){
            //获得访问的方法名
            String methodName = joinPoint.getSignature().getName();
            Class[] argClass = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
            String dataSource= "dataSourceCore";
            try{
                Method method=className.getMethod(methodName,argClass);
                if (method.isAnnotationPresent(DataSourceName.class)){
                    DataSourceName annotation = method.getAnnotation(DataSourceName.class);
                    dataSource = annotation.dataSourceName();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            DataSourceHolder.setDataSourceType(dataSource);
        }

    }
}
