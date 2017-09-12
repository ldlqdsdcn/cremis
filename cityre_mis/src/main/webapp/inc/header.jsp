<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/4
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="computer,mobile,internet">
<meta http-equiv="description" content="mis管理系统">
<link rel="shortcut icon" href="<%=path%>/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/default/easyui.css" id="swicth-style">
<script type="text/javascript">
    //域路径
    var ctx = '<%=basePath%>';
    var ctx2='<%=request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path %>';
</script>

<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="<%=path%>/css/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/ImiUtil.js" charset="utf-8"></script>
<!-- 编辑器 -->
<script language="javascript" type="text/javascript" src="<%=path%>/js/ux/datetime/WdatePicker.js"></script>

<script type="text/javascript" src="<%=path%>/js/hcharts/highcharts.js"></script>
<link href="<%=path%>/js/art/skins/idialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/ux/date.format.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/js/ux/validate.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/js/art/artDialog.js"></script>
<script type="text/javascript" src="<%=path%>/js/art/plugins/iframeTools.js"></script>
<link href="<%=path%>/js/ztree/css/showLoading.css" rel="stylesheet" media="screen" />
<link rel="stylesheet"          href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css"
      type="text/css" />
<script type="text/javascript"            src="<%=path%>/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"            src="<%=path%>/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"            src="<%=path%>/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript"            src="<%=path%>/js/ztree/js/jquery.showLoading.min.js"></script>
<script  type="text/javascript" src="<%=path%>/js/eidea.validate.js"></script>
<script  type="text/javascript" src="<%=path%>/js/eidea.util.js"></script>