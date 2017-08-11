<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/inc/taglib.jsp"%>
<!--验证用户密码的模态框-->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
     aria-labelledby="confirmModalLabel"  hidden="true" ng-app="myApp">
    <div class="modal-dialog" ng-controller="confirmCtrl">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="confirmModalLabel"></h4>
            </div>
            <div class="modal-body">
                <form role="form" name="myform" class="form-inline">
                    <div class="form-group"><%--请输入确认密码--%>
                        <eidea:label key="change.password.input.confirm.password"/>
                        <input type="text" class="form-control" ng-model="" id="password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><eidea:label key="common.button.closed"/></button>
                <button type="button" class="btn btn-primary" ng-click="confirm()" ng-keyup="doConfirm($event)">
                    <eidea:label key="change.password.button.confirm.password"/><%--确认密码--%>
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    var app = angular.module('myApp', ['ngRoute', 'ui.bootstrap', 'jcs-autoValidate']).config(['$routeProvider', function ($routeProvider) {
    }]);
    app.controller('confirmCtrl',function ($scope,$http,$rootScope) {
        $scope.userlist=[];
        $scope.userPwd=[];
        var url = "<c:url value="/mis/datavip/new/get"/> "+"?suid="+'vip_1621345301264920718';
        $http.get(url).success(function (response) {
            if(response.success){
                $scope.userlist=response.data;
                window.location.href="<c:url value="/mis/datavip/bills/edit.tpl.jsp"/> "
            }else {
                bootbox.alert(response.message);
            }
        })
    })
</script>
