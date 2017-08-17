package cn.cityre.mis.datavip.Excel;

import java.util.List;

public interface ExcelHeader{

	/**获得导出的exl的表头集合
	 * @return
	 */
	public  List<String []> getHeadNames();
		
	/**获得导出的exl的表头所对应的bean属性集合
	 * @return
	 */
	public List<String []> getFieldNames();
	
	
}
