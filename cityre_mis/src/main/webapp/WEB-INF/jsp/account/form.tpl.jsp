<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/4
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userPanel" class="easyui-panel" style="width:100%;max-width:100%;padding:30px 60px;">
    <form id="modelForm" class="easyui-form" method="post" data-options="novalidate:true">
        <table width="100%;" border="0">
            <tr>
                <td width="50%">
                    <input type="hidden" name="id" id="id">
                    <input class="easyui-textbox" name="unionuid" id="unionuid" style="width:60%"
                           data-options="label:'用户id:',required:true">
                </td>
                <td width="50%">
                    <input class="easyui-textbox" name="userid" id="userid" style="width:60%"
                           data-options="label:'用户登录名:',required:true">
                </td>
            </tr>
            <tr>
                <td ><input class="easyui-textbox" name="nickname" id="nickname" style="width:100%"
                                       data-options="label:'用户显示名称:'"></td>
                <td>
                    <input class="easyui-textbox" name="realname" id="realname" style="width:100%"
                           data-options="label:'用户真实姓名:'">
                </td>
            </tr>
            <tr><td>性别: 男<input type="radio" id="nan" name="sex" value="1" checked/> 女<input type="radio" id="nv" name="sex" value="0"> 未知<input type="radio" id="unknown" name="sex" value="-1">  </td>
                <td>
                    <input class="easyui-datebox" name="birthday" id="birthday" style="width:100%"
                           data-options="label:'生日:',formatter:myformatter,parser:myparser">

                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input class="easyui-textbox" name="userIcon" id="userIcon" style="width:100%"
                           data-options="label:'userIcon:'">

                </td>
            </tr>
            <tr>
                <td> <input class="easyui-textbox" name="isVerified" id="isVerified" style="width:100%"
                            data-options="label:'验证状态:'"></td>
                <td>
                    <input class="easyui-textbox" name="isValid" id="isValid" style="width:100%"
                           data-options="label:'是否有效:'">
                </td>
            </tr>
            <tr>
                <td> <input class="easyui-passwordbox" name="password" id="password" style="width:100%" iconWidth="28"
                            data-options="label:'密码:'"></td>
                <td>
                    <input class="easyui-passwordbox" name="passwordConfirm" id="passwordConfirm" style="width:100%"
                           data-options="label:'密码确认:'" iconWidth="28" >
                </td>
            </tr>
            <tr>
                <td><input class="easyui-textbox" name="createTime" disabled id="createTime" style="width:60%;"
                           data-options="label:'创建时间:'"></td>
                     <td><input class="easyui-textbox" disabled name="updateTime" id="updateTime" style="width:60%;"
                           data-options="label:'修改时间:'"></td>
            </tr>
            <tr>
                <td colspan="2">用户组：
                    <div id="userGroupDiv" style="display: inline"></div>
                </td>
            </tr>
        </table>

    </form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="accountHelper.save()" style="width:80px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="  accountHelper.goList();" style="width:80px">返回</a>
    </div>

    <h3>权限</h3>
    <table>
        <thead>
        <th><input type="checkbox" onclick="eideaValidator.selectAll('privileges',this)"></th><th>编号</th><th>名称</th>
        </thead>
        <tbody id="userTbodyContent">
        </tbody>
    </table>
</div>
