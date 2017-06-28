<%--
User: 刘大磊
Date: 2017-06-28 15:47:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon-fire"></i><eidea:label key="city.title"/></a></li>
        </ol>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <br>
            <form role="form" name="editForm" novalidate  ng-submit="save()">
                <div class="form-group">
                    <label for="cityid" ><%--cityid--%><eidea:label key="base.city.label.cityid"/></label>
                            <input type="text" class="form-control" id="cityid" placeholder="<eidea:message key="common.please.input"><eidea:param value="base.city.label.cityid" type="label"/></eidea:message>" ng-model="cityPo.cityid" >

                </div>
                <div class="form-group">
                    <label for="city" ><%--city--%><eidea:label key="base.city.label.city"/></label>
                            <input type="text" class="form-control" id="city" placeholder="<eidea:message key="common.please.input"><eidea:param value="base.city.label.city" type="label"/></eidea:message>" ng-model="cityPo.city" >

                </div>
                <div class="form-group">
                    <label for="provinceid" ><%--provinceid--%><eidea:label key="base.city.label.provinceid"/></label>
                            <input type="text" class="form-control" id="provinceid" placeholder="<eidea:message key="common.please.input"><eidea:param value="base.city.label.provinceid" type="label"/></eidea:message>" ng-model="cityPo.provinceid" >

                </div>
                <div class="form-group">
                    <label for="isactive" ><%--是否有效--%><eidea:label key="base.city.label.isactive"/></label>
                            <input id="isactive" type="checkbox"  ng-true-value="'Y'" ng-false-value="'N'" ng-model="cityPo.isactive">
                </div>


                <div class="form-group">
                    <p class="text-right">
                        <button type="reset" ng-click="create()"  class="btn btn-default btn-sm" ng-show="canAdd"><%--新建--%><eidea:label key="common.button.create"/></button>
                        <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label key="common.button.save"/></button>
                        <a href="#/list" class="btn btn-default btn-sm"><%--返回--%><eidea:label key="common.button.back"/></a>
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
            </form>
        </div>
    </div>
</div>