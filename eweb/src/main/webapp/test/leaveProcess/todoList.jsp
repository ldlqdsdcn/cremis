<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/5/23
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<html>
<head>
    <title><%--用户Session--%><eidea:label key="leave.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/inc/inc_ang_js_css.jsp" %>
</head>
<body>
<div ng-app='myApp' ng-view class="content">
    <div  class="container-fluid" ng-controller="listCtrl">
        <div class="page-header" >
            <ol class="breadcrumb">
                <li><a href="javascript:;"><i class="icon icon-tasks"></i><eidea:label key="leave.title"/></a></li>
            </ol>
            <a href="#/edit" class="btn  btn-primary btn-sm" ng-show="canAdd"><eidea:label key="common.button.create"/></a>
            <button type="button" class="btn  btn-primary btn-sm" id="search_but" data-toggle="modal"
                    data-target="#searchModal"><eidea:label key="common.button.search"/></button>
            <button type="button" class="btn  btn-primary btn-sm" ng-disabled="!canDelete()"
                    ng-click="deleteRecord()" ng-show="canDel" ><eidea:label key="common.button.delete"/></button>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <table  class="table table-hover table-striped table-condensed">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="selectAll" style="margin:0px;" ng-change="selectAll()"  ng-model="delFlag"></th>
                        <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                        <th><%--标题--%><eidea:label key="test.leave.label.title"/></th>
                        <th><%--content--%><eidea:label key="test.leave.label.content"/></th>
                        <th><%--开始时间--%><eidea:label key="test.leave.label.bgnTime"/></th>
                        <th><%--结束时间--%><eidea:label key="test.leave.label.endTime"/></th>
                        <th><%--leaveUserId--%><eidea:label key="test.leave.label.leaveUserId"/></th>
                        <th>状态</th>
                        <th>任务创建时间</th>
                        <th>suspended</th>
                        <th>assignee</th>

                        <th><%--编辑--%><eidea:label key="common.button.edit"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr ng-repeat="model in modelList track by $index" ng-class-even="success">
                        <td>
                            <input type="checkbox" ng-model="model.delFlag">
                        </td>
                        <td>{{(queryParams.pageNo-1)*queryParams.pageSize+$index+1}}</td>
                        <td>
                            {{model.title}}
                        </td>
                        <td>
                            {{model.content}}
                        </td>
                        <td>
                            {{model.bgnTime|date:"yyyy-MM-dd HH:mm:ss"}}
                        </td>
                        <td>
                            {{model.endTime|date:"yyyy-MM-dd HH:mm:ss"}}
                        </td>
                        <td>
                            {{model.userName}}
                        </td>
                        <td>
                            <a target="_blank" href='<c:url value="/common/activiti/show/trace/"/>{{model.processInstanceId}}'  title="点击查看流程图">{{model.taskName }}</a>
                        </td>
                        <td>{{model.taskCreateTime }}</td>
                        <td>{{model.suspended ? "已挂起" : "正常" }}；<b title='流程版本号'>V: {{model.version }}</b></td>
                        <td>{{model.assignee }}</td>
                        <td ng-show="model.assignee!=null&&model.assignee!=''">

                            <button class="btn btn-primary btn-sm" ng-click="approve(model.taskId)">同意</button>
                            <button class="btn btn-primary btn-sm" ng-click="reject(model.taskId)">拒绝</button>
                        </td>
                        <td ng-show="model.assignee==null||model.assignee==''">
                            <button class="btn btn-primary btn-sm" ng-click="claim(model.taskId)">签收</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <ul uib-pagination boundary-links="true" total-items="queryParams.totalRecords" ng-model="queryParams.pageNo"
                    max-size="maxSize" first-text="<eidea:label key="common.label.firstpage"/>" previous-text="<eidea:label key="common.label.previouspage"/>" next-text="<eidea:label key="common.label.nextpage"/>" last-text="<eidea:label key="common.label.lastpage"/>"
                    class="pagination-sm" boundary-link-numbers="true" rotate="false" items-per-page="queryParams.pageSize"
                    ng-change="pageChanged()"></ul>
                <div class="text-left ng-binding padding_total_banner"><eidea:message key="common.msg.result.prefix"/><span>{{queryParams.totalRecords}}</span><eidea:message key="common.msg.result.suffix"/></div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/common/searchPage">
    <jsp:param name="uri" value="${uri}"/>
</jsp:include>
</body>
<script type="text/javascript">
    var app = angular.module('myApp', ['ui.bootstrap', 'jcs-autoValidate']);
    app.controller('listCtrl', function ($scope,$rootScope, $http) {
        $scope.allList = [];
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.canDel=PrivilegeService.hasPrivilege('delete');
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $http.get("<c:url value="/test/leaveProcess/todoList"/>")
                .success(function (response) {
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        $scope.updateList = function (data) {
            $scope.allList = data;
            $scope.queryParams.totalRecords  = $scope.allList.length;
            $scope.modelList.length = 0;
            $scope.pageChanged();
        };
        $scope.pageChanged = function (delF) {
            var bgn = ($scope.queryParams.pageNo - 1) * $scope.queryParams.pageSize;
            var end = bgn +  $scope.queryParams.pageSize;
            $scope.modelList.length = 0;
            if (delF == null) {
                delF = false;
            }
            for (var i = bgn; i < end && i < $scope.allList.length; i++) {
                var item = $scope.allList[i];
                item.delFlag = delF;
                $scope.modelList.push(item);

            }
        }
        $scope.canDelete = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                if ($scope.modelList[i].delFlag) {
                    return true;
                }
            }
            return false;
        }
        $scope.approve=function(taskId)
        {
            $http.post("<c:url value="/test/leaveProcess/complete/"/>"+taskId+"/"+true).success(function (data) {
                if (data.success) {
                    bootbox.alert({"message":"审批通过","callback": function() {

                        window.location.href="<c:url value="/test/leaveProcess/showTodoList"/>";
                    }});
                }
                else {
                    bootbox.alert(data.message);
                }

            });
        };
        $scope.reject=function(taskId)
        {
            $http.post("<c:url value="/test/leaveProcess/complete/"/>"+taskId+"/"+false).success(function (data) {
                if (data.success) {
                    bootbox.alert({"message":"审批拒绝","callback": function() {
                        window.location.href="<c:url value="/test/leaveProcess/showTodoList"/>";
                    }});
                }
                else {
                    bootbox.alert(data.message);
                }

            });
        }
        $scope.claim=function(taskId)
        {
            $http.post("<c:url value="/test/leaveProcess/taskClaim/"/>"+taskId+"").success(function (data) {
                if (data.success) {
                    bootbox.alert({"message":"签收成功","callback": function() {
                        window.location.href="<c:url value="/test/leaveProcess/showTodoList"/>";
                    }});
                }
                else {
                    bootbox.alert(data.message);
                }

            });
        };
        $scope.selectAll = function () {
            $scope.pageChanged($scope.delFlag);
        }
        $scope.deleteRecord = function () {

            bootbox.confirm({
                message: "<eidea:message key="common.warn.confirm.deletion"/>",
                buttons: {
                    confirm: {
                        label: '<eidea:label key="common.button.yes"/>',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '<eidea:label key="common.button.no"/>',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $http.post("<c:url value="/test/leave/deletes"/>", ids).success(function (data) {
                            if (data.success) {
                                bootbox.alert("<eidea:message key="common.warn.deleted.success"/>");
                                $scope.updateList(data.data);
                            }
                            else {
                                bootbox.alert(data.message);
                            }

                        });
                    }
                }
            });
        };
//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        if ($rootScope.listQueryParams != null) {
            $rootScope.queryParams = $scope.listQueryParams;
            $rootScope.queryParams.init = true;
        }
        else {
            $scope.queryParams = {
                pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
                pageNo: 1, //当前页
                totalRecords: 0,//记录数
                init: true
            };
            $rootScope.listQueryParams = $scope.queryParams;
        }
        $scope.pageChanged();
    });
</script>
</html>