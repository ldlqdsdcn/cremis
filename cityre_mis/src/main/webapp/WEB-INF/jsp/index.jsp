<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/21
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta name="keywords" content="cremis">
    <meta name="description" content="Mis系统-登录界面">
    <title>Mis系统-登录界面</title>

    <link rel="stylesheet" href="<c:url value="/css/cremis_common.css"/>">

</head>

<body>
<div class="layout-admin">
    <header class="layout-header">
        <div class="logo"></div>
        <span class="header-logo">CRE@MIS 系统管理平台</span>
        <a class="header-menu-btn" href="javascript:;"><i class="icon-font">&#xe600;</i></a>
        <ul class="header-bar">
            <!--<li class="header-bar-role"><a href="javascript:;">超级管理员</a></li>-->
            <li class="header-bar-nav">
                <a href="javascript:;">【青岛】选择城市<i class="icon-font" style="margin-left:5px;">&#xe60c;</i></a>
            </li>
            <li class="header-bar-nav">
                <a href="javascript:;">简体中文<i class="icon-font" style="margin-left:5px;">&#xe60c;</i></a>
                <ul class="header-dropdown-menu">
                    <li><a href="javascript:;">English USA</a></li>
                </ul>
            </li>
            <li class="header-bar-nav">
                <a href="javascript:;">admin<i class="icon-font" style="margin-left:5px;">&#xe60c;</i></a>
                <ul class="header-dropdown-menu">
                    <li><a href="javascript:;">修改密码</a></li>
                    <li><a href="javascript:;">个人设置</a></li>
                    <li><a href="javascript:;">退出</a></li>
                </ul>
            </li>
            <li class="header-bar-nav">
                <a href="javascript:;" title="消息"><i class="icon-font">&#xe603;</i></a>
            </li>
        </ul>
    </header>

    <aside class="layout-side">
        <div class="profile">
            <div class="profile_pic">

                <img src="<c:url value="/image/header.png"/>" alt="..." class="img-circle">
            </div>
            <div class="profile_info">
                <span>Welcome,</span>
                <h2><p>用户姓名</p></h2>
            </div>
        </div>
        <ul class="side-menu">

        </ul>
    </aside>

    <div class="layout-side-arrow">
        <div class="layout-side-arrow-icon"><i class="icon-font">&#xe60d;</i></div>
    </div>

    <section class="layout-main">
        <div class="layout-main-tab">
            <button class="tab-btn btn-left"><i class="icon-font">&#xe60e;</i></button>
            <nav class="tab-nav">
                <div class="tab-nav-content">
                    <a href="javascript:;" class="content-tab active" data-id="home.html">首页</a>
                </div>
            </nav>
            <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
        </div>

        <div class="layout-main-body">
            <iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="<c:url value="/kouhao"/>" frameborder="0" data-id="<c:url value="/kouhao"/>" seamless></iframe>
        </div>
    </section>

    <div class="layout-footer">Copyright © 2017 CreMis系统</div>

</div>
<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/cremis.js"/>"></script>
</body>
</html>

