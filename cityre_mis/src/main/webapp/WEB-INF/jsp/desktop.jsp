<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<!-- 避免IE使用兼容模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<meta name="renderer" content="webkit">

	<link rel="stylesheet" type="text/css"
		  href="<c:url value="/topjui/plugins/font-awesome/css/font-awesome.min.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/topjui/plugins/bootstrap/css/bootstrap.min.css"/>">
	<link type="text/css" href="<c:url value="/topjui/css/timeaxis.css"/>" rel="stylesheet"/>
	<script type="text/javascript" src="<c:url value="/topjui/plugins/jquery/jquery.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/topjui/plugins/bootstrap/js/bootstrap.min.js"/>"></script>
	<script>
		/* 静态演示中获取contextPath，动态演示非必须 开始 */
        var contextPath = "";
        var firstPathName = window.location.pathname.split("/")[1];
        if (!(firstPathName == "html" || firstPathName == "json" || firstPathName == "topjui")) {
            contextPath = "/" + firstPathName;
        }
		/* 静态演示中获取contextPath，动态演示非必须 结束 */
	</script>
	<style>
		body {
			font: 12px/normal "microsoft yahei", "Times New Roman", "宋体", Times, serif;
			letter-spacing: 1px;
		}

		.container-fluid {
			margin-top: 5px !important;
		}

		.panel {
			margin-bottom: 10px;
		}

		.panel-heading {
			font-weight: bold;
		}

		.panel-body {
			height: 165px;
		}

		ul {
			margin: 0;
			/*padding-left: 10px;*/
		}

		ul li {
			line-height: 27px;
		}

		.colorPic {
			padding-left: 0;
		}

		.colorPic li {
			list-style: none;
			width: 46%;
			height: 120px;
			float: left;
			margin: 10px;;
		}

		/*div[class^=col-] {
            position: relative;
            min-height: 1px;
            padding-right: 5px;
            padding-left: 5px;
        }*/

		.row .no_gutter[class^="col-"], .row .no_gutter [class*="col-"] {
			padding-right: 5px;
			padding-left: 5px;
		}
	</style>
</head>

<body>

<div class="container-fluid" style="margin-top: 20px;">
	<div class="row">
		<div class="col-md-9">
			<div class="row no_gutter">
				<div class="col-md-6">

					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-edge"></i> TopJUI前端框架
						</div>
						<div class="panel-body">
							<ul>
								<li>TopJUI前端框架 - 最懂后端程序员的极速Web开发框架</li>
								<li>框架特性：EasyUI功能+组件扩展+性能优化+界面美化+便捷开发</li>
								<li>
									官方下载：<a href="http://www.topjui.com/download.html" target="_blank">http://www.topjui.com/download.html</a>
									<a style="color: red;" href="http://www.topjui.com/service.html" target="_blank">商用授权</a>
								</li>
								<li>
									开发文档：<a href="http://www.topjui.com/document/index/index.html" target="_blank">http://www.topjui.com/document/index/index.html</a>
								</li>
								<li>
									客户服务：
									<i class="fa fa-qq"></i> 251122361 <i class="fa fa-fax"></i> 0755-33942020 <i
										class="fa fa fa-envelope"></i> service@ewsd.cn
								</li>
							</ul>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-user"></i> 常见问题
						</div>
						<div class="panel-body">
							<ul>
								<li>
									<a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中首页初始化为什么有时不能全屏？</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中部分组件（如对话框）风格和整体风格不一致？</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中首页初始化为什么有时不能全屏？</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中部分组件（如对话框）风格和整体风格不一致？</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中部分组件（如对话框）风格和整体风格不一致？</a>
								</li>

							</ul>
						</div>
					</div>

					<!--<div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-user"></i> 常见问题
                        </div>
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中首页初始化为什么有时不能全屏？</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中部分组件（如对话框）风格和整体风格不一致？</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中首页初始化为什么有时不能全屏？</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中部分组件（如对话框）风格和整体风格不一致？</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:'http://www.topjui.com/document/question/fullscreen.html',title:'TopJUI前端框架'})">静态演示系统中部分组件（如对话框）风格和整体风格不一致？</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>-->

				</div>

				<div class="col-md-6">

					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-edge"></i> TopJUI与JAVA整合项目
						</div>
						<div class="panel-body">
							<ul>
								<li>
									系统简介：<a href="http://www.ewsd.cn/emis.html" target="_blank">企业级快速开发平台</a>
								</li>
								<li>TopJUI整合JAVA后端（Spring+Spring MVC+Hibernate+Mybatis）及第三方组件</li>
								<li>
									演示地址：<a href="http://demo.ewsd.cn/" target="_blank">http://demo.ewsd.cn</a>
								</li>
								<li>
									帐号：topjui，密码：topjui
								</li>
								<!--<li>
                                    本动态演示系统为 <a href="http://www.ewsd.cn" target="_blank">易网时代</a> 商业产品，欢迎洽谈合作！
                                </li>-->
							</ul>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-users"></i> 客商管理
						</div>
						<div class="panel-body">
							<ul>
								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'强化党内监督是全面从严治党重要保障'})">强化党内监督是全面从严治党重要保障</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：地方和部门主要负责同志要带头...'})">李克强：地方和部门主要负责同志要带头...</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：政务公开是常态不公开是例外'})">李克强：政务公开是常态不公开是例外</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'军委机关将总体实行“部—局—处”三级体制'})">军委机关将总体实行“部—局—处”三级体制</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'蔡奇就任北京代市长 王安顺因工作调动请辞'})">蔡奇就任北京代市长王安顺因工作调动请辞</a>
								</li>
							</ul>
						</div>
					</div>

					<!--<div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-user"></i> 客商管理
                        </div>
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'强化党内监督是全面从严治党重要保障'})">强化党内监督是全面从严治党重要保障</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：地方和部门主要负责同志要带头...'})">李克强：地方和部门主要负责同志要带头...</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：政务公开是常态不公开是例外'})">李克强：政务公开是常态不公开是例外</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'军委机关将总体实行“部—局—处”三级体制'})">军委机关将总体实行“部—局—处”三级体制</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'蔡奇就任北京代市长 王安顺因工作调动请辞'})">蔡奇就任北京代市长王安顺因工作调动请辞</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>-->

				</div>
			</div>

			<div class="row no_gutter">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart"></i> 快速入口
						</div>
						<div class="panel-body">

							<style>
								.link a {
									color: #fff;
									text-decoration: none;
								}

								.link .fa {
									height: 135px;
									line-height: 135px;
								}

								.link .bg {
									height: 135px;
									text-align: center
								}

								.link .bg:hover {
									background-color: #009688;
								}

								.bg-green {
									background-color: #5FB878;
								}

								.bg-blue {
									background-color: #1E9FFF;
								}

								.bg-yellow {
									background-color: #F7B824;
								}

								.bg-red {
									background-color: #FF5722;
								}

								.bg span {
									font-weight: bold;
									font-size: 30px;
									word-break: normal;
									white-space: pre-wrap;
								}

							</style>
							<div class="row">
								<div class="col-md-3 link">
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/complex/datagrid.html',title:'数据表格'})">
										<div class="bg bg-green">
											<div class="fa fa-users fa-3x"></div>
											<span>数据表格</span>
										</div>
									</a>
								</div>
								<div class="col-md-3 link">
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/complex/treegrid.html',title:'树形表格'})">
										<div class="bg bg-blue">
											<div class="fa fa-users fa-3x"></div>
											<span>树形表格</span>
										</div>
									</a>
								</div>
								<div class="col-md-3 link">
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/complex/edatagrid.html',title:'可编辑表格'})">
										<div class="bg bg-yellow">
											<div class="fa fa-users fa-3x"></div>
											<span>可编辑表格</span>
										</div>
									</a>
								</div>
								<div class="col-md-3 link">
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/form/textbox.html',title:'文本输入'})">
										<div class="bg bg-red">
											<div class="fa fa-users fa-3x"></div>
											<span>文本输入</span>
										</div>
									</a>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-md-3">
			<div class="row no_gutter">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-sticky-note"></i> 通知公告
						</div>
						<div class="panel-body">
							<ul>
								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'强化党内监督是全面从严治党重要保障'})">强化党内监督是全面从严治党重要保障</a>
								</li>
								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：地方和部门主要负责同志要带头...'})">李克强：地方和部门主要负责同志要带头...</a>
								</li>
								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：政务公开是常态不公开是例外'})">李克强：政务公开是常态不公开是例外</a>
								</li>
								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'军委机关将总体实行“部—局—处”三级体制'})">军委机关将总体实行“部—局—处”三级体制</a>
								</li>
								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'蔡奇就任北京代市长 王安顺因工作调动请辞'})">蔡奇就任北京代市长王安顺因工作调动请辞</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="row no_gutter">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-newspaper-o"></i> 公司新闻
						</div>
						<div class="panel-body">
							<ul>
								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'强化党内监督是全面从严治党重要保障'})">强化党内监督是全面从严治党重要保障</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：地方和部门主要负责同志要带头...'})">李克强：地方和部门主要负责同志要带头...</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'李克强：政务公开是常态不公开是例外'})">李克强：政务公开是常态不公开是例外</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'军委机关将总体实行“部—局—处”三级体制'})">军委机关将总体实行“部—局—处”三级体制</a>
								</li>

								<li>
									<a href="javascript:window.parent.addParentTab({href:contextPath + '/html/article/detail.html',title:'蔡奇就任北京代市长 王安顺因工作调动请辞'})">蔡奇就任北京代市长王安顺因工作调动请辞</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="row no_gutter">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-edge"></i> 服务器信息
						</div>
						<div class="panel-body">
							<ul>
								<li>JDK运行版本：1.8.0_60</li>
								<li>服务器运行时区：Asia/Shanghai</li>
								<li>服务器系统名称：Linux</li>
								<li>服务器系统架构：amd64</li>
								<li>服务器系统版本：2.6.32-573.22.1.el6.x86_64</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

</body>
</html>





