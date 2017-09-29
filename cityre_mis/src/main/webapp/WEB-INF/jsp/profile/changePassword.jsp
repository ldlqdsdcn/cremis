<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/14
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .editTable .label {
        min-width: 80px;
        width: 80px;
    }
</style>
<table class="editTable">
    <tr>
        <td class="label">旧密码</td>
        <td><input class="easyui-passwordbox" name="oldPassword" id="oldPassword" style="width: 200px"></td>
    </tr>
    <tr>
        <td class="label">新密码</td>
        <td><input class="easyui-passwordbox" name="password" id="password" style="width: 200px"></td>
    </tr>
    <tr>
        <td class="label">新密码确认</td>
        <td>
            <input class="easyui-passwordbox" name="passwordConfirm" id="passwordConfirm"
                   validType="confirmPass['#password']" style="width: 200px">
        </td>
    </tr>
</table>