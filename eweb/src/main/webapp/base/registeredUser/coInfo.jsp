<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String cocode = (String)request.getParameter("cocode");
%>
<html>
<head>
    <script src="../../jQuery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="../../jQuery/jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../../jQuery/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../../jQuery/date.format.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="../../jQuery/jquery-easyui-1.5/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../../jQuery/jquery-easyui-1.5/themes/default/easyui.css" />
    <style>
        li {list-style-type:none;}
    </style>
    <script>
        $(function(){

        });
        function getHeadImg(headImg){
            var html ="";

            html = "<a  target='_blank' href='"+headImg+"'><img width='200' height='200' src='"+headImg+"'/></a>";
            return html;
        }
    </script>

    <title>用户信息查询</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height:320px;overflow:hidden;background:#fafafa;">

        <fieldset class="fieldset" style="height:300px">
            <legend class="legend">公司信息</legend>
            <table id="coInfoTb" >
                <tr><td>信息类型:</td>
                    <td colspan="3"> ${coinfo.coinfo}</td><td rowspan = "7"><a  target='_blank' href='${coinfo.coimagefile}'><img width='250' height='250' src="${coinfo.coimagefile}"/></a></td>
                </tr>
                <tr><td>公司名称：</td>
                    <td colspan="3">${coinfo.coname} </td>
                </tr>
                <tr><td>地址：</td>
                    <td colspan="3">${coinfo.coAddr} </td>
                </tr>
                <tr><td>电话：</td>
                    <td>${coinfo.cotel}</td>
                    <td>传真：</td>
                    <td>${coinfo.cofax}</td>
                </tr>
                <tr><td>邮件：</td>
                    <td>${coinfo.coemail}</td>
                    <td>网址：</td>
                    <td>${coinfo.cowebsite}</td>
                </tr>
                <tr><td>注册时间：</td><td> ${coinfo.regtimestr}</td>
                    <td>更新时间：</td><td>${coinfo.updatetimestr}</td>
                </tr>
                <tr><td>标语：</td><td>${coinfo.slogan}</td>
                    <td>店长uid：</td><td>${coinfo.couid}</td>
                </tr>
                <tr><td>公司房源数量：</td>
                    <td>
                    <a href='<%=path%>/base/registeredUser/perdaycountco.jsp?cocode=${coinfo.cocode}' target='_blank'><font color='blue'>${coinfo.sourceCnt}</font></a>
                </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div data-options="region:'center',border:false" >
        <fieldset class="fieldset">
        <legend class="legend">以下是公司员工清单</legend>
        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true"
               rownumbers="true" fitColumns="true" border="false" data-options="singleSelect:true,pageSize:20"
               url="getCoUser?cocode=<%=cocode%>"
               id="userList_ids"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'name',sortable:true" width="5%">姓名</th>
                <th data-options="field:'setuid',sortable:true,formatter: function(value,row,index){
			                     if(row.setuid == null || row.setuid == ''){
			                        return '未审核';
			                     }else{
			                        return '已审核';
			                     }
			                 }" width="5%">审核状态</th>
                <th data-options="field:'nickname',sortable:true" width="5%">昵称</th>
                <th data-options="field:'sex',sortable:true" width="5%">姓别</th>
                <th data-options="field:'bornDate',sortable:true,
                formatter: function(value,row,index){
			                      if(null!=row.bornDate&&row.bornDate!=''){
			                      	return dateFormat(row.bornDate,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="10%">出生日期</th>
                <th data-options="field:'idcl',sortable:true" width="5%">证件类型</th>
                <th data-options="field:'idno',sortable:true" width="10%">证件号码</th>
                <th data-options="field:'telno',sortable:true" width="10%">电话</th>
                <th data-options="field:'email',sortable:true" width="10%">邮箱</th>
                <th data-options="field:'regtime',sortable:true,
                formatter: function(value,row,index){
			                      if(null!=row.regtime&&row.regtime!=''){
			                      	return dateFormat(row.regtime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="10%">注册时间</th>
                <th data-options="field:'headportrait',sortable:true,formatter: function(value,row,index){
                                     return getHeadImg(row.headportrait);
			                         }" width="20%">头像</th>
            </tr>
            </thead>
        </table>
        </fieldset>
    </div>
</div>
</body>
</html>
