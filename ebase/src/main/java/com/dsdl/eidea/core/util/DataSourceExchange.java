package com.dsdl.eidea.core.util;

import org.aspectj.lang.JoinPoint;

/**
 * Created by cityre on 2017/7/24.
 */
public class DataSourceExchange {

    public void before(JoinPoint point) {

        //获取目标对象的类类型
        Class<?> classType = point.getTarget().getClass();

        //获取包名用于区分不同数据源
        String whichDataSource = classType.getName();
        if ("dataSource".equals(whichDataSource)) {
            DataSourceHolder.setDataSources("dataSourceCore");
        } else {
            DataSourceHolder.setDataSources("dataSourceAccount");
        }

    }


    /**
     * 执行后将数据源置为空
     */
    public void after() {
        DataSourceHolder.setDataSources(null);
    }

}
