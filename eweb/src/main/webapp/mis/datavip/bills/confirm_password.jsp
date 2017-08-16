<%@ page import="com.dsdl.eidea.core.web.def.WebConst" %>
<%@ page import="com.dsdl.eidea.base.entity.bo.UserBo" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!--jsencryptcipher-->
<script src="<c:url value="/js/crypto-js.js"/>"></script>
<script src="<c:url value="/js/jsencrypt.js"/>"></script>
<script src="<c:url value="/js/security/AesAndRsaUtil.js"/>"></script>
<!--验证用户密码的模态框-->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div id="confirmPwd" class="modal-dialog" ng-app='confirmApp'>
        <div class="modal-content" ng-controller="confirmCtrl">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel"><eidea:label key="change.password.input.confirm.password"/></h4>
            </div>
            <form name="editForm" novalidate ng-submit="save()" method="post" class="form-horizontal form-label-left">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" id="hah" class="form-control" ng-model="password">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"><eidea:label key="common.button.confirm"/></button>
                </div>
                <div class="form-group">
                    <p class="text-center" style="color: red">
                        {{message}}
                    </p>
                    <p class="text-center" style="color: red" ng-repeat="msg in errorMessages track by $index"
                       ng-show="errorCode==3">
                        {{msg.message}}
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    var obj=document.getElementsByName("elementPassword");
        debugger;
    //密钥偏移量，用于aes加密
    var iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
    //用于生成初始key
    var key = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
    var app = angular.module('confirmApp', ['ngRoute', 'ui.bootstrap', 'jcs-autoValidate']);
    app.controller('confirmCtrl', function ($scope, $http,$rootScope) {
        $scope.password = null;
        var  userName = '<%=((UserBo)request.getSession().getAttribute(WebConst.SESSION_LOGINUSER)).getUsername()%>';
        $scope.save = function () {
            if($scope.password==null){
                bootbox.alert("请输入密码！");
                return false;
            }
            var usernameAndPassword = userName+"|"+$scope.password;
            var aesAndRsaUtil = new AesAndRsaUtil(iv,key);
            var enkey = aesAndRsaUtil.encryptkey();
            var cipherUsernameAndPassword = aesAndRsaUtil.aesencrypt(usernameAndPassword);
            var allparam = cipherUsernameAndPassword + "|" + enkey + "|" + iv;
            var password=window.passConfirm;
            $http.post("<c:url value="/mis/datavip/bills/confirmPassword"/>", {"password":allparam}).success(function (data) {
                if (data.success) {
                    $scope.message = data.data;
                    $('#confirmModal').modal('hide');
                    var url = "#/invoiceEdit?id="+password;
                    window.location.href = url;
                }else{
                    bootbox.alert("密码错误请重新输入！")
                }
            }).errors(function (response) {
                $scope.message=response.message;
            })

        }
    });
    angular.bootstrap(document.getElementById("confirmPwd"), ['confirmApp']);
</script>
