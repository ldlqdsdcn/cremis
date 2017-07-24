<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/13
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCheckboxCtrl">
    <form role="form" name="editForm" novalidate ng-submit="save()" class="form-horizontal form-label-left">
        <div class="row-fluid">
            <div class="span12">
                    </div>
                    </div>
            <div class="form-group">
            <label for="elementId" class="col-sm-2 text-right"><%--elementId--%><eidea:label key="base.elementCheckbox.label.elementId"/></label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="elementId" placeholder="<eidea:message key="common.please.input"><eidea:param value="base.elementCheckbox.label.elementId" type="label"/></eidea:message> " ng-model="elementCheckboxPo.elementId" ng-minlength="1"
                       ng-maxlength="11">
            </div>
        </div>
            <div class="form-group">
                <label for="checkedValue" class="col-sm-2 text-right"><%--checkedValue--%><eidea:label key="base.elementCheckbox.label.checkedvalue"/></label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="checkedValue" placeholder="<eidea:message key="common.please.input">
                            <eidea:param value="base.elementCheckbox.label.checkedvalue" type="label"/> </eidea:message>" ng-model="elementCheckboxPo.checkedValue" ng-maxlength="255">
                </div>
            </div>
            <div class="form-group">
                <label for="uncheckedValue" class="col-sm-2 text-right"><%--uncheckedValue--%><eidea:label key="base.elementCheckbox.label.uncheckedvalue"/></label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="uncheckedValue"  ng-model="elementCheckboxPo.uncheckedValue" placeholder="<eidea:message key="common.please.input">
                                    <eidea:param value="base.elementCheckbox.label.uncheckedvalue" type="label"/></eidea:message> " ng-maxlength="255">
                </div>
            </div>
        <div class="form-group">
            <p class="text-right">
                <a ui-sref="checkboxEdit({checkboxId:null})" ui-sref-active="active" class="btn btn-default btn-sm"><%--新建--%>
                    <eidea:label key="common.button.create"/></a>
                <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label
                        key="common.button.save"/></button>
                <a ui-sref="checkboxList" class="btn btn-default btn-sm" ui-sref-acitve="active" ><%--返回--%><eidea:label key="common.button.back"/></a>
            </p>
        </div>
                    <div class="form-group">
                        <p class="text-center" style="color: red"  >
                            {{message}}
                        </p>
                        <p class="text-center" style="color: red" ng-repeat="msg in errorMessages track by $index" ng-show="errorCode==3">
                            {{msg.message}}
                        </p>
                    </div>
            </div>
        </div>
    </form>
    <jsp:include page="/common/common_upload.jsp"/>
</div>