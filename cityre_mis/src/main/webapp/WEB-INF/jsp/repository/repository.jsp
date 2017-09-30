<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/28
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/inc/topjuiheader.jsp" %>
</head>
<body>
<script type="text/javascript">
    var repositoryHelper = {
        editColumnConverter: function (value, row, index) {
            return "<a href='javascript:void(0)' onclick='repositoryHelper.edit(" + index + ")' style='margin-left:10px;margin-top:0px'>编辑</a>";
        },
        remove: function () {
            var rows = $('#repositoryTable').datagrid('getSelections');
            if (rows == null || rows.length == 0) {
                $.messager.alert("警告", "请先选中再删除");
                return;
            }
            var ids = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                ids.push(row.id);
            }
            $.messager.confirm('提示:', '您确认要删除选中记录吗?', function (event) {
                if (event) {
                    $.ajax({
                        url: "<%=path%>/sys/repository/deleteRepositories",
                        data: JSON.stringify(ids),
                        type: 'POST',
                        dataType: "json",
                        contentType: 'application/json;charset=utf-8',
                        success: function (result) {
                            if (result.success) {
                                $.messager.alert("操作成功", "操作成功！");
                                $('#repositoryTable').datagrid('reload');
                                return;
                            }
                            else {
                                $.messager.alert("删除失败", result.message);
                            }
                        }
                    });
                }
            });
        },
        edit: function (index) {
            var data = $('#repositoryTable').datagrid('getData');
            var row = data.rows[index];
            //赋值
            $("#id").val(row.id);
            $("#name").textbox("setValue", row.name);
            $("#no").textbox("setValue", row.no);
            document.getElementById("name").value = row.name;
            if (row.isactive == 'Y') {
                $("#isactive").attr('checked', 'checked');
            }
            else {
                $("#isactive").attr('checked', '');
            }
            $('#addRepository_dig').dialog('open').dialog('setTitle', '编辑授权点');
        },
        search: function () {
            var no = $("#noLike").val();
            var name = $("#nameLike").val();
            var param = {};
            if (eideaValidator.isNotEmpty(no)) {
                param.noLike = no;
            }
            if (eideaValidator.isNotEmpty(name)) {
                param.nameLike = name;
            }
            $("#repositoryTable").datagrid({
                queryParams: param,
                cache: false
            });
        },
        add: function () {
            $('#addRepository_dig').dialog('open').dialog('setTitle', '添加授权点');
            $("#id").val('');
            $('#modelForm').form('reset');
        },
        closeEditDialog: function () {
            $('#addRepository_dig').dialog('close');
        },
        save: function () {
            if (!$("#modelForm").form('enableValidation').form('validate')) {
                return;
            }
            var param = {};
            param.id = $("#id").val();
            param.no = $("#no").textbox("getValue");
            param.name = $("#name").textbox("getValue");
            if ($('#isactive').is(':checked')) {
                param.isactive = "Y";
            }
            else {
                param.isactive = "N";
            }
            $.ajax({
                url: "<%=path%>/sys/repository/save",
                data: JSON.stringify(param),
                type: 'POST',
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result.success) {
                        $('#addRepository_dig').dialog('close')
                        $.messager.alert("操作成功", "操作成功！");
                        $('#repositoryTable').datagrid('reload');
                        return;
                    }
                    else {
                        $.messager.alert("操作失败", result.message);
                    }
                }
            });

        }
    };
</script>
<table id="productDg"></table>

<!-- 表格工具栏开始 -->
<div id="productDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'productDg'
       }">
    <a id="add" href="javascript:void(0)">新增</a>
    <a id="edit" href="javascript:void(0)">编辑</a>
    <a id="delete" href="javascript:void(0)">删除</a>
    <a id="filter" href="javascript:void(0)">过滤</a>
    <a id="export" href="javascript:void(0)">导出</a>
    <form id="queryForm" class="search-box">
        <input type="text" name="noLike" data-toggle="topjui-textbox"
               data-options="id:'noLike',prompt:'编号'">
        <input type="text" name="nameLike" data-toggle="topjui-textbox"
               data-options="id:'nameLike',prompt:'名称'">
        <a id="queryBtn" href="javascript:void(0)">查询</a>
    </form>
</div>
<!-- 表格工具栏结束 -->
<script type="text/javascript">
    $(function () {
        var productDg = {
            type: 'datagrid',
            id: 'productDg'
        };

        $("#productDg").iDatagrid({
            id: 'productDg',
            url: '<%=path %>/sys/repository/getList',
            columns: [[
                {field: 'ck', title: 'UUID', checkbox: true},
                {field: 'no', title: '编号', sortable: true},
                {field: 'name', title: '名称', sortable: true},
                {field: 'isactive', title: '是否有效', sortable: true},
                {field: 'created', title: '创建时间', sortable: true},
                {field: 'createdby', title: '创建人', sortable: true},
                {field: 'updated', title: '修改时间', sortable: true},
                {field: 'updatedby', title: '修改人', sortable: true}//,
                //{field: 'operator', title: 'operator', sortable: true, formatter:repositoryHelper.editColumnConverter}
            ]],
            filter: [{
                field: 'name',
                type: 'textbox',
                op: ['contains', 'equal', 'notequal', 'less', 'greater']
            }]
        });

        $("#add").iMenubutton({
            method: 'openDialog',
            iconCls: 'fa fa-plus',
            dialog: {
                id: 'userAddDialog',
                width: 930,
                height: 500,
                href: '<c:url value="/sys/repository/showForm"/>',
                buttonsGroup: [
                    {
                        text: '保存',
                        url: '<c:url value="/sys/repository/save/"/>',
                        iconCls: 'fa fa-plus',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-normal'
                    }
                ]
            }
        });

        $("#edit").iMenubutton({
            method: 'openDialog',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn',
            grid: productDg,

            dialog: {
                width: 930,
                height: 500,
                href: '<c:url value="/sys/repository/showForm"/>',
                url: '<c:url value="/sys/repository/get/"/>{id}',

                buttonsGroup: [
                    {
                        text: '更新',
                        url: '<c:url value="/sys/repository/save/"/>',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn'
                    }
                ]
            }
        });

        $("#delete").iMenubutton({
            method: 'doAjax',
            iconCls: 'fa fa-trash',
            btnCls: 'topjui-btn-danger',
            confirmMsg: '确认删除？',
            grid: {
                type: 'datagrid',
                id: 'productDg',
                uncheckedMsg: '请先勾选要删除的数据',
                param: 'idsString:id'
            },
            url: '<c:url value="/sys/repository/deleteRepositories"/>'
        });

        $("#filter").iMenubutton({
            method: 'filter',
            btnCls: 'topjui-btn-warm',
            grid: productDg
        });
        $("#export").iMenubutton({
            method: 'export',
            btnCls: 'topjui-btn',
            grid: {type: 'datagrid', 'id': 'productDg'},
            url: '<c:url value="/sys/repository/export"/>'
        });
        $('#queryBtn').iMenubutton({
            method: 'query',
            iconCls: 'fa fa-search',
            btnCls: 'topjui-btn-normal',
            form: {id: 'queryForm'},
            grid: {type: 'datagrid', 'id': 'productDg'}
        });
    });
</script>
</body>
</html>