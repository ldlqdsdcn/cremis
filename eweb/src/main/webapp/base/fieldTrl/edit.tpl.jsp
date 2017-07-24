<%--
User: 刘大磊
Date: 2017-05-02 15:46:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editFieldTrlCtrl">

    <div class="row-fluid">
        <div class="span12">
            <br>
            <form role="form" name="editForm" novalidate ng-submit="save()">
                <div class="form-group">
                    <label for="lang"><%--lang--%><eidea:label key="base.fieldTrl.label.lang"/></label>
                    <select id="lang" required class="form-control" ng-model="fieldTrlPo.lang"
                            ng-options="languageBo.code as languageBo.name for languageBo in languageList"/>
                </div>
                <div class="form-group">
                    <label for="fieldId"><%--fieldId--%><eidea:label key="base.fieldTrl.label.fieldId"/></label>
                    <input type="text" class="form-control" id="fieldId"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.fieldTrl.label.fieldId" type="label"/></eidea:message>"
                           ng-model="fieldTrlPo.fieldId" ng-minLength="1" ng-maxLength="11" required>

                </div>
                <div class="form-group">
                    <label for="name"><%--名称--%><eidea:label key="base.fieldTrl.label.name"/></label>
                    <input type="text" class="form-control" id="name"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.fieldTrl.label.name" type="label"/></eidea:message>"
                           ng-model="fieldTrlPo.name" ng-minLength="1" ng-maxLength="200" required>

                </div>
                <div class="form-group">
                    <label for="description"><%--description--%><eidea:label
                            key="base.fieldTrl.label.description"/></label>
                    <input type="text" class="form-control" id="description"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.fieldTrl.label.description" type="label"/></eidea:message>"
                           ng-model="fieldTrlPo.description" ng-maxLength="500">

                </div>
                <div class="form-group">
                    <label for="help"><%--帮助--%><eidea:label key="base.fieldTrl.label.help"/></label>
                    <input type="text" class="form-control" id="help"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.fieldTrl.label.help" type="label"/></eidea:message>"
                           ng-model="fieldTrlPo.help" ng-maxLength="3000">

                </div>


                <div class="form-group">
                    <p class="text-right">
                        <a ui-sref="editFieldTrl({fieldTrlId:model.id})"  ui-sref-active="active" class="btn btn-default btn-sm" ng-show="canAdd">
                            <eidea:label key="common.button.create"/> </a>
                        <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label
                                key="common.button.save"/></button>
                        <a ui-sref="listFieldTrl" ui-sref-active="active" class="btn btn-default btn-sm"><%--返回--%><eidea:label key="common.button.back"/></a>
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