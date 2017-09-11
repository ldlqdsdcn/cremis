<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/31
  Time: 14:52
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
                <label>&nbsp;&nbsp;&nbsp;编号:</label>
                <input id="groupNoLike" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;名称:</label>
                <input id="groupNameLike" style="width:150px;height:23px;" class="easyui-textbox" />
                &nbsp;&nbsp;&nbsp;<a href="#"  onclick="groupHelper.search()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)"  onclick="groupHelper.add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  style="margin-left:10px;margin-top:0px">添加</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)"  onclick="groupHelper.remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  style="margin-left:10px;margin-top:0px">删除</a>
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false" >
        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true"
               rownumbers="true" fitColumns="true" border="false" data-options="rownumbers:true,singleSelect:false,pageSize:20,pageList:[20,40,60,80]"
               url="<%=path %>/sys/group/list"
               method="post"
               id="groupTable"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true,sortable:true" width="auto"></th>
                <th data-options="field:'no',sortable:true" width="10%">编号</th>
                <th data-options="field:'name',sortable:true" width="10%">名称</th>
                <th data-options="field:'isactive',sortable:true" width="10%">描述</th>
                <th data-options="field:'created',sortable:true" width="10%">创建时间</th>
                <th data-options="field:'createdby',sortable:true" width="10%">创建人</th>
                <th data-options="field:'created',sortable:true" width="10%">修改时间</th>
                <th data-options="field:'createdby',sortable:true" width="10%">修改建人</th>
                <th data-options="field:'operator',sortable:true,formatter: function(value,row,index){
                                  return operation(row,index);
			                }" width="auto">相关操作</th>

            </tr>
            </thead>
        </table>
    </div>
