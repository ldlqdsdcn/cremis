<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/28
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<!-- 避免IE使用兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<!-- TopJUI框架样式 -->
<link type="text/css" href="<c:url value="/topjui/css/topjui.core.min.css"/>" rel="stylesheet">
<link type="text/css" href="<c:url value="/topjui/themes/default/topjui.green.css"/>" rel="stylesheet"
      id="dynamicTheme"/>
<!-- FontAwesome字体图标 -->
<link type="text/css" href="<c:url value="/topjui/plugins/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet"/>
<!-- jQuery相关引用 -->
<script type="text/javascript" src="<c:url value="/topjui/plugins/jquery/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/topjui/plugins/jquery/jquery.cookie.js"/>"></script>
<!-- TopJUI框架配置 -->
<script type="text/javascript" src="<c:url value="/static/public/js/topjui.config.js"/>"></script>
<!-- TopJUI框架核心-->
<script type="text/javascript" src="<c:url value="/topjui/js/topjui.core.min.js"/>"></script>
<!-- TopJUI中文支持 -->
<script type="text/javascript" src="<c:url value="/topjui/js/locale/topjui.lang.zh_CN.js"/>"></script>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<script type="text/javascript">
    //域路径
    var ctx = '<%=basePath%>';
    var ctx2 = '<%=request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path %>';
</script>