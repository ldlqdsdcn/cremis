<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/13
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="tab-content vertical-tab-content">
        <form role="form" name="editForm" novalidate ng-submit="save()" class="form-horizontal form-label-left">
            <jsp:include page="/common/common_edit_button.jsp"/>
            <div class="row-fluid">
                <div class="span12">
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 text-right"><%--name--%><eidea:label
                            key="base.element.label.name"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name"
                               placeholder="<eidea:message key="org.input.name"/>" ng-model="elementPo.name" required
                               ng-min="2" ng-maxlength="200">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputtype" class="col-sm-2 text-right"><%--element_input_type--%><eidea:label
                            key="base.element.label.input_type"/></label>
                    <div class="col-sm-10">
                        <select class="form-control" id="inputtype" ng-model="elementPo.elementInputType"
                                ng-options="option.value as option.desc for option in inputTypeList"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="showType" class="col-sm-2 text-right"><%--element_show_type--%><eidea:label
                            key="base.field.label.showType"/></label>
                    <div class="col-sm-10">
                        <select class="form-control" id="showType" ng-model="elementPo.elementShowType"
                                ng-options="option.value as option.desc for option in showTypeList"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="width" class="col-sm-2 text-right"><%--width--%><eidea:label
                            key="base.element.label.width"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="width"
                               placeholder="<eidea:message key="common.please.input"><eidea:param value="base.element.label.width" type="label"/></eidea:message> "
                               ng-model="elementPo.width" ng-maxlength="11">
                    </div>
                </div>
                <div class="form-group">
                    <label for="height" class="col-sm-2 text-right"><%--height--%><eidea:label
                            key="base.element.label.height"/></label>
                    <div class="col-sm-10 text-left"><input type="text" id="height"
                                                            class="form-control"
                                                            placeholder="<eidea:message key="common.please.input"><eidea:param value="base.element.label.height" type="label"/></eidea:message> "
                                                            ng-model="elementPo.height" ng-maxlength="11">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 text-right"><%--description--%><eidea:label
                            key="base.element.label.description"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="description"
                               placeholder="<eidea:message key="common.please.input"><eidea:param
        value="base.element.label.description" type="label"/></eidea:message> " ng-model="elementPo.description"
                               ng-maxlength="255">
                    </div>
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
            </div>
    </div>
    </form>
</div>
<jsp:include page="/common/common_upload.jsp"/>
</div>