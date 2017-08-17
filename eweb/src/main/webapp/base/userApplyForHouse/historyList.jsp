<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String qd_contract_id = request.getParameter("id");
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
</script>

<title>历史记录</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" >
        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true" 
        		rownumbers="true" fitColumns="true" border="false" data-options="singleSelect:true,pageSize:50,pageList:[50,100,150,200]"
               url="<%=path %>/base/userApplyForHouse/historylist?id=<%=qd_contract_id %>"
               id="historyList_ids" 
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'amount',sortable:true,formatter: function(value,row,index){ 
			                   if(row.amount != null && row.amount != '0'){
			                   	return row.amount+'万元';
			                   }
			                 }" width="10%">价格</th>
                <th data-options="field:'loanDate',sortable:true" width="10%">放款时间</th>
                <th data-options="field:'bankName',sortable:true" width="20%">放款银行</th>
                <th data-options="field:'userName',sortable:true" width="10%">填写人</th>
                <th data-options="field:'updateTime',sortable:true" width="10%">填写时间</th>
                <th data-options="field:'prestatus',sortable:true,formatter: function(value,row,index){ 			                   
			                      if(row.prestatus != null){
			                      if(row.prestatus == '0'){
			                      		return '看房中';
			                      	}
			                      if(row.prestatus == '1'){
			                      		return '未签约';
			                      	}
			                      if(row.prestatus == '2'){
			                      		return '已签约';
			                      	}
			                      if(row.prestatus == '3'){
			                      		return '已签约(全款)';
			                      	}
			                      if(row.prestatus == '4'){
			                      		return '发放贷款';
			                      	}
			                      if(row.prestatus == '5'){
			                      		return '拒绝贷款';
			                      	}
			                      if(row.prestatus == '6'){
			                      		return '其它银行放款';
			                      	}
			                      if(row.prestatus == '7'){
			                      		return '关闭交易';
			                      	}
			                      }
			                 }" width="10%">房源状态（前）</th>
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
			                 }" width="10%">房源状态（后）</th>
            </tr>
            </thead>
        </table>
    </div>
    
</div>
</body>
</html>