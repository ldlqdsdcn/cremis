<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/28
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/inc/header.jsp" %>
</head>
<body>
<script type="text/javascript">
    var repositoryHelper={
        editColumnConverter:function (value,row,index) {
            return "<a href='javascript:void(0)' onclick='repositoryHelper.edit("+index+")' style='margin-left:10px;margin-top:0px'>编辑</a>";
        },
        remove:function()
        {
            var rows = $('#repositoryTable').datagrid('getSelections');
            if(rows==null||rows.length==0)
            {
                $.messager.alert("警告","请先选中再删除");
                return;
            }
            var ids=[];
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                ids.push(row.id);
            }
            $.messager.confirm('提示:','您确认要删除选中记录吗?',function(event){
                if(event){
                    $.ajax({
                        url: "<%=path%>/sys/repository/deleteRepositories",
                        data:JSON.stringify(ids),
                        type: 'POST',
                        dataType: "json",
                        contentType : 'application/json;charset=utf-8',
                        success: function (result) {
                            if(result.success){
                                $.messager.alert("操作成功","操作成功！");
                                $('#repositoryTable').datagrid('reload');
                                return;
                            }
                            else
                            {
                                $.messager.alert("删除失败",result.message);
                            }
                        }
                    });
                }
            });
        },
        edit:function(index)
        {
            var data=$('#repositoryTable').datagrid('getData');
            var row = data.rows[index];
            //赋值
            $("#id").val(row.id);
            $("#name").textbox("setValue", row.name);
            $("#no").textbox("setValue", row.no);
            document.getElementById("name").value=row.name;
            if(row.isactive=='Y')
            {
                $("#isactive").attr('checked','checked');
            }
            else
            {
                $("#isactive").attr('checked','');
            }
            $('#addRepository_dig').dialog('open').dialog('setTitle','编辑授权点');
        },
        search:function () {
        var no = $("#noLike").val();
        var name=$("#nameLike").val();
        var param={};
        if(eideaValidator.isNotEmpty(no))
        {
            param.noLike=no;
        }
        if(eideaValidator.isNotEmpty(name))
        {
            param.nameLike=name;
        }
        $("#repositoryTable").datagrid(  {
            queryParams: param,
            cache: false
        });
    },
        add:function()
        {
            $('#addRepository_dig').dialog('open').dialog('setTitle','添加授权点');
            $('#modelForm').form('reset');
        },
        closeEditDialog:function()
        {
            $('#addRepository_dig').dialog('close');
        },
        save:function () {
            if(!$("#modelForm").form('enableValidation').form('validate'))
            {
                return;
            }
            var param={};
            param.id=$("#id").val();
            param.no=$("#no").textbox("getValue");
            param.name=$("#name").textbox("getValue");
            if($('#isactive').is(':checked'))
            {
                param.isactive="Y";
            }
            else
            {
                param.isactive="N";
            }
            $.ajax({
                url: "<%=path%>/sys/repository/save",
                data:JSON.stringify(param),
                type: 'POST',
                dataType: "json",
                contentType : 'application/json;charset=utf-8',
                success: function (result) {
                    if(result.success){
                        $('#addRepository_dig').dialog('close')
                        $.messager.alert("操作成功","操作成功！");
                        $('#repositoryTable').datagrid('reload');
                        return;
                    }
                    else
                    {
                        $.messager.alert("操作失败",result.message);
                    }
                }
            });

        }
    };
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height:70px;overflow:hidden;background:#fafafa;">
        <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:65px"
             data-options="border:false,iconCls:'icon-save'">
            <fieldset class="fieldset" style="height: 45px;">
                <legend class="legend">查询条件</legend>
                <label>&nbsp;&nbsp;&nbsp;编号:</label>
                <input id="noLike" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;名称:</label>
                <input id="nameLike" style="width:150px;height:23px;" class="easyui-textbox" />
                &nbsp;&nbsp;&nbsp;<a href="#" id="seach_user" onclick="repositoryHelper.search()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)"  onclick="repositoryHelper.add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  style="margin-left:10px;margin-top:0px">添加</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)"  onclick="repositoryHelper.remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  style="margin-left:10px;margin-top:0px">删除</a>
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false" >
        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true"
               rownumbers="true" fitColumns="true" border="false" data-options="rownumbers:true,singleSelect:false,pageSize:20,pageList:[20,40,60,80]"
               url="<%=path %>/sys/repository/getList"
               method="post"
               id="repositoryTable"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true,sortable:true" width="auto"></th>
                <th data-options="field:'no',sortable:true" width="10%">编号</th>
                <th data-options="field:'name',sortable:true" width="10%">名称</th>
                <th data-options="field:'isactive',sortable:true" width="10%">是否有效</th>
                <th data-options="field:'created',sortable:true" width="10%">创建时间</th>
                <th data-options="field:'createdby',sortable:true" width="10%">创建人</th>
                <th data-options="field:'created',sortable:true" width="10%">修改时间</th>
                <th data-options="field:'createdby',sortable:true" width="10%">修改建人</th>
                <th data-options="field:'operator',sortable:true,formatter:repositoryHelper.editColumnConverter" width="auto">相关操作</th>

            </tr>
            </thead>
        </table>
    </div>
    <div id="addRepository_dig" class="easyui-window" style="width: 400px"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'添加授权点',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <form id="modelForm" class="easyui-form" method="post" data-options="novalidate:true">
            <fieldset class="fieldset" style="height:120px">
                <legend class="legend" id="title">授权点信息</legend>
                <input type="hidden" id="id">
                <table>
                    <tr>
                        <td ><label>编号：</label></td>
                        <td ><input type="text" class="easyui-textbox" id="no"   data-options="required:true"/></td>
                    </tr>
                    <tr>
                        <td ><label>名称：</label></td>
                        <td ><input type="text" class="easyui-textbox" id="name" data-options="required:true"/></td>
                    </tr>
                    <tr>
                        <td ><label>是否有效：</label></td>
                        <td ><input type="checkbox" id="isactive"></td>
                    </tr>
                </table>
            </fieldset>
            <div id="dlg-buttons" style="text-align: right">
                <a id="addUser_btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="repositoryHelper.save();">保存</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="repositoryHelper.closeEditDialog();">取消</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>