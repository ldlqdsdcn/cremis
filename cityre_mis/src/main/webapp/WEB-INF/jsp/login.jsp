<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/22
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta name="keywords" content="cremis">
    <meta name="description" content="Mis系统-登录界面">
    <title>Mis系统-登录界面</title>

    <link rel="stylesheet" href="<c:url value="/css/cremis_common.css"/>">

</head>

<body class="login-bg">

<div class="login-box">
    <div class="login-center-margin">
        <img src="<c:url value="/image/logo_cityre2017.png"/>" width="36%">
    </div>
    <header>
        <h1>Mis系统-登录管理系统</h1>
    </header>
    <div class="login-main">
        <form action="<c:url value="/login"/>" method="post">
            <div class="login-form-item">
                <label class="login-icon">
                    <i class="icon-font">&#xe609;</i>
                </label>
                <input type="text" name="username" lay-verify="username" autocomplete="off" placeholder="这里输入登录名"
                       class="login-input">
            </div>
            <div class="login-form-item">
                <label class="login-icon">
                    <i class="icon-font">&#xe606;</i>
                </label>
                <input type="password" name="password" lay-verify="password" autocomplete="off" placeholder="这里输入密码"
                       class="login-input">
            </div>
            <c:if test="${message!=null}">
                <div class="login-form-item" style="color: red;">
                    <c:out value="${message}"/>
                </div>
            </c:if>
            <div class="login-form-item">
                <div class="pull-left login-remember">
                    <input type="checkbox" name="rememberMe" value="true" title="记住帐号">
                    <label>记住帐号？</label>
                </div>
                <div class="pull-right">
                    <button class="login-btn">登录</button>
                </div>
                <div class="clear"></div>
            </div>
        </form>
    </div>
</div>
<footer>
    <p>Copyright © 2017 CreMis系统</p>
</footer>

</body>
</html>
