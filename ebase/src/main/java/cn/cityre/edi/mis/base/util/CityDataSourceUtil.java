package cn.cityre.edi.mis.base.util;

/**
 * 获取城市库链接切换
 *
 * @author 2017-5-31
 */
public class CityDataSourceUtil {
/*   private static String read;*/
   /*private static String write;*/
  private static  String dbname;
 /*   public static String getRead(String cityCode) {
    	read = "dataSource_"+cityCode;
        return read;
    }*/
  /*  public static String getWrite(String cityCode) {
    	write = "dataSource_"+cityCode;
        return write;
    }*/
    public static String changeDB(String cityCode) {
        dbname = "dataSource_" + cityCode;
        return dbname;
    }
}