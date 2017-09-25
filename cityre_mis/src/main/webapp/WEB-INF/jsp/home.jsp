<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/25
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mis管理中心</title>
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
        var ctx2 = '<%=request.getScheme() + "://"
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
    <link href="<%=path%>/js/art/skins/idialog.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/js/ux/date.format.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path%>/js/ux/validate.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path%>/js/art/artDialog.js"></script>
    <script type="text/javascript" src="<%=path%>/js/art/plugins/iframeTools.js"></script>
    <link href="<%=path%>/js/ztree/css/showLoading.css" rel="stylesheet" media="screen"/>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css"/>
    <script type="text/javascript" src="<%=path%>/js/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=path%>/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="<%=path%>/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
    <script type="text/javascript" src="<%=path%>/js/ztree/js/jquery.showLoading.min.js"></script>
    <script language="javascript" type="text/javascript" src="<%=path%>/js/eidea.validate.js"></script>
    <script language="javascript" type="text/javascript" src="<%=path%>/js/eidea.util.js"></script>
    <style type="text/css">
        body {
            font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
            padding: 0;
            margin: 0;
            min-width: 1200px;
        }

        a:link {
            text-decoration: none;
        }

        a:visited {
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        a:active {
            text-decoration: none;
        }

        /*右侧区域滚动*/


        .cs-north {
            height: 60px;
        }

        .cs-north-bg {
            width: 100%;
            height: 100%;
            background: url(<%=path%>/image/header_bg.png) repeat-x;
        }

        .cs-north-logo {
            height: 40px;
            margin: 15px 0px 0px 5px;
            display: inline-block;
            color: #000000;
            font-size: 22px;
            font-weight: bold;
            text-decoration: none
        }

        .cs-west {
            width: 190px;
            padding: 0px;
        }

        .note_refresh {
            background: url('<%=path%>/image/icon/refresh.gif') no-repeat;
        }

        .cs-south {
            height: 23px;
            background: url(<%=path%>/image/panel_title.gif) repeat-x;
            padding-top: 5px;
            text-align: center;
        }

        .cs-tab-menu {
            width: 120px;
        }

        .cs-home-remark {
            padding: 10px;
        }

        .ui-skin-nav {
            float: right;
            margin-right: 10px;
            list-style: none outside none;
            height: 5px;
        }

        .ui-skin-nav .li-skinitem {
            float: left;
            font-size: 5px;
            line-height: 5px;
            margin-left: 5px;
            text-align: center;
        }

        .ui-skin-nav .li-skinitem span {
            cursor: pointer;
            width: 10px;
            height: 10px;
            display: inline-block;
        }

        .ui-skin-nav .li-skinitem span.cs-skin-on {
            border: 1px solid #FFFFFF;
        }

        .ui-skin-nav .li-skinitem span.gray {
            background-color: blue;
        }

        .ui-skin-nav .li-skinitem span.black {
            background-color: black;
        }

        .ui-skin-nav .li-skinitem span.metro {
            background-color: gray;
        }

        .cs-navi-tab {
            height: 10px;
            padding: 5px 0 13px 22px;
            overflow: hidden;
            border-bottom: 1px dotted #DBDBDB;
            background: #FCFCFD;
            cursor: pointer;
            line-height: 20px;
            list-style: none;
            background: url(<%=path%>/image/icon/bulletrbl.png) no-repeat 2px center;
        }

        .li_fn {
            background: url(<%=path%>/image/icon/bulletrbl.png) no-repeat 2px center;
        }

        .tree_fn {
            background: url(<%=path%>/image/icon/pkg.gif) no-repeat 2px center;
        }

        .cs-navi-tab:hover {
            background: #88CFFF;
            cursor: pointer;
        }

        .cs_now {
            background: #88CFFF;
        }

        .console-tab-div {
            height: 28px;
            border-bottom: 1px solid #C9C9C9;
            position: relative;
        }

        .tab_items {
            height: 23px;
            border: 1px solid #C9C9C9;
            float: left;
            margin-right: 2px;
            padding: 0 20px;
            cursor: pointer;
            color: #0033FF;
            background-color: white;
        }

        .tab_items_href {
            border-bottom: 1px solid white;
            border-top: 5px solid #C9C9C9;
            background-color: white;
            cursor: default;
            cursor: pointer;
            line-height: 23px;
            font-weight: bold;
        }

        ._hrefselect {
            border-top: 5px solid #F90;
        }

        .footer_mdiv {
            position: absolute;
            bottom: 0px;
            width: 100%;
            height: 60px;
            text-align: center;
            overflow: hidden;
            text-align: center;
        }

        .console-suggest {
            position: absolute;
            color: #999;
        }

        .console-icon-18 {
            width: 16px;
            height: 16px;
            background: url(<%=path%>/image/icon/header0.png) 0;
            display: inline-block;
            zoom: 1;
            *display: inline;
            vertical-align: middle;
            overflow: hidden;
            margin-right: 5px;
        }

        .console-icon-18:hover {
            background-position: -17px;
        }

        ._table {
            font-size: 12px;
            line-height: 20px;
            color: #666;
            background: #fefefe;
            border-left: 1px solid #dedede;
            border-left: 1px solid #dedede;
            border-top: 1px solid #dedede;
        }

        .td_title {
            font-size: 12px;
            background: #f9f9f9;
            color: #000000;
            text-align: center;
            padding: 6px 0px;
            font-weight: bold;
            border-right: 1px solid #dedede;
            border-bottom: 1px solid #dedede;
        }

        .td_title_left {
            font-size: 12px;
            background: #f9f9f9;
            color: #000000;
            padding: 6px 0px;
            font-weight: bold;
            border-right: 1px solid #dedede;
            border-bottom: 1px solid #dedede;
        }

        .td_center {
            font-size: 12px;
            border-right: 1px solid #dedede;
            border-bottom: 1px solid #dedede;
        }

        .td_center a {
            font-size: 12px;
            color: #F30;
        }

        .td_left {
            font-size: 12px;
            text-align: left;
            border-right: 1px solid #dedede;
            border-bottom: 1px solid #dedede;
        }

        #img_qh_div {
            position: relative;
        }

        #img_dg_div {
            position: absolute;
            width: 16px;
            height: 16px;
            bottom: 5px;
            right: 5px;
            background-image: url('<%=path%>/image/icon/del.png');
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        $.extend($.fn.validatebox.defaults.rules, {
            confirmPass: {
                validator: function (value, param) {
                    var pass = $(param[0]).passwordbox('getValue');
                    return value == pass;
                },
                message: '两次密码输入不一致.'
            }
        });
        $(function () {
            loadSystemZtreeIMI();

            var role = "<%="admin"%>";
            if (role == "normal") {
                $("#systemztreeId").hide();
            } else if (role == "admin") {
                $("#systemztreeId").show();
            }


            //右击事件
            tabCloseEven();
            $('#time_ids').html(currentTime);

            //左边切换
            $('.cs-navi-tab').click(function () {
                $('#mobileAccordion_ids li').removeClass("cs_now");
                var $this = $(this);
                $this.addClass("cs_now");
                var href = $this.attr('src');
                var title = $this.text();
                addTabto(title, href);
            });

            //首页切换
            $('.tab_items_href').click(function () {
                $('.console-tab-div a').removeClass("_hrefselect");
                var $this = $(this);
                $this.addClass("_hrefselect");

            });

            var themes = {
                'gray': ctx + '/css/gray/easyui.css',
                'black': ctx + '/css/black/easyui.css',
                'metro': ctx + '/css/metro/easyui.css',
            };

            var skins = $('.li-skinitem span').click(function () {
                var $this = $(this);
                if ($this.hasClass('cs-skin-on')) return;
                skins.removeClass('cs-skin-on');
                $this.addClass('cs-skin-on');
                var skin = $this.attr('rel');
                $('#swicth-style').attr('href', themes[skin]);
                setCookie('cs-skin', skin);
                skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
            });

            if (getCookie('cs-skin')) {
                var skin = getCookie('cs-skin');
                $('#swicth-style').attr('href', themes[skin]);
                $this = $('.li-skinitem span[rel=' + skin + ']');
                $this.addClass('cs-skin-on');
                skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
            }
            //调用抖动接口
            artDialog.fn.shake = function () {
                var style = this.DOM.wrap[0].style,
                    p = [4, 8, 4, 0, -4, -8, -4, 0],
                    fx = function () {
                        style.marginLeft = p.shift() + 'px';
                        if (p.length <= 0) {
                            style.marginLeft = 0;
                            clearInterval(timerId);
                        }
                    };
                p = p.concat(p.concat(p));
                timerId = setInterval(fx, 13);
                return this;
            };
            //加载菜单

        });

        var zTreeIMI;
        //加载ztree数据
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onTreeClick,
            }

        };
        function loadSystemZtreeIMI() {
            $('#systemztreeId').showLoading();
            var url = "<%=path%>/leftmenu";
            $.ajax({
                    url: url,
                    type: 'POST',
                    dataType: "json",
                    success: function (result) {
                        var value = result;
                        if (value.root != "") {
                            var message = eval(value.data);
                            $.fn.zTree.init($("#systemztreeId"), setting, message);
                            $("#systemztreeId").hideLoading();
                        }
                    },
                    error: function (data, textStatus, errorThrown) {
                    }
                }
            );
            return false;
        }

        function onTreeClick(e, treeId, treeNode) {
            if (treeNode.menuView && treeNode.menuView != "") {
                var m_name = treeNode.name;
                var url = treeNode.menuView;
                addTabto(m_name, url);
            }
        }
        //art 引用
        (function (config) {
            config['lock'] = true;
            config['fixed'] = true;
            config['okVal'] = '确定';
            config['cancelVal'] = '取消';
            // [more..]
        })(art.dialog.defaults);

        //左边树的点击 事件
        function menuClick(node) {
            var target = node.target;

            if (node.menutype == 0) {
                return;
            }
            $(this).tree('toggle', target);

            var attrmenu = target.attributes;
            if (attrmenu != '' && attrmenu != null && node.attributes != undefined) {
                var m_name = "";
                var url_text = node.attributes.menucontrol;
                var m_iconcls = node.attributes.ename;
                if (url_text == "ok") {
                    m_name = "数据信息管理";
                } else {
                    m_name = node.text;
                }

                $("#hide_left_menumodule_ids").val(m_iconcls);
                var url_ = node.attributes.menuview + "?module=" + m_iconcls;
                addTabto(m_name, url_);
            }
        }
        //添加Tab有效果
        function addTabto(title, url) {
            if (url) {
                var href = ctx + url;
                var content = createFrame(href);
            } else {
                var href = ctx + "/webi/error.jsp";
                var content = createFrame(href);
            }
            if ($('#tabs').tabs('exists', title) && Imi.isNotVal(url)) {
                $('#tabs').tabs('select', title);//选中并刷新
                var currTab = $('#tabs').tabs('getSelected');
                $('#tabs').tabs('close', title);
                $('#tabs').tabs('add', {
                    title: title,
                    closable: true,
                    content: content,
                    tools: [{
                        iconCls: 'icon-mini-refresh',
                        handler: function () {
                            refreshTab(title);
                        }
                    }]
                });
            } else {
                //URL 是否有效的路经
                if (Imi.isNotVal(url) && url != "-") {
                    $('#tabs').tabs('add', {
                        title: title,
                        content: content,
                        closable: true,
                        tools: [{
                            iconCls: 'icon-mini-refresh',
                            handler: function () {
                                refreshTab(title);
                            }
                        }]
                    });
                } else {
                    //错误的地址 跳到错误的页面
                    if (!$('#tabs').tabs('exists', title)) {
                        $('#tabs').tabs('select', title);
                        $('#tabs').tabs('add', {
                            title: title,
                            content: content,
                            closable: true
                        });
                    }
                }
            }
            tabClose();
        }
        function createFrame(url) {
            var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:98%;"></iframe>';
            return s;
        }

        function tabClose() {
            /*双击关闭TAB选项卡*/
            $(".tabs-inner").dblclick(function () {
                var subtitle = $(this).children(".tabs-closable").text();
                $('#tabs').tabs('close', subtitle);

            });
            /*为选项卡绑定右键*/
            $(".tabs-inner").bind('contextmenu', function (e) {
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
                var subtitle = $(this).children(".tabs-closable").text();
                $('#mm').data("currtab", subtitle);
                $('#tabs').tabs('select', subtitle);
                return false;
            });
        }
        function refreshTab(title) {
            var centerTabs = $('#tabs');
            var tab = centerTabs.tabs('getTab', title);
            centerTabs.tabs('update', {
                tab: tab,
                options: tab.panel('options')
            });
        }
        //绑定右键菜单事件
        function tabCloseEven() {
            //刷新
            $('#mm-tabupdate').click(function () {
                var currTab = $('#tabs').tabs('getSelected');
                var url = currTab.panel('options').href;
                var title = currTab.panel('options').title;
                if (url != undefined && title != '首页') {

                    $('#tabs').tabs('select', title);//选中并刷新
                    $('#tabs').tabs('close', title);
                    $('#tabs').tabs('add', {
                        title: title,
                        href: url,
                        closable: true
                    });
                }
                tabClose();
            });
            //关闭当前
            $('#mm-tabclose').click(function () {
                var currtab_title = $('#mm').data("currtab");
                $('#tabs').tabs('close', currtab_title);
            });
            //全部关闭
            $('#mm-tabcloseall').click(function () {
                $('.tabs-inner span').each(function (i, n) {
                    var t = $(n).text();
                    if (t != '首页') {
                        $('#tabs').tabs('close', t);
                    }
                });
            });
            //关闭除当前之外的TAB重置密码

            $('#mm-tabcloseother').click(function () {
                var prevall = $('.tabs-selected').prevAll();
                var nextall = $('.tabs-selected').nextAll();
                if (prevall.length > 0) {
                    prevall.each(function (i, n) {
                        var t = $('a:eq(0) span', $(n)).text();
                        if (t != '首页') {
                            $('#tabs').tabs('close', t);
                        }
                    });
                }
                if (nextall.length > 0) {
                    nextall.each(function (i, n) {
                        var t = $('a:eq(0) span', $(n)).text();
                        if (t != '首页') {
                            $('#tabs').tabs('close', t);
                        }
                    });
                }
                $('#mm').menu('hide');
                return false;
            });
            //关闭当前右侧的TAB
            $('#mm-tabcloseright').click(function () {
                var nextall = $('.tabs-selected').nextAll();
                if (nextall.length == 0) {
                    //msgShow('系统提示','后边没有啦~~','error');
                    return false;
                }
                nextall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    $('#tabs').tabs('close', t);
                });
                $('#mm').menu('hide');
                return false;
            });
            //关闭当前左侧的TAB
            $('#mm-tabcloseleft').click(function () {
                var prevall = $('.tabs-selected').prevAll();
                if (prevall.length == 0) {
                    return false;
                }
                prevall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    $('#tabs').tabs('close', t);
                });
                $('#mm').menu('hide');
                return false;
            });

            //退出
            $("#mm-exit").click(function () {
                $('#mm').menu('hide');
            })
        }
        function setCookie(name, value) {//两个参数，一个是cookie的名子，一个是值
            var Days = 30; //此 cookie 将被保存 30 天
            var exp = new Date();    //new Date("December 31, 9998");
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
            document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
        }

        function getCookie(name) {//取cookies函数
            var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
            if (arr != null) return unescape(arr[2]);
            return null;
        }
        //当前系统时间
        function currentTime() {
            var d = new Date(), str = '';
            var td = d.getDay();
            var f;
            if (td == 0) {
                f = "星期天";
            } else if (td == 1) {
                f = "星期一";
            } else if (td == 2) {
                f = "星期二";
            } else if (td == 3) {
                f = "星期三";
            } else if (td == 4) {
                f = "星期四";
            } else if (td == 5) {
                f = "星期五";
            } else {
                f = "星期六";
            }
            var hours = d.getHours();
            var hf = "";
            if (hours < 13) {
                hf = "上午 ";
            } else {
                hf = "下午 ";
            }
            str += d.getFullYear() + '年';
            str += d.getMonth() + 1 + '月';
            str += d.getDate() + '日 &nbsp;';
            str += hf + hours + ':';
            str += d.getMinutes() + ':';
            str += d.getSeconds() + '&nbsp; ' + f;
            return str;
        }
        setInterval(function () {
            $('#time_ids').html(currentTime)
        }, 1000);

        /**
         *退出
         */
        function exitSys() {
            var dialogexit = art.dialog({
                lock: true,
                background: '#600',
                opacity: 0.87,
                content: '您确定要退出本系统吗？',
                icon: 'error',
                ok: function () {

                    $.ajax({
                        type: 'POST',
                        url: ctx2 + "/login/loginout.api",
                        success: function (value) {
                            var flag = value;
                            if (flag) {

                            }
                        },
                        error: function () {
                            //$.messager.alert('报错','系统在退出时，出现异常！', 'error');
                        }
                    });
                    showProcess(true, '温馨提示', "退出登录成功，即将跳转至登录页");
                    setTimeout(function () {
                        showProcess(false);
                        location.href = ctx2 + '/login';
                    }, 500);
                    return false;
                },
                cancel: true
            });
            dialogexit.shake();
            return false;
        }

        //重置密码
        function resetClk() {
            win = parent.Imi.dialog({
                title: '修改密码',
                href: ctx + "/profile/showChangePassword",
                width: 400,
                height: 336,
                buttons: [{
                    text: '确定',
                    iconCls: 'icon-save',
                    handler: function () {
                        //执行创建方法：
                        var f = win.find('form');
                        var floag = f.form('validate');
                        if (floag) {
                            restPwd(f);
                        }
                    }
                }, {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        Imi.winclose(win);
                    }
                }]
            });
            return false;
        }
        //重置密码提交数据
        function restPwd(uiFrom) {
            var oldpwd = $("#oldPassword").textbox("getValue");
            var newpwd = $("#password").textbox("getValue");
            var conpwd = $("#passwordConfirm").textbox("getValue");

            if (eideaValidator.isEmpty(oldpwd)) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>旧密码不允许为空</font>"
                });
                return;
            }
            if (eideaValidator.isEmpty(newpwd)) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>新密码不允许为空</font>"
                });
                return;
            }

            if (newpwd.length < 6) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>新密码长度最少为6位</font>"
                });
                return;
            }

            if (eideaValidator.isEmpty(conpwd)) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>密码确认不允许为空</font>"
                });
                return;
            }

            if (newpwd != conpwd) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>两次密码输入不一致</font>"
                });
                return;
            }

            $.ajax({
                url: ctx + "profile/changePassword",
                type: 'POST',
                data: {
                    oldPassword: oldpwd,
                    newPassword: newpwd
                },
                beforeSend: function (XMLHttpRequest) {

                    showProcess(true, '温馨提示', '正在提交数据...');

                },
                success: function (data) {

                    var result = data;
                    if (result.success) {
                        Imi.winclose(win);
                        parent.Imi.messagerShow({
                            title: '提示',
                            msg: "<font color=blue>修改密码成功</font>"
                        });
                    } else {
                        parent.Imi.messagerShow({
                            title: '提示',
                            msg: "<font color=red>" + result.message + "</font>"
                        });
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {

                    showProcess(false);
                },
                error: function () {
                    showProcess(false);
                    $.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
                }

            });
            return false;
        }
        //web go event

        //菜单点击事件
        function attrTreeFn(node) {
            var attr_ = node.attributes;
            var tge_leaf = attr_.leaf;
            if (tge_leaf) {
                var href = attr_.href;
                var text = node.text;
                var gettitle = attr_.title;
                var n_t = "";
                if (null != gettitle) {
                    n_t = gettitle;
                } else {
                    n_t = text;
                }
                addTabto(n_t, href);
                console.log("n_t=" + n_t + " href=" + href);
            }
        }


    </script>

</head>
<body class="easyui-layout">
<input type="hidden" id="hide_left_menumodule_ids"/>
<div region="north" border="false" class="cs-north"
     style="height: 66px;overflow: hidden;background: url('<%=path%>/image/bg.png') repeat-x;">
    <div
            style="position: absolute;height: 85px;width:379px;overflow: hidden;padding-top:9px;padding-left:9px;font-size:30px">
        MIS管理中心
    </div>
    <div
            style="float:right;height:65px;right:0px;width:600px;padding-top:6px;">
        <div style="float:right;width:420px;height:35px;">
            <font style="color:#666666;">&nbsp;您好 : </font><span
                style="color:blue;">${MIS_USER.name}</span>
            <font style="color:#666666;">当前城市：<span id="currentCity"><c:out value="${MIS_USER.cityName}"/> </span>   <input type="button" onclick="openDialog();" value="更换"></font>
            &nbsp;&nbsp;
            <font style="color:#666666;">
                <span onclick="resetClk()" style="cursor:pointer;">修改密码</span> </font>&nbsp;&nbsp;&nbsp;&nbsp;<font
                style="color:#666666;cursor:pointer;"
                onclick="exitSys()">退出系统</font>

        </div>
        <div style="float:right;width:405px;height:30px;">
            <font style="color:#666666;">当前系统日期： </font><span id="time_ids"
                                                              style="font-size: 12px;"></span> <span
                style="float:right;padding-top:5px;">
					<div class="ui-skin-nav" style="height:2px;float:right;">
						<li class="li-skinitem" title="gray"><span class="gray"
                                                                   rel="gray"></span>
						</li>
						<li class="li-skinitem" title="black"><span class="black"
                                                                    rel="black"></span>
						</li>
						<li class="li-skinitem" title="metro"><span class="metro"
                                                                    rel="metro"></span>
						</li>
					</div> </span>
        </div>
    </div>

</div>

<div data-options="region:'west'" border="true" split="true"
     title="导航栏" class="cs-west">
    <div id="abp_accord_layout_ids" border="false">
        <div id="systemztreeId" class="ztree" style="overflow:auto;"></div>
    </div>
</div>
<div id="mainPanle" region="center" border="true" border="false">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="首页" style="padding-left:50px;padding-top:20px;"
             data-options="href:'<%=path%>/desktop'"></div>
    </div>
</div>


<div id="mm" class="easyui-menu cs-tab-menu">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseother">关闭其他</div>
    <div id="mm-tabcloseall">关闭全部</div>
</div>

<div id="selectCityDig" class="easyui-dialog" title="请选择城市" data-options="iconCls:'icon-save'"
     style="width:300px;height:200px;padding:10px">
    <select id="pro_sel" onchange="initCityOptions(this.value)"></select><br><br>
    <select id="city_sel"></select><br><br>
    <input type="button" id="selCityBtn" value="保存" onclick="saveCity()">
</div>

<script type="text/javascript">
    $(document).ready(function () {
        <c:choose>
        <c:when test="${MIS_USER.currentCityCode==null}">
        openDialog();
        </c:when>
        <c:otherwise>
        $('#selectCityDig').dialog('close');
        </c:otherwise>
        </c:choose>
    });

    function initProvinces(provinceCode, citycode) {
        $.ajax({
            url: "<%=path%>/provinces",
            type: 'GET',
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            success: function (result) {
                if (result.success) {
                    var proBuf = new StringBuffer();
                    var provinces = result.data;
                    for (var i = 0; i < provinces.length; i++) {
                        proBuf.append("<option value='").append(provinces[i].code).append("'>");
                        proBuf.append(provinces[i].name).append("</option>");
                    }
                    if (citycode == null) {
                        citycode = null;
                    }
                    $("#pro_sel").html(proBuf.toString());
                    if (provinceCode != null) {
                        $("#pro_sel").val(provinceCode);
                    }
                    if (provinceCode != null) {

                        initCityOptions(provinceCode, citycode);
                    }
                    else {
                        initCityOptions(provinces[0].code, citycode);
                    }

                    return;
                }
                else {
                    $.messager.alert("获取省份列表出错", result.message);
                }
            }
        });

    }
    function initCityOptions(provinceCode, citycode) {
        $.ajax({
            url: "<%=path%>/city/" + provinceCode,
            type: 'GET',
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            success: function (result) {
                if (result.success) {
                    var cityBuf = new StringBuffer();
                    var cities = result.data;
                    for (var i = 0; i < cities.length; i++) {
                        cityBuf.append("<option value='").append(cities[i].code).append("'>");
                        cityBuf.append(cities[i].name).append("</option>");
                    }
                    $("#city_sel").html(cityBuf.toString());
                    if (citycode != null) {
                        $("#city_sel").val(citycode);
                    }
                    return;
                }
                else {
                    $.messager.alert("获取城市列表出错", result.message);
                }
            }
        });
    }
    function openDialog() {


        $.ajax({
            url: "<%=path%>/userSession",
            type: 'GET',
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            success: function (result) {
                if (result.success) {
                    var user = result.data;
                    initProvinces(user.currentProvinceCode, user.currentCityCode);
                    return;
                }
                else {
                    $.messager.alert("获取省份列表出错", result.message);
                }
            }
        });
        //去除默认关闭按钮
        $('#selectCityDig').panel({
            closable:false
        });
        $('#selectCityDig').dialog('open');
    }
    function saveCity() {
        var cityCode = $("#city_sel").val();
        $.ajax({
            url: "<%=path%>/selectCity/" + cityCode,
            type: 'GET',
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            success: function (result) {
                if (result.success) {
                    $("#currentCity").html(result.data.cityName);
                    $('#selectCityDig').dialog('close');
                    return;
                }
                else {
                    $('#selectCityDig').dialog('close');
                    $.messager.alert("获取省份列表出错", result.message);
                }
            }
        });
    }
</script>
</body>
</html>
