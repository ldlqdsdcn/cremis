<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String headerKey = "x-requested-with";
    String headerContent = "XMLHttpRequest";
    String angularKey = "accept";
    String angularContent = "application/json";
    if (!headerContent.equals(request.getHeader(headerKey)) && !(request.getHeader(angularKey)!=null&&request.getHeader(angularKey).contains(angularContent))) {
        response.setContentType("text/html; charset=UTF-8");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <head>
        <script type="text/javascript">
            if (window.parent.parent != null)
                window.parent.parent.location = "<c:url value='/login'/>";
            else {
                if (window.parent != null)
                    window.parent.location = "<c:url value='/login'/>";
                else window.location = "<c:url value='/login'/>";
            }
        </script>
    </head>
<body></body>
</html>
<%
    } else {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        ;
        out.print("{\"code\":4,\"message\":\"你需要登录\"}");
    }%>