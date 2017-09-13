<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/24
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<% String headerKey = "x-requested-with";
    String headerContent = "XMLHttpRequest";
    String angularKey = "accept";
    String angularContent = "application/json";
    if (!headerContent.equals(request.getHeader(headerKey)) && !(request.getHeader(angularKey) != null && request.getHeader(angularKey).contains(angularContent))) {
        response.setContentType("text/html; charset=UTF-8");
%>
<html>
<head>
</head>
<body style="background: white">
<div align="center">

    <img src="<%=request.getContextPath() %>/image/noview.jpg"><br><br>
    <font size="4" color="red"><b>您没有权限执行该操作</b></font>
</div>
</body>
</html>
<%
    } else {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        out.print("{\"code\":4,\"message\":\"您没有权限执行该操作\"}");
    }
%>