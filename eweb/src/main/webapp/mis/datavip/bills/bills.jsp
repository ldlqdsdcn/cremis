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
<%--<jsp:include page="/common/searchPage">--%>
    <%--<jsp:param name="uri" value="${uri}"/>--%>
<%--</jsp:include>--%>
</body>
<script type="text/javascript">
    var app = angular.module('myApp', ['ngRoute', 'ui.bootstrap', 'jcs-autoValidate'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/list', {templateUrl: '<c:url value="/mis/datavip/bills/list.tpl.jsp"/>'})
                .when('/serviceEdit', {templateUrl: '<c:url value="/base/bills/service.tpl.jsp"/>'})
                .when('/invoiceEdit',{templateUrl:'<c:url value="/base/bills/invoice.tpl.jsp"/> '})
                .otherwise({redirectTo: '/list'});
        }]);
    app.controller('listCtrl', function ($scope,$rootScope, $http) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel=PrivilegeService.hasPrivilege('delete');
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag=$scope.delFlag;
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
            $http.post("<c:url value="/mis/bills/list"/>", $scope.queryParams)
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
                        $scope.queryParams.init=true;
                        var param={"queryParams":$scope.queryParams,"ids":ids};
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
//        服务开通的判断




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
    <%--<%--%>

				<%--if 	rs_datavip("w_pay_type")="1" or isnull(rs_datavip("w_pay_type")) then--%>

					<%--if rs_datavip("pay_flag")<>1 then--%>

						<%--response.write"<p><a href='open_service.asp?billsid="& rs_datavip("ids") & "&bigbillcode=" & rs_datavip("big_billcode") & "' target='_black'>开通服务</a></p>"--%>

					<%--end if--%>
				<%--elseif rs_datavip("pay_flag")=1 and isnull(rs_datavip("end_time")) then--%>
					<%--'2017.05.24 修改：如果支付成功，但是服务没开通，也显示开通服务的链接--%>
					<%--response.write"<p><a href='open_service.asp?billsid="& rs_datavip("ids") & "&bigbillcode=" & rs_datavip("big_billcode") & "' target='_black'>开通服务</a></p>"--%>
				<%--end if--%>
				<%----%>


					<%--'如果支付俄时间<当前的时间-1天的4点则需要财务输入密码--%>

				<%--if rs_datavip("invoice_no_flag")<>1 then--%>

					<%--str_time=date()&" 16:00:00"--%>

					<%--if rs_datavip("pay_updatetime")<dateadd("d",-1,str_time) then		--%>

					<%--para=rs_datavip("ids")--%>

				<%--%>--%>

    app.controller('serviceCtrl',function ($scope,$http,$routeParams) {
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

    });
    app.controller('invoiceCtrl',function ($scope,$http,$routeParams) {
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
        $scope.bill={};
        if($routeParams.id==null){
            bootbox.alert("参数错误！")
        }
        var url = "<c:url value="/base/bills/get"/> "+"?id="+$routeParams.id
        $http.get(url).success(function (response) {
            if (response.success){
                $scope.bill = response.data;
            }else {
                bootbox.alert(response.message);
            }
        });
        $scope.addInvoice = function () {
            if ($scope.addInvoiceForm.$valid){
                var url='<c:url value = "/base/bills/addInvoice"/>'
                $http.post(url,$scope.bill).success(function (response) {
                    if(response.success){
                        $scope.message="<eidea:label key="base.save.success"/> "
                        $scope.bill = response.data;
                    }else {
                        $scope.message = response.message;
                        $scope.errors=response.data;
                    }
                }).errors(function(response,status,header,config){
                    alert(JSON.stringify(response.data));
                })
            }

        }
    })
    app.run([
        'bootstrap3ElementModifier',
        function (bootstrap3ElementModifier) {
            bootstrap3ElementModifier.enableValidationStateIcons(true);
        }]);
</script>
</html>
