<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.cityre.edi.mis.base.entity.cpo.CityPo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	CityPo cityPo =(CityPo)session.getAttribute("currentCity");
	String citycode = cityPo.getCityid();
%>
<html>
<head>
	<script src="../../jQuery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../../jQuery/jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="../../jQuery/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="../../jQuery/date.format.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="../../jQuery/jquery-easyui-1.5/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="../../jQuery/jquery-easyui-1.5/themes/default/easyui.css" />

<script>
//检索
function finduserApplyCheckHouseSeachFn() {
	
    var phoneStr = $("#phone_ids").val();
    var startDateStr = $("#startDate_ids").datebox("getValue");
    var endDateStr = $("#endDate_ids").datebox("getValue");
    var statusStr = $("#status_ids").combobox("getValue");
    
    $("#userApplyCheckHouseList_ids").datagrid({
        queryParams: {
	       	phone: phoneStr,
	     	startDate: startDateStr,
	        endDate:endDateStr,
	        status:statusStr
        }
    });
}
function finduserApplyCheckHouseReload() {

    var phoneStr = $("#phone_ids").val();
    var startDateStr = $("#startDate_ids").datebox("getValue");
    var endDateStr = $("#endDate_ids").datebox("getValue");
    var statusStr = $("#status_ids").combobox("getValue");

    $("#userApplyCheckHouseList_ids").datagrid({
        queryParams: {
            phone: phoneStr,
            startDate: startDateStr,
            endDate:endDateStr,
            status:statusStr
        }
    });
}
function aboutOperation(rows,index){
	 var html = "";
	 html += "<a href='javascript:void(0);' onclick='acceptanceUrlFn("+rows.id+","+rows.status+","+index+")'><font color='green'>受理</font></a>&nbsp;&nbsp;|";
	 html += "&nbsp;&nbsp;<a href='javascript:void(0);' onclick='addHouseInfoUrlFn("+index+")'><font color='blue'>补充房产源信息</font></a>";
     return html;
}

function getHistory(valueId,valueStatus,index){
    var url='<%=path%>' + "/base/userApplyForHouse/historyList.jsp?id="+valueId;
	 var html = "";
	 html += "<a href='"+url+"' target='_blank'><font color='blue'>查看历史</font></a>";
     return html;
}
function getHouseAddress(address,dealCode){
    var cityCode ='<%=citycode%>';
	 var html = "";
 	 html += "<a href='http://"+cityCode+".cityhouse.cn/forsale/"+dealCode+".html' target='_blank'><font color='blue'>"+address+"</font></a>"  ;   
	
     return html;
}
function acceptanceUrlFn(id,status,index){
	var rows = $('#userApplyCheckHouseList_ids').datagrid('getRows');
	var obj = rows[index];
	if(status == '0' || status == '1'){
		alert("此房源还未签约，请联系社区中介进行签约操作");
	}else if(status == '2'){
		alert("请联系社区银行填写贷款结果！");
	}else if( status == '5'||status == '6'){
		$("#id_dig").val(id);
		$("#status_dig").val(status);		
		$("#bank_dig").val("");
		$("#amount_dig").val("");
		$("#date_dig").datebox('setValue', '');
		$("#bank_dig").attr("readonly",false);
		$("#prebank_dig").val(obj.bankName);
		$("#preamount_dig").val(obj.loanAmount);
		$("#predate_dig").val(obj.loanDate);
		$("#premisname_dig").val(obj.misUserName);
		$('#acceptHouseLoanDig').window('open');
	}else if(status == '4'){
		$("#prebank_dig").val(obj.bankName);
		$("#preamount_dig").val(obj.loanAmount);
		$("#predate_dig").val(obj.loanDate);
		$("#premisname_dig").val(obj.misUserName);
		$("#id_dig").val(id);
		$("#status_dig").val(status);
		if(obj.bankName != null && obj.bankName != ""){
			$("#bank_dig").val(obj.bankName);
			$("#bank_dig").attr("readonly",true);
		}else{
			$("#bank_dig").val("");
			$("#bank_dig").attr("readonly",false);
		}				
		$("#amount_dig").val("");
		$("#date_dig").datebox('setValue', '');
		$('#acceptHouseLoanDig').window('open');
	}else if(status == '3'){
		alert("此房源已全款支付，您不能填写放款信息");
	}else if(status == '7'){
		alert("交易已关闭，您不能填写放款信息");
	}
}
function closeAcceptHouseLoanDig(){
	$("#id_dig").val("");
	$("#status_dig").val("");
	$("#bank_dig").val("");
	$("#amount_dig").val("");
	$('#acceptHouseLoanDig').window('close');
}
function addHouseInfoUrlFn(index){
	var rows = $('#userApplyCheckHouseList_ids').datagrid('getRows');
	var obj = rows[index];
	$("#houseInfoTable").html("");
	var htmlStr = "";
	
	if(obj.district != null && obj.district != "" &&
        obj.street != null&& obj.street != "" &&
        obj.stno != null&& obj.stno != "" &&
    	obj.ha != null&& obj.ha != "" &&
    	obj.bldgno != null&& obj.bldgno != "" &&
    	obj.unit != null && obj.unit != "" &&
    	obj.roomno != null&& obj.roomno != ""){
		return;
	}
	
	
	if(obj.district==null || obj.district == ''){
		htmlStr = htmlStr + "<tr><td><label>行政区:</label></td><td> <input id = 'district_dig' style='height:30px;width:300px;'class='easyui-textbox' maxlength='50' /></td></tr>";
	}else{
		htmlStr = htmlStr + "<tr><td><label>行政区:</label></td><td> <input id = 'district_dig' style='height:30px;width:300px;' value='"+obj.district+"' class='easyui-textbox' readonly='readonly'/></td></tr>";
	}
	if(obj.street==null ||obj.street == ''){
		htmlStr = htmlStr + "<tr><td><label>街道:</label></td><td> <input id = 'street_dig' style='height:30px;width:300px;'class='easyui-textbox' maxlength='20'/></td></tr>";
	}else{
		htmlStr = htmlStr + "<tr><td><label>街道:</label></td><td> <input id = 'street_dig' style='height:30px;width:300px;' value='"+obj.street+"' class='easyui-textbox' readonly='readonly'/></td></tr>";
	}
	if(obj.stno==null ||obj.stno == ''){
		htmlStr = htmlStr + "<tr><td><label>门牌号:</label></td><td> <input id = 'stno_dig' style='height:30px;width:300px;'class='easyui-textbox' maxlength='10'/></td></tr>";
	}else {
		htmlStr = htmlStr + "<tr><td><label>门牌号:</label></td><td> <input id = 'stno_dig' style='height:30px;width:300px;' value='"+obj.stno+"' class='easyui-textbox' readonly='readonly'/></td></tr>";
	}
	if(obj.ha==null ||obj.ha == ''){
		htmlStr = htmlStr + "<tr><td><label>小区:</label></td><td> <input id = 'ha_dig' style='height:30px;width:300px;'class='easyui-textbox' maxlength='50'/></td></tr>";
	}else {
		htmlStr = htmlStr + "<tr><td><label>小区:</label></td><td> <input id = 'ha_dig' style='height:30px;width:300px;' value='"+obj.ha+"' class='easyui-textbox' readonly='readonly'/></td></tr>";
	}
	if(obj.bldgno==null ||obj.bldgno == ''){
		htmlStr = htmlStr + "<tr><td>详细地址：</td><td > <input id = 'bldgno_dig' style='height:30px;width:60px;'class='easyui-textbox' maxlength='10'/>栋";
	}else {
		htmlStr = htmlStr + "<tr><td>详细地址：</td><td > <input id = 'bldgno_dig' style='height:30px;width:60px;' value='"+obj.bldgno+"' class='easyui-textbox' readonly='readonly'/>栋";
	}
	if(obj.unit==null ||obj.unit == ''){
		htmlStr = htmlStr + " <input id = 'unit_dig' style='height:30px;width:60px;'class='easyui-textbox' maxlength='10'/>单元";
	}else {
		htmlStr = htmlStr + " <input id = 'unit_dig' style='height:30px;width:60px;' value='"+obj.unit+"' class='easyui-textbox' readonly='readonly'/>单元";
	}
	if(obj.roomno==null ||obj.roomno == ''){
		htmlStr = htmlStr + " <input id = 'roomno_dig' style='height:30px;width:60px;'class='easyui-textbox' maxlength='10'/>室</td></tr>";
	}else {
		htmlStr = htmlStr + " <input id = 'roomno_dig' style='height:30px;width:60px;' value='"+obj.roomno+"' class='easyui-textbox' readonly='readonly'/>室</td></tr>";
	}	
		htmlStr = htmlStr +"<input id='houseInfo_id' type= 'hidden'  value ='"+obj.id+"'/>";
		$("#houseInfoTable").html(htmlStr);
		$('#houseInfoDig').window('open');
}
function closeHouseInfoDig(){
		$('#houseInfoDig').window('close');
}
function acceptHouseLoanBtnClick(){
	var bankname = $("#bank_dig").val();
	var amount = $("#amount_dig").val();
	var date = $("#date_dig").datebox('getValue');
	var id = $("#id_dig").val();
	var status=$("#status_dig").val();
	var preBankName=$("#prebank_dig").val();
	var preAmount = $("#preamount_dig").val();
	var preDate = $("#predate_dig").val();
	var preMisName = $("#premisname_dig").val();
	var url='<%=path%>' + "/mis/merchant/userApplyForHouse/addBankInfo";
	$.post(url,{bankname:bankname,amount:amount,date:date,id:id,status:status,preBankName:preBankName,preAmount:preAmount,preDate:preDate,preMisName:preMisName},function(data){
		if(data.result == 'success'){
			
			$("#id_dig").val("");
			$("#status_dig").val("");
			$('#acceptHouseLoanDig').window('close');
            finduserApplyCheckHouseReload();
			alert("操作成功");
			
		}
	});
}
function houseInfoBtnClick(){
	var id = $("#houseInfo_id").val();
	var district_dig = $("#district_dig").val();
	var street_dig = $("#street_dig").val();
	var stno_dig = $("#stno_dig").val();
	var ha_dig = $("#ha_dig").val();
	var bldgno_dig = $("#bldgno_dig").val();
	var unit_dig = $("#unit_dig").val();
	var roomno_dig = $("#roomno_dig").val();
	var url='<%=path%>' + "/mis/merchant/userApplyForHouse/addHouseInfo";
	$.post(url,{id:id,district_dig:district_dig,street_dig:street_dig,stno_dig:stno_dig,ha_dig:ha_dig,bldgno_dig:bldgno_dig,unit_dig:unit_dig,roomno_dig:roomno_dig},function(data){
		if(data.result == 'success'){
			
			$("#houseInfo_id").val("");
			$('#houseInfoDig').window('close');
            finduserApplyCheckHouseReload();
			alert("操作成功");
			
		}
	});
}
</script>

<title>客户看房列表</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
<!-- 工具栏 -->
    <div data-options="region:'north',border:false" style="height:70px;overflow:hidden;background:#fafafa;">
       <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:65px" 
            data-options="border:false,iconCls:'icon-save'">
           	<fieldset class="fieldset" style="height: 45px;">
            <legend class="legend">查询条件</legend>
            <label>&nbsp;&nbsp;&nbsp;申请时间:</label>
            <input id="startDate_ids" style="width:150px;" class="easyui-datebox" />
            -
            <input id="endDate_ids" style="width:150px;" class="easyui-datebox" />
            <label>&nbsp;&nbsp;&nbsp;申请人电话:</label>
            <input id="phone_ids" style="width:150px;height:23px;" class="easyui-textbox" />
            <label>&nbsp;&nbsp;&nbsp;状态:</label>
            <input id="status_ids" class="easyui-combobox" style="width:150px;height:23px"
                   data-options="valueField: 'value',textField: 'label',
				data: [{label:'请选择',value:'-1',selected:true},
						{label:'看房中',value:'0'},
						{label:'未签约',value:'1'},
						{label:'已签约',value:'2'},
						{label:'已签约(全款)',value:'3'},
						{label:'发放贷款',value:'4'},
						{label:'拒绝贷款',value:'5'},
						{label:'其它银行放款',value:'6'},
						{label:'关闭交易',value:'7'}]"/>           
                       &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="seach_qd_contract_ids" onclick="finduserApplyCheckHouseSeachFn()" class="easyui-linkbutton"
               data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;

     		</fieldset>
       </div>
    </div>
    <div data-options="region:'center',border:false" >
        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true" 
        		rownumbers="true" fitColumns="true" border="false" data-options="singleSelect:true,nowrap:false,pageSize:50,pageList:[50,100,150,200]"
               url="<%=path %>/mis/merchant/userApplyForHouse/list"
               id="userApplyCheckHouseList_ids" 
               style="overflow:auto;width:auto;height:880px ">
            <thead>
            <tr>
                <th data-options="field:'bookTime',sortable:true,
                formatter: function(value,row,index){
			                      if(null!=row.bookTime&&row.bookTime!=''){
			                      	return dateFormat(row.bookTime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="15px">申请时间</th>
                <th data-options="field:'customerName',sortable:true" width="15px">申请人姓名</th>
                <th data-options="field:'customerTel',sortable:true" width="15px">申请电话</th>
                <th data-options="field:'address',sortable:true,
                			formatter: function(value,row,index){
                                  return getHouseAddress(row.address,row.dealCode);
			                }" width="15px">地址</th>
                <th data-options="field:'price',sortable:true,formatter: function(value,row,index){ 
			                   if(row.price != null){
			                   	return row.price+'万元';
			                   }
			                 }" width="15px">价格</th>
                <th data-options="field:'bldgArea',sortable:true,formatter: function(value,row,index){
                				if(row.bldgArea != null){
                					return row.bldgArea+'平';
                				} 			                   
			                      	
			                 }" width="15px">面积</th>
                <th data-options="field:'status',sortable:true,formatter: function(value,row,index){ 			                   
			                      if(row.status != null){
			                      	if(row.status == '0'){
			                      		return '看房中';
			                      	}
			                      	if(row.status == '1'){
			                      		return '未签约';
			                      	}
			                      	if(row.status == '2'){
			                      		return '已签约';
			                      	}
			                      	if(row.status == '3'){
			                      		return '已签约(全款)';
			                      	}
			                      	if(row.status == '4'){
			                      		return '发放贷款';
			                      	}
			                      	if(row.status == '5'){
			                      		return '拒绝贷款';
			                      	}
			                      	if(row.status == '6'){
			                      		return '其它银行放款';
			                      	}
			                      	if(row.status == '7'){
			                      		return '关闭交易';
			                      	}
			                      }
			                 }" width="15px">房源状态</th>
                <th data-options="field:'contractPrice',sortable:true,formatter: function(value,row,index){ 
			                   if(row.contractPrice != null){
			                      	return row.contractPrice+'万元';
								}
			                 }" width="15px">签约价格</th>
                <th data-options="field:'contractDate',sortable:true" width="15px">签约时间</th>
                <th data-options="field:'agencyNote',sortable:true" width="15px">未成交原因</th>
                <th data-options="field:'agencyInfo',sortable:true" width="15px">社区中介</th>
                <th data-options="field:'bankInfo',sortable:true" width="15px">社区银行</th>
                <th data-options="field:'loanAmount',sortable:true,formatter: function(value,row,index){ 
			                   if(row.loanAmount != null){
			                      	return row.loanAmount+'万元';
									}
			                 }" width="15px">放款金额</th>
                <th data-options="field:'loanDate',sortable:true" width="15px">放款时间</th>
                <th data-options="field:'bankNote',sortable:true" width="15px">拒绝贷款原因</th>
                <th data-options="field:'misUserName',sortable:true" width="15px">受理人</th>
                <th data-options="field:'misTime',sortable:true,
                formatter: function(value,row,index){
			                      if(null!=row.misTime&&row.misTime!=''){
			                      	return dateFormat(row.misTime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="15px">受理时间</th>
                <th data-options="field:'name',sortable:true,formatter: function(value,row,index){
                                  return aboutOperation(row,index);
			                }" width="15px">相关操作</th>
                <th data-options="field:'situation',sortable:true" width="15px">受理情况</th>			    
                <th data-options="field:'operator',sortable:true,
                			formatter: function(value,row,index){
                                  return getHistory(row.id,row.status,index);
			                }" width="15px">历史记录</th>
            </tr>
            </thead>
        </table>
    </div>
    
    <div id="acceptHouseLoanDig" class="easyui-window"
		style="width: 500px; height:200px; text-align: center;"
		data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'受理',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
		
			<table>
				<input id="id_dig" type = "hidden"/>
				<input id="status_dig" type = "hidden"/>
				<input id="prebank_dig" type = "hidden"/>
				<input id="preamount_dig" type = "hidden"/>
				<input id="predate_dig" type = "hidden"/>
				<input id="premisname_dig" type = "hidden"/>
				<tr>
				<td colspan="2"><font color ='red'>请务必在银行放款后填写最终放款金额 </font></td>
				</tr>
				<tr>
					<td><label>放款银行:</label></td>
					<td>
					    <input id = "bank_dig"
					 	    style="height:30px;width:300px;"
					 	    maxlength='50' 
							class="easyui-textbox"
							/>
					</td>
				</tr>
				<tr>
					<td><label>放款金额:</label></td>
					<td>
					    <input id = "amount_dig"
					    	onkeyup="value=value.replace(/[^\d.]/g,'')" 
					 	    style="height:30px;width:300px;"
							class="easyui-textbox"
							 />万元
					</td>
				</tr>
				<tr>
					<td><label>放款时间:</label></td>
					<td>
					    <input id = "date_dig"
					 	    style="height:30px;width:300px;"
							class="easyui-datebox"
							/>
					</td>
				</tr>
			</table>
				<a id="acceptHouseLoanBtn" href="javascript:void(0)"
				class="easyui-linkbutton" onclick="acceptHouseLoanBtnClick();">保存</a>
				&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeAcceptHouseLoanDig();">取消</a>
	</div>
    
     <div id="houseInfoDig" class="easyui-window"
		style="width: 430px; height:400px; text-align: center;"
		data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'补充房源信息',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
			<table id="houseInfoTable">
				
			</table>
				<a id="houseInfoBtn" href="javascript:void(0)"
				class="easyui-linkbutton" onclick="houseInfoBtnClick();">保存</a>
				&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeHouseInfoDig();">取消</a>
	</div>
    
</div>
</body>
</html>