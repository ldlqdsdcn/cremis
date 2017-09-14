<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/14
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:50px 60px">
    <div style="margin-bottom:20px"><input class="easyui-passwordbox" name="oldPassword" id="oldPassword"
                                           style="width:100%"
                                           data-options="label:'旧密码:'"></div>
    <div style="margin-bottom:20px"><input class="easyui-passwordbox" name="password" id="password" style="width:100%"
                                           data-options="label:'新密码:'"></td>
    </div>
    <div style="margin-bottom:20px"><input class="easyui-passwordbox" name="passwordConfirm" id="passwordConfirm"
                                           style="width:100%"
                                           data-options="label:'新密码确认:'" validType="confirmPass['#password']"></div>
</div>
