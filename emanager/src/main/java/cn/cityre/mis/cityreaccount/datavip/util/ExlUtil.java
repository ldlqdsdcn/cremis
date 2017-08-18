package cn.cityre.mis.cityreaccount.datavip.util;

import cn.cityre.mis.cityreaccount.datavip.Excel.ExcelHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;


public class ExlUtil {

	/**
	 * @param excelHeader 表头信息
	 * @param list 要导出到excel的数据源,List类型
	 * @param sheetName 表名
	 * @return
	 */
	public static ResponseEntity<byte[]> getDataStream(ExcelHeader excelHeader, List list, String sheetName){
		LinkedHashMap<String, List> map =  new LinkedHashMap<String, List>();
		map.put(sheetName, list);
		byte[] buffer = null;
		
		try {
			buffer = ExcelUtil.output(excelHeader.getHeadNames(),excelHeader.getFieldNames(),new String[]{sheetName}, map);
		
		} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	    HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    try {
			sheetName=new String(sheetName.getBytes("gbk"),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    String fileGenerateTime = DateUtil.getCurrentTimeForYyyyMMddHHmmssFormat();
	    headers.setContentDispositionFormData("attachment", sheetName+fileGenerateTime+".xls");  
	    return new ResponseEntity<byte[]>(buffer,
                headers, HttpStatus.CREATED);  
	};
	
	
	

	
}


