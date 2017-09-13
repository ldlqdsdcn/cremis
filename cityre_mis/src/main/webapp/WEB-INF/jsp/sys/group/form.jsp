<%@include file="/inc/taglib.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/1
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="groupPanel" class="easyui-panel" style="width:100%;max-width:100%;padding:30px 60px;">
    <form id="groupModelForm" class="easyui-form" method="post" data-options="novalidate:true">
        <table width="100%;" border="0">
            <tr>
                <td width="50%">
                    <input type="hidden" name="groupId" id="groupId">
                    <input class="easyui-textbox" name="groupNo" id="groupNo" style="width:60%"
                           data-options="label:'编号:',required:true">
                </td>
                <td width="50%">
                    <input class="easyui-textbox" name="groupName" id="groupName" style="width:60%"
                           data-options="label:'名称:',required:true">
                </td>
            </tr>
            <tr>
                <td colspan="2"><input class="easyui-textbox" name="groupDesc" id="groupDesc" style="width:100%"
                                       data-options="label:'描述:'"></td>
            </tr>
            <tr>
                <td colspan="2"> 是否有效:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="groupIsactive" id="groupIsactive"></td>
            </tr>
            <tr>
                <td><input class="easyui-textbox" name="groupCreated" disabled id="groupCreated" style="width:60%;"
                           data-options="label:'创建时间:'"></td>
                <td><input class="easyui-textbox" name="groupCreatedby" disabled id="groupCreatedby" style="width:60%"
                           data-options="label:'创建人:'"></td>
            </tr>
            <tr>
                <td><input class="easyui-textbox" disabled name="groupUpdated" id="groupUpdated" style="width:60%;"
                           data-options="label:'修改时间:'"></td>
                <td><input class="easyui-textbox" name="groupUpdatedby" disabled id="groupUpdatedby" style="width:60%;"
                           data-options="label:'修改人:'"></td>
            </tr>
        </table>

    </form>
    <div style="text-align:center;padding:5px 0">
        <shiro:hasPermission name="group:edit">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="groupHelper.save()" style="width:80px">保存</a>
        </shiro:hasPermission>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="  groupHelper.goList();" style="width:80px">返回</a>
    </div>
    <h3>权限</h3>
    <table>
        <thead>
        <th><input type="checkbox" onclick="eideaValidator.selectAll('privileges',this)"></th><th>编号</th><th>名称</th>
        </thead>
        <tbody id="groupTbodyContent">
        </tbody>
    </table>
</div>


