<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/13
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editSelectCtrl">
    <form role="form" name="editForm" novalidate ng-submit="save()" class="form-horizontal form-label-left">
        <div class="row-fluid">
            <div class="span12">
            </div>
        </div>
        <div class="form-group">
            <label for="sql" class="col-sm-2 text-right"><%--sql--%><eidea:label
                    key="base.elementSelect.label.sql"/></label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="sql" placeholder="<eidea:message key="org.input.remark"/>"
                       ng-model="elementSelectPo.sql"
                       ng-maxlength="4000">
            </div>
        </div>
        <div class="form-group">
            <label for="elementId" class="col-sm-2 text-right"><%--elementId--%><eidea:label
                    key="base.element.label.id"/></label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="elementId"
                       placeholder="<eidea:message key="org.input.remark"/>" ng-model="elementSelectPo.elementId"
                       ng-maxlength="11">
            </div>
        </div>
        <div class="form-group">
            <label for="keyType" class="col-sm-2 text-right"><%--keyType--%><eidea:label
                    key="base.elementSelect.label.keyType"/></label>
            <div class="col-sm-10">
                <select class="form-control" id="keyType" ng-model="elementSelectPo.keyType"
                        ng-options="option.key as option.desc for option in javaDataTypeList" required></select>
            </div>
        </div>
        <div class="form-group">
            <label for="valueType" class="col-sm-2 text-right"><%--valueType--%><eidea:label
                    key="base.elementSelect.label.valueType"/></label>
            <div class="col-sm-10">
                <select class="form-control" id="valueType" ng-model="elementSelectPo.valueType"
                        ng-options="option.key as option.desc for option in javaDataTypeList" required></select>
            </div>
        </div>
        <div class="form-group">
            <p class="text-right">
                <a ui-sref="itemEdit({itemId:null})" ui-sref-active="active" class="btn btn-default btn-sm"><%--新建--%>
                    <eidea:label key="common.button.create"/></a>
                <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label
                        key="common.button.save"/></button>
                <a ui-sref="itemList" class="btn btn-default btn-sm" ui-sref-acitve="active"><%--返回--%><eidea:label
                        key="common.button.back"/></a>
            </p>
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
<jsp:include page="/common/common_upload.jsp"/>
</div>