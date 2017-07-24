<%--
  Created by 刘大磊.
  Date: 2017-05-02 15:41:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<html>
<head>
    <title><%--窗体--%><eidea:label key="window.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/inc/inc_ang_js_css.jsp" %>
    <%@include file="/common/common_header.jsp" %>
</head>
<body>
<div ng-app='myApp' class="content">
    <div ng-controller="tabCtrl" class="nav nav-tab vertical-tab">
        <uib-tabset vertical="true" active="active" type="tabs">
            <uib-tab index="0" heading="<eidea:label key="window.title"/> " select="windowGo()"
                     disable="windowShow"></uib-tab>
            <uib-tab index="1" heading="<eidea:label key="windowTrl.title"/> " select="windowTrlGo()"
                     disable="windowTrlShow"></uib-tab>
            <uib-tab index="2" heading="<eidea:label key="tab.title"/>" select="tabGo()" disable="tabShow"></uib-tab>
            <uib-tab index="3" heading="<eidea:label key="tabTrl.title"/>" select="tabTrlGo()"
                     disable="tabTrlShow"></uib-tab>
            <uib-tab index="4" heading="<eidea:label key="field.title"/>" select="fieldGo()"
                     disable="fieldShow"></uib-tab>
            <uib-tab index="5" heading="<eidea:label key="fieldTrl.title"/>" select="fieldTrlGo()"
                     disable="fieldTrlShow"></uib-tab>
            <uib-tab index="6" heading="<eidea:label key="fieldValidator.title"/>" select="fieldValidatorGo()"
                     disable="fieldValidatorShow"></uib-tab>
        </uib-tabset>
    </div>
    <div ui-view class="tab-content vertical-tab-content"></div>
</div>
<jsp:include page="/common/searchPage">
    <jsp:param name="uri" value="${uri}"/>
</jsp:include>
</body>
<script type="text/javascript">
    var app = angular.module('myApp', ['ngFileUpload', 'ngRoute', 'ui.router', 'ui.bootstrap', 'jcs-autoValidate'])
        .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/list');
            $stateProvider
                .state('list', {
                    url: '/list',
                    templateUrl: '<c:url value="/base/window/list.tpl.jsp"/>'
                })
                .state('edit', {
                    url: '/edit?id',
                    templateUrl: '<c:url value="/base/window/edit.tpl.jsp"/>'
                })
                .state('windowTrlList', {
                    url: '/windowTrllist',
                    templateUrl: '<c:url value="/base/windowTrl/list.tpl.jsp"/>'
                })
                .state('windowTrlEdit', {
                    url: '/windowTrlEdit?windowTrlId',
                    templateUrl: '<c:url value="/base/windowTrl/edit.tpl.jsp"/>'
                })
                .state('listTab', {
                    url: '/tabList',
                    templateUrl: '<c:url value="/base/tab/list.tpl.jsp"/> '
                })
                .state('editTab', {
                    url: '/editTab?tabId',
                    templateUrl: '<c:url value="/base/tab/edit.tpl.jsp"/> '
                })
                .state('listTabTrl', {
                    url: '/listTabTrl',
                    templateUrl: '<c:url value="/base/tabTrl/list.tpl.jsp"/> '
                })
                .state('editTabTrl', {
                    url: '/editTabTrl?tabTrlId',
                    templateUrl: '<c:url value="/base/tabTrl/edit.tpl.jsp"/>'
                })
                .state('listField', {
                    url: '/listField',
                    templateUrl: '<c:url value="/base/field/list.tpl.jsp"/>'
                })
                .state('editField', {
                    url: '/editField?fieldId',
                    templateUrl: '<c:url value="/base/field/edit.tpl.jsp"/> '
                })
                .state('listFieldTrl', {
                    url: '/listFieldTrl',
                    templateUrl: '<c:url value="/base/fieldTrl/list.tpl.jsp"/>'
                })
                .state('editFieldTrl', {
                    url: '/editFieldTrl?fieldTrlId',
                    templateUrl: '<c:url value="/base/fieldTrl/edit.tpl.jsp"/> '
                })
                .state('listFieldValidator', {
                    url: '/listFieldValidator',
                    templateUrl: '<c:url value="/base/fieldValidator/list.tpl.jsp"/> '
                })
                .state('editFieldValidator', {
                    url: '/editFieldValidator?fieldValidatorId',
                    templateUrl: '<c:url value="/base/fieldValidator/edit.tpl.jsp"/> '
                })
        }]);
    app.controller('tabCtrl', function ($scope, $rootScope, $state) {
        $scope.windowGo = function () {
            $rootScope.windowShow = false;
            $rootScope.windowTrlShow = true;
            $rootScope.tabShow = true;
            $rootScope.tabTrlShow = true;
            $rootScope.fieldShow = true;
            $rootScope.fieldTrlShow = true;
            $rootScope.fieldValidatorShow = true;
            $state.go('list');
        }
        $scope.windowTrlGo = function () {
            $rootScope.windowtrlShow = false;
            $rootScope.tabTrlShow = true;
            $rootScope.fieldShow = true;
            $rootScope.fieldTrlShow = true;
            $rootScope.fieldValidatorShow = true;
            $state.go('windowTrlList');
        }
        $scope.tabGo = function () {
            $rootScope.tabShow = false;
            $rootScope.tabTrlShow = true;
            $rootScope.fieldShow = true;
            $rootScope.fieldTrlShow = true;
            $rootScope.fieldValidatorShow = true;
            $state.go('listTab');
        }
        $scope.tabTrlGo = function () {
            $rootScope.tabTrlShow = false;
            $rootScope.fieldTrlShow = true;
            $rootScope.fieldValidatorShow = true;
            $state.go('listTabTrl');
        }
        $scope.fieldGo = function () {
            $rootScope.fieldTrlShow = true;
            $rootScope.fieldValidatorShow = true;
            $state.go('listField')
        }
        $scope.fieldTrlGo = function () {
            $state.go('listFieldTrl')
        }
        $scope.fieldValidatorGo = function () {
            $state.go('listFieldValidator')
        }
    });
    app.controller('listCtrl', function ($rootScope, $scope, $http, $window) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
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
        $scope.pageChanged = function () {
            $http.post("<c:url value="/base/window/list"/>", $scope.queryParams)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }


//批量删除
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
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/window/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };


//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        $scope.queryParams = {
            pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
            pageNo: 1, //当前页
            totalRecords: 0,//记录数
            init: true
        };
        $scope.pageChanged();

        buttonHeader.listInit($scope, $window);
    });
    app.controller('editCtrl', function ($rootScope, $stateParams, $routeParams, $scope, $http, $window, $timeout, Upload) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });
        $scope.message = '';
        $scope.windowPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $rootScope.id = $stateParams.id;
        var url = "<c:url value="/base/window/create"/>";
        if ($stateParams.id != null) {
            url = "<c:url value="/base/window/get"/>" + "?id=" + $stateParams.id;
            $rootScope.windowId = $stateParams.id;
            $rootScope.windowTrlShow = false;
            $rootScope.tabShow = false;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.windowPo = response.data;
                    $scope.tableId = $scope.windowPo.id;
                    $scope.getClientList();
                    $scope.getOrgList();
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.windowPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/window/saveForUpdated"/>';
                if ($scope.windowPo.id == null) {
                    postUrl = '<c:url value="/base/window/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.windowPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.windowPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.windowPo = {};
            var url = "<c:url value="/base/window/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.windowPo = response.data;
                        $scope.getClientList();
                        $scope.getOrgList();
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.windowPo.id == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }
        $scope.getClientList = function () {
            $http.get("<c:url value="/base/org/clients"/> ").success(function (data) {
                if (data.success) {
                    $scope.clientList = data.data;
                }
            }).error(function (data) {
                bootbox.alert(data.message);
            })
        }
        var param = {"queryParams": $scope.queryParams};
        $scope.getOrgList = function () {
            $http.post("<c:url value="/base/org/list"/> ", param).success(function (data) {
                if (data.success) {
                    $scope.orgList = data.data.data;
                }
            }).error(function (data) {
                bootbox.alert(data.message);
            })
        }
        buttonHeader.editInit($scope, $http, $window, $timeout, Upload, "/base");
    });
    app.controller('listWindowTrlCtrl', function ($scope, $http, $state, $rootScope) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
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
        $scope.pageChanged = function () {
            $http.post("<c:url value="/base/windowTrl/windowTrlList"/>", $rootScope.windowId)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }

//批量删除
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
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/windowTrl/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };


//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        $scope.queryParams = {
            pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
            pageNo: 1, //当前页
            totalRecords: 0,//记录数
            init: true
        };
        $scope.pageChanged();
    });
    app.controller('editWindowTrlCtrl', function ($scope, $http, $stateParams) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });

        $scope.message = '';
        $scope.windowTrlPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        var url = "<c:url value="/base/windowTrl/create"/>";
        if ($stateParams.windowTrlId != null) {
            url = "<c:url value="/base/windowTrl/get"/>" + "?id=" + $stateParams.windowTrlId;
        }
//        获取语言列表
        $http.get("<c:url value="/core/language/getLanguageList"/>").success(function(response){
            if (response.success){
                $scope.languageList = response.data;
            }else {
                bootbox.alert(response.message);
            }
        });
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.windowTrlPo = response.data;
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.windowTrlPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            var windowId = /^[0-9]+$/;
            if (!windowId.test($scope.windowTrlPo.windowId)) {
                $scope.message = "<eidea:label key="base.window.id.type.error"/> ";
                return false;
            }
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/windowTrl/saveForUpdated"/>';
                if ($scope.windowTrlPo.id == null) {
                    postUrl = '<c:url value="/base/windowTrl/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.windowTrlPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.windowTrlPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.windowTrlPo = {};
            var url = "<c:url value="/base/windowTrl/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.windowTrlPo = response.data;
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.windowTrlPo.id == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }
    });
    app.controller('listTabCtrl', function ($scope, $http, $stateParams, $rootScope) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
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
        $scope.pageChanged = function () {
            $http.post("<c:url value="/base/tab/tablist"/>", $rootScope.windowId)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }

//批量删除
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
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/tab/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };


//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        $scope.queryParams = {
            pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
            pageNo: 1, //当前页
            totalRecords: 0,//记录数
            init: true
        };
        $scope.pageChanged();
    });
    app.controller('editTabCtrl', function ($scope, $http, $rootScope, $stateParams, $state) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });
        $scope.message = '';
        $scope.tabPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        var url = "<c:url value="/base/tab/create"/>";
        if ($stateParams.tabId != null) {
            url = "<c:url value="/base/tab/get"/>" + "?id=" + $stateParams.tabId;
            $rootScope.tabTrlShow = false;
            $rootScope.fieldShow = false;
            $rootScope.tabid = $stateParams.tabId;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.tabPo = response.data;
                    $scope.getTableList();
                    $scope.getTableColumnList($scope.tabPo.tableId);
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.tabPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            var windowId = /^[0-9]+$/;
            if (!windowId.test($scope.tabPo.windowId)) {
                $scope.message = "<eidea:label key="base.window.id.type.error"/> ";
                return false;
            }
            if (!windowId.test($scope.tabPo.level)) {
                $scope.message = "<eidea:label key="base.tab.level.type.error"/> ";
                return false;
            }
            if (!windowId.test($scope.tabPo.sortno)) {
                $scope.message = "<eidea:label key="base.tab.sortno.type.error"/> ";
                return false;
            }
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/tab/saveForUpdated"/>';
                if ($scope.tabPo.id == null) {
                    postUrl = '<c:url value="/base/tab/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.tabPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.tabPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.tabPo = {};
            var url = "<c:url value="/base/tab/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.tabPo = response.data;
                        $scope.getTableList();
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.tabPo.id == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        };
        $scope.getTableList = function () {
            $http.get("<c:url value="/base/tab/getTablePoList"/> ").success(function (data) {
                if (data.success) {
                    $scope.tablePoList = data.data.data;
                }
            }).error(function () {
                bootbox.alert(data.message);
            })
        }
        $scope.getTableColumnList = function (id) {
            $http.post("<c:url value="/base/tab/getTableColumnList"/>", id).success(function (data) {
                if (data.success) {
                    $scope.tableColumnList = data.data;
                }
            }).error(function (data) {
                bootbox.alert(data.message);
            })
        }
    });
    app.controller('listTabTrlCtrl', function ($scope, $http, $stateParams, $state, $rootScope) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
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
        $scope.pageChanged = function () {
            $http.post("<c:url value="/base/tabTrl/tabTrlList"/>", $rootScope.tabid)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }

//批量删除
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
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/tabTrl/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };


//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        $scope.queryParams = {
            pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
            pageNo: 1, //当前页
            totalRecords: 0,//记录数
            init: true
        };
        $scope.pageChanged();
    });
    app.controller('editTabTrlCtrl', function ($scope, $http, $stateParams, $state) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });

        $scope.message = '';
        $scope.tabTrlPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $http.get("<c:url value="/core/language/getLanguageList"/> ").success(function (response) {
            if (response.success){
                $scope.languageList = response.data;
            }else {
                bootbox.alert(response.message);
            }
        })
        var url = "<c:url value="/base/tabTrl/create"/>";
        if ($stateParams.tabTrlId != null) {
            url = "<c:url value="/base/tabTrl/get"/>" + "?id=" + $stateParams.tabTrlId;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.tabTrlPo = response.data;
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.tabTrlPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            var tabId = /^[0-9]+$/
            if (!tabId.test($scope.tabTrlPo.tabId)) {
                $scope.message = "<eidea:label key="base.window.id.type.error"/> ";
                return false;
            }
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/tabTrl/saveForUpdated"/>';
                if ($scope.tabTrlPo.id == null) {
                    postUrl = '<c:url value="/base/tabTrl/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.tabTrlPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.tabTrlPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.tabTrlPo = {};
            var url = "<c:url value="/base/tabTrl/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.tabTrlPo = response.data;
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.tabTrlPo.id == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }
    });
    app.controller('listFieldCtrl', function ($scope, $http, $stateParams, $state, $rootScope) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
            }
        }
        $scope.canDelete = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                if ($scope.modelList[i].delFlag) {
                    return true;
                }
            }
            return false;
        };
        $scope.pageChanged = function () {
            $http.post("<c:url value="/base/field/fieldList"/>", $rootScope.tabid)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }

//批量删除
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
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/field/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };


//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        $scope.queryParams = {
            pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
            pageNo: 1, //当前页
            totalRecords: 0,//记录数
            init: true
        };
        $scope.pageChanged();
    });
    app.controller('editFieldCtrl', function ($scope, $http, $stateParams,$rootScope) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });

        $scope.message = '';
        $scope.fieldPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $http.get("<c:url value="/base/field/selectInputType"/>").success(function (response) {
            if (response.success) {
                var selectInputTypeList = $.parseJSON(response.data);
                $scope.inputTypeList = selectInputTypeList.fieldInputType;
            } else {
                bootbox.alert(response.message);
            }
        });
        $http.get("<c:url value="/base/field/selectShowType"/> ").success(function (response) {
            if (response.success) {
                var selectShowType = $.parseJSON(response.data);
                $scope.showTypeList = selectShowType.fieldShowType;
            } else {
                bootbox.alert(response.message);
            }
        })
        var url = "<c:url value="/base/field/create"/>";
        if ($stateParams.fieldId != null) {
            url = "<c:url value="/base/field/get"/>" + "?id=" + $stateParams.fieldId;
            $rootScope.fieldTrlShow=false;
            $rootScope.fieldValidatorShow=false;
            $rootScope.field = $stateParams.fieldId;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.fieldPo = response.data;
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.fieldPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            var sortno = /^[0-9]+$/;
            if (!sortno.test($scope.fieldPo.seqNo)) {
                $scope.message = "<eidea:label key="base.tab.sortno.type.error"/> ";
                return false;
            }
            if (!sortno.test($scope.fieldPo.displaylength)) {
                $scope.message = "<eidea:label key="base.field.displaylength.type.error"/> ";
                return false;
            }
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/field/saveForUpdated"/>';
                if ($scope.fieldPo.id == null) {
                    postUrl = '<c:url value="/base/field/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.fieldPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.fieldPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.fieldPo = {};
            var url = "<c:url value="/base/field/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.fieldPo = response.data;
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.fieldPo.id == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }
    });
    app.controller('listFieldTrlCtrl', function ($scope, $http, $stateParams, $state,$rootScope) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
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
        $scope.pageChanged = function () {
            $http.post("<c:url value="/base/fieldTrl/fieldTrlList"/>", $rootScope.field)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }

//批量删除
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
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/fieldTrl/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };


//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        $scope.queryParams = {
            pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
            pageNo: 1, //当前页
            totalRecords: 0,//记录数
            init: true
        };
        $scope.pageChanged();
    });
    app.controller('editFieldTrlCtrl', function ($scope, $http, $stateParams) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });

        $scope.message = '';
        $scope.fieldTrlPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $http.get("<c:url value="/core/language/getLanguageList"/> ").success(function (response) {
            if (response.success){
                $scope.languageList = response.data;
            }else{
                bootbox.alert(response.message);
            }
        });
        var url = "<c:url value="/base/fieldTrl/create"/>";
        if ($stateParams.fieldTrlId != null) {
            url = "<c:url value="/base/fieldTrl/get"/>" + "?id=" + $stateParams.fieldTrlId;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.fieldTrlPo = response.data;
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.fieldTrlPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            var field = /^[0-9]+$/;
            if (!field.test($scope.fieldTrlPo.fieldId)) {
                $scope.message = "<eidea:label key="base.field.id.type.error"/> ";
                return false;
            }
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/fieldTrl/saveForUpdated"/>';
                if ($scope.fieldTrlPo.id == null) {
                    postUrl = '<c:url value="/base/fieldTrl/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.fieldTrlPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.fieldTrlPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.fieldTrlPo = {};
            var url = "<c:url value="/base/fieldTrl/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.fieldTrlPo = response.data;
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.fieldTrlPo.id == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }
    });
    app.controller('listFieldValidatorCtrl', function ($scope, $http, $stateParams, $state,$rootScope) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
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
        $scope.pageChanged = function () {
            $http.post("<c:url value="/base/fieldValidator/fieldValidatorList"/>", $rootScope.field)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }

//批量删除
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
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/fieldValidator/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };


//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        $scope.queryParams = {
            pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
            pageNo: 1, //当前页
            totalRecords: 0,//记录数
            init: true
        };
        $scope.pageChanged();
    });
    app.controller('editFieldValidatorCtrl', function ($scope, $http, $stateParams, $state) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });
        $scope.message = '';
        $scope.fieldValidatorPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        var url = "<c:url value="/base/fieldValidator/create"/>";
        if ($stateParams.fieldValidatorId != null) {
            url = "<c:url value="/base/fieldValidator/get"/>" + "?id=" + $stateParams.fieldValidatorId;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.fieldValidatorPo = response.data;
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.fieldValidatorPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            $scope.message = "";
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/fieldValidator/saveForUpdated"/>';
                if ($scope.fieldValidatorPo.id == null) {
                    postUrl = '<c:url value="/base/fieldValidator/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.fieldValidatorPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.fieldValidatorPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.fieldValidatorPo = {};
            var url = "<c:url value="/base/fieldValidator/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.fieldValidatorPo = response.data;
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.fieldValidatorPo.id == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }

    });

    app.run([
        'bootstrap3ElementModifier',
        function (bootstrap3ElementModifier) {
            bootstrap3ElementModifier.enableValidationStateIcons(true);
        }]);
</script>
</html>
