<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/4
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>


<div data-options="region:'north',border:false" style="height:70px;overflow:hidden;background:#fafafa;">
    <div  class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:65px"
          data-options="border:false,iconCls:'icon-save'">
        <fieldset class="fieldset" style="height: 45px;">
            <legend class="legend">查询条件</legend>
            <label>&nbsp;&nbsp;&nbsp;登录名:</label>
            <input id="useridLike" style="width:150px;height:23px;" class="easyui-textbox" />
            <label>&nbsp;&nbsp;&nbsp;昵称:</label>
            <input id="nicknameLike" style="width:150px;height:23px;" class="easyui-textbox" />
            &nbsp;&nbsp;&nbsp;<a href="#"  onclick="accountHelper.search()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
        </fieldset>
    </div>
</div>
<div data-options="region:'center',border:true">
    <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true"
           rownumbers="true" fitColumns="true" border="false" data-options="rownumbers:true,singleSelect:false,pageSize:20,pageList:[20]"
           url="<%=path %>/account/user/list"
           method="post"
           id="userTable"
           style="overflow:auto;width:auto; ">
        <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true,sortable:true"></th>
            <th data-options="field:'unionuid',sortable:true" >union uid</th>
            <th data-options="field:'userid',sortable:true" >user Id</th>
            <th data-options="field:'nickname',sortable:true">昵称</th>
            <th data-options="field:'realname',sortable:true" >真实姓名</th>
            <th data-options="field:'sex',sortable:true" >性别</th>
            <th data-options="field:'isverified',sortable:true" >是否验证</th>
            <th data-options="field:'isvalid',sortable:true" >是否有效</th>
            <th data-options="field:'operator',sortable:true,formatter: function(value,row,index){
                                  return operation(row,index);
			                }" width="auto">相关操作</th>

        </tr>
        </thead>
    </table>
    <%--    <table   id="userTable" data-toggle="topjui-datagrid"
               data-options="id:'issueInvoiceDatagrid',
        url:'<%=path %>/account/user/list">
            <thead>
            <tr>
                <th data-options="field:'id',title:'ID',checkbox:true"></th>
                <th data-options="field:'unionuid',title:'union uid'"></th>
                <th data-options="field:'nickname',title:'昵称'"></th>
                <th data-options="field:'realname',title:'真实姓名'"></th>
                <th data-options="field:'sex',title:'性别'"></th>
                <th data-options="field:'isverified',title:'是否验证'"></th>
                <th data-options="field:'isvalid',title:'是否有效'"></th>
                <th data-options="field:'operator',title:'相关操作',sortable:true,formatter: function(value,row,index){return operation(row,index);}"></th>
            </tr>
            </thead>
        </table>--%>
</div>
