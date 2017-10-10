package cn.cityre.mis.core.dao.db;

/**
 * Created by 刘大磊 on 2017/8/23 18:07.
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getDbType() {
        return ((String) contextHolder.get());
    }

    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
