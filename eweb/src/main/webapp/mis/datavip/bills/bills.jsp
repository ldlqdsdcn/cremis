<%--
  Created by 刘大磊.
  Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<html>
<head>
    <title><%--区域--%><eidea:label key="area.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/inc/inc_ang_js_css.jsp" %>
</head>
<body>
<div ng-app='myApp' ng-view class="content"></div>
<jsp:include page="/common/searchPage">
    <jsp:param name="uri" value="${uri}"/>
</jsp:include>
</body>
<div name="elementPassword" ng-show="false">

</div>
<%@include file="/mis/datavip/bills/confirm_password.jsp" %>
<script type="text/javascript">
    var app = angular.module('myApp', ['ngRoute', 'ui.bootstrap', 'jcs-autoValidate'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/list', {templateUrl: '<c:url value="/mis/datavip/bills/list.tpl.jsp"/>'})
                .when('/edit', {templateUrl: '<c:url value="/mis/datavip/bills/edit.tpl.jsp"/>'})
                .when('/invoiceEdit', {templateUrl: '<c:url value="/mis/datavip/bills/invoice.tpl.jsp"/> '})
                .otherwise({redirectTo: '/list'});
        }]);
    app.controller('listCtrl', function ($scope, $rootScope, $http) {
        //查询条件
        $scope.billCode = null;
        $scope.bigBillCode = null;
        $scope.alipayBillCode = null;
        $scope.payFlag = null;
        $scope.postInvoiceFlag = null
        $scope.invoiceType = null;
        $scope.postTypeCode = null;
        $scope.invoiceNo = null
        $scope.uid = null;
        $scope.typeCode = null;
        $scope.invoiceNoFlag = null;

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
        $scope.getBillsId = function (id) {
            window.passConfirm = id;
        }
        $scope.pageChanged = function () {
            var searchBillParams = {
                "uid": $scope.uid,
                "billCode": $scope.billCode,
                "bigBillCode": $scope.bigBillCode,
                "alipayBillCode": $scope.alipayBillCode,
                "typeCode": $scope.typeCode,
                "payFlag": $scope.payFlag,
                "invoiceNoFlag": $scope.invoiceNoFlag,
                "postTypeCode": $scope.postTypeCode,
                "invoiceNo": $scope.invoiceNo,
                "postInvoiceFlag": $scope.postInvoiceFlag,
                "invoiceType": $scope.invoiceType,
                "queryParams": $scope.queryParams
            }
            $http.post("<c:url value="/mis/datavip/bills/list"/>", searchBillParams)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                        $scope.modelList = null;
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
                        $http.post("<c:url value="/base/area/deletes"/>", param).success(function (data) {
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
//        获取支付类型
        $http.get("<c:url value="/mis/datavip/bills/getPayType"/>").success(function (response) {
            if (response.success) {
                $scope.payTypeList = response.data;
            } else {
                $scope.message = response.message;
            }
        });
        //        获取邮寄类型
        $http.get("<c:url value="/mis/datavip/bills/getPostType"/>").success(function (response) {
            if (response.success) {
                $scope.postTypeList = response.data;
            } else {
                $scope.message = response.message;
            }
        });
        //        获取支付状态
        $http.get("<c:url value="/mis/datavip/bills/getBillFlag"/>").success(function (response) {
            if (response.success) {
                var billFlag = $.parseJSON(response.data);
                $scope.billsFlag = billFlag.billFlagList
            } else {
                $scope.message = response.message;
            }
        });
        //        获取发票状态
        $http.get("<c:url value="/mis/datavip/bills/getInvoiceFlag"/>").success(function (response) {
            if (response.success) {
                var invoiceFlag = $.parseJSON(response.data);
                $scope.invoiceFlagType = invoiceFlag.invoiceTypeList
            } else {
                $scope.message = response.message;
            }
        });
        //        获取发票状态
        $http.get("<c:url value="/mis/datavip/bills/getFlag"/>").success(function (response) {
            if (response.success) {
                var flag = $.parseJSON(response.data);
                $scope.falgList = flag.flag;
            } else {
                $scope.message = response.message;
            }
        });
        //        获取发票状态
        $http.get("<c:url value="/mis/datavip/bills/getInvoiceState"/>").success(function (response) {
            if (response.success) {
                var invoice = $.parseJSON(response.data);
                $scope.invoiceStateList = invoice.invoiceState;
            } else {
                $scope.message = response.message;
            }
        });


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
        var searchBillParams = {
            "postTypeCode": $scope.postTypeCode,
            "postInvoiceFlag": $scope.postInvoiceFlag,
        }

        $scope.exportExcel=function () {
            var url ="<c:url value="/mis/datavip/bills/exportExl"/>" +"?uid="+$scope.uid+"&billCode="+$scope.billCode+"&bigBillCode="+$scope.bigBillCode+"&alipayBillCode="+$scope.alipayBillCode+"&typeCode="+$scope.typeCode+
                "&invoiceNo="+$scope.invoiceNo+"&invoiceFlag="+$scope.invoiceNoFlag+"&invoiceState="+$scope.invoiceType+"&payState="+$scope.payFlag
            +"&stateCode="+$scope.postTypeCode+"&invoiceFlagState="+$scope.postInvoiceFlag
            alert(url);
            window.open(url);
        }
    $scope.pageChanged();
    });
    //    开通服务和关闭服务
    app.controller('editCtrl', function ($scope, $http, $routeParams, $rootScope) {
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
        $scope.billsPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        if ($routeParams.billCode != null) {
            var url = "<c:url value="/mis/datavip/bills/getBills"/>" + "?billCode=" + $routeParams.billCode;
        }
        var postUrl = "<c:url value="/mis/datavip/bills/openService"/> "
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.billsPo = response.data;
                    $rootScope.billsId = $scope.billsPo.suid;
                    if ($scope.billsPo.serviceState == 2) {
                        postUrl = "<c:url value="/mis/datavip/userpaymentInfo/closeService"/> "
                    }
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.billsPo.id == null) || PrivilegeService.hasPrivilege('update');
                } else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response.data);
        });
        $scope.serviceEdit = function () {
            if ($scope.editForm.$valid) {
                $http.post(postUrl, $scope.billsPo).success(function (response) {
                    if (response.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.billsPo = response.data;
                    } else {
                        $scope.message = response.message;
                        $scope.errors = response.data;
                    }
                }).errors(function (response) {
                    bootbox.alert(response.message);
                })
            }
        }
    });
    app.controller('invoiceCtrl', function ($scope, $http, $routeParams) {
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
        $scope.bill = {};
        if ($routeParams.id == null) {
            bootbox.alert("参数错误！")
        }
        var url = "<c:url value="/mis/datavip/bills/get"/>" + "?id=" + $routeParams.id;
        $http.get(url).success(function (response) {
            if (response.success) {
                $scope.bill = response.data;
            } else {
                bootbox.alert(response.message);
            }
        });
        $scope.addInvoice = function () {
            if ($scope.addInvoiceForm.$valid) {
                var url = '<c:url value = "/mis/datavip/bills/addInvoice"/>'
                $http.post(url, $scope.bill).success(function (response) {
                    if (response.success) {
                        $scope.message = "<eidea:label key="base.save.success"/> "
                        $scope.bill = response.data;
                    } else {
                        $scope.message = response.message;
                        $scope.errors = response.data;
                    }
                }).errors(function (response, status, header, config) {
                    alert(JSON.stringify(response.data));
                })
            }

        }
    });

    app.run([
        'bootstrap3ElementModifier',
        function (bootstrap3ElementModifier) {
            bootstrap3ElementModifier.enableValidationStateIcons(true);
        }]);
</script>
</html>
