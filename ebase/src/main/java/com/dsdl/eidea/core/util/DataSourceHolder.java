package com.dsdl.eidea.core.util;

/**
 * Created by cityre on 2017/7/24.
 */
public class DataSourceHolder {
    private static final ThreadLocal<String>  contextHolder = new ThreadLocal<String>();//获取本地的线程环境
    //设置数据源
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    //    获取数据源
    public static String getDataSourceType(){
        System.out.println("目前链接的数据库是："+contextHolder.get());
        return contextHolder.get();}
}
