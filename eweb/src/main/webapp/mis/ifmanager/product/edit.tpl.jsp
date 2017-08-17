<%--
User: 刘大磊
Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon-fire"></i><eidea:label key="base.product.title"/></a></li>
        </ol>
    </div>
    <form role="form" name="editForm" novalidate ng-submit="save()">
        <div class="row-fluid">
            <div class="span12">
                <br>
                <div class="form-group">
                    <label for="productCode"><%--productCode--%><eidea:label
                            key="base.product.label.productCode"/></label>
                    <input class="form-control" id="productCode" type="text" ng-model="misProductPo.productCode"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.product.label.productCode" type="label"/></eidea:message> " required ng-blur="getExistProduct()"/>
                </div>
                <div class="form-group">
                    <label for="productName"><%--productName--%><eidea:label
                            key="base.product.label.productName"/></label>
                    <input class="form-control" id="productName" type="text" ng-model="misProductPo.productName"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.product.label.productName" type="label"/></eidea:message> " required/>
                </div>
                <div class="form-group">
                    <label for="platform"><%--platform--%><eidea:label key="base.product.label.platform"/></label>
                    <select id="platform" class="form-control" ng-model="misProductPo.platform" ng-options="option.key as option.value for option in platformTypeList"></select>
                </div>
                <div class="form-group">
                    <label for="createTime"><%--createTime--%><eidea:label key="base.product.label.createTime"/></label>
                    <input class="form-control" id="createTime" type="text" ng-model="misProductPo.createTime"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.product.label.createTime" type="label"/></eidea:message> "
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true"/>
                    <%--<span class="input-group-addon"><span--%>
                    <%--class="glyphicon glyphicon-calendar"></span></span>--%>
                </div>
                <div class="form-group">
                    <label for="updateTime"><%--createTime--%><eidea:label key="base.product.label.updateTime"/></label>
                    <input class="form-control" id="updateTime" type="text" ng-model="misProductPo.updateTime"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.product.label.updateTime " type="label"/></eidea:message> "
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true"/>
                    <%--<span class="input-group-addon"><span--%>
                    <%--class="glyphicon glyphicon-calendar"></span></span>--%>
                </div>
                <div class="form-group">
                    <p class="text-right">
                        <button type="reset" ng-click="create()" class="btn btn-default btn-sm"
                                ng-show="canAdd"><%--新建--%>
                            <eidea:label key="common.button.create"/></button>
                        <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label
                                key="common.button.save"/></button>
                        <a href="#/list" class="btn btn-default btn-sm"><%--返回--%><eidea:label
                                key="common.button.back"/></a>
                    </p>
                </div>
                <div class="form-group">
                    <p class="text-center" style="color: red">
                        {{message}}
                    </p>
                    <p>
                        <span ng-repeat="error in errors track by $index">
                            {{error.message}}
                        </span>
                    </p>
                </div>
            </div>
        </div>
    </form>
</div>
