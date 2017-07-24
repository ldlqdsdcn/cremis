<%--
User: 刘大磊
Date: 2017-05-04 13:22:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editFieldCtrl">
    <form role="form" name="editForm" novalidate ng-submit="save()" class="form-horizontal form-label-left input_mask">
        <table class="table table-borderless">
            <tr>
                <td class="control-label"><%--seqNo--%><eidea:label key="base.field.label.seqNo"/></td>
                <td class="form-group"><input type="text" class="form-control" id="seqNo"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.seqNo" type="label"/></eidea:message>"
                                              ng-model="fieldPo.seqNo" ng-minLength="1" ng-maxLength="11" required></td>
                <td class="control-label"><%--名称--%><eidea:label key="base.field.label.name"/></td>
                <td class="form-group"><input type="text" class="form-control" id="name"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.name" type="label"/></eidea:message>"
                                              ng-model="fieldPo.name" ng-minLength="2" ng-maxLength="200" required></td>
            </tr>
            <tr>
                <td class="control-label"><%--required--%><eidea:label key="base.field.label.required"/></td>
                <td class="form-group"><input type="checkbox" id="required" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.required"></td>
                <td class="control-label"><%--是否有效--%><eidea:label key="base.field.label.isactive"/></td>
                <td class="form-group"><input id="isactive" type="checkbox" ng-true-value="'Y'" ng-false-value="'N'"
                                              ng-model="fieldPo.isactive"></td>
            </tr>
            <tr>
                <td class="control-label"><%--fieldName--%><eidea:label key="base.field.label.fieldName"/></td>
                <td class="form-group"><input type="text" class="form-control" id="fieldName"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.fieldName" type="label"/></eidea:message>"
                                              ng-model="fieldPo.fieldName" ng-minLength="2" ng-maxLength="200" required>
                </td>
                <td class="control-label"><%--columnId--%><eidea:label key="base.field.label.columnId"/></td>
                <td class="form-group"><input type="text" class="form-control" id="columnId"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.columnId" type="label"/></eidea:message>"
                                              ng-model="fieldPo.columnId" ng-maxLength="11"></td>
            </tr>
            <tr>
                <td class="control-label"><%--创建人--%><eidea:label key="base.field.label.createdby"/></td>
                <td class="form-group "><input type="text" class="form-control" id="createdby"
                                               placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.createdby" type="label"/></eidea:message>"
                                               ng-model="fieldPo.createdby" ng-minLength="1" ng-maxLength="11" required>
                </td>
                <td class="control-label"><%--创建时间--%><eidea:label key="base.field.label.created"/></td>
                <td class="input-group date bootstrap-datetime"><input type="text" class="form-control" id="created"
                                                                       placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.created" type="label"/></eidea:message>"
                                                                       ng-model="fieldPo.created"
                                                                       uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"
                                                                       required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></td>

            </tr>
            <tr>
                <td class="control-label"><%--修改人--%><eidea:label key="base.field.label.updatedby"/></td>
                <td class="form-group"><input type="text" class="form-control" id="updatedby"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.updatedby" type="label"/></eidea:message>"
                                              ng-model="fieldPo.updatedby" ng-minLength="1" ng-maxLength="11" required>
                </td>
                <td class="control-label"><%--修改时间--%><eidea:label key="base.field.label.updated"/></td>
                <td class="input-group date bootstrap-datetime"><input type="text" class="form-control" id="updated"
                                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.updated" type="label"/></eidea:message>"
                                                                      ng-model="fieldPo.updated"
                                                                      uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"
                                                                      required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></td>
            </tr>
            <tr>
                <td class="control-label"><%--description--%><eidea:label key="base.field.label.description"/></td>
                <td class="form-group" colspan="3"><input type="text" class="form-control" id="description"
                                                          placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.description" type="label"/></eidea:message>"
                                                          ng-model="fieldPo.description" ng-maxLength="500"></td>
            </tr>
            <tr>
                <td class="control-label"><%--issameline--%><eidea:label key="base.field.label.issameline"/></td>
                <td class="form-group"><input type="checkbox" id="issameline" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.issameline"></td>

                <td class="control-label"><%--isencrypted--%><eidea:label key="base.field.label.isencrypted"/></td>
                <td class="form-group"><input type="checkbox" id="isencrypted" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.isencrypted"></td>
            </tr>
            <tr>
                <td class=" control-label"><%--inputType--%><eidea:label key="base.field.label.inputType"/></td>
                <td class="form-group"><select class="form-control" id="inputType"
                                               ng-model="fieldPo.inputType"
                                               ng-options="option.value as option.desc for option in inputTypeList"
                                               required></select></td>
                <td class="control-label"><%--defaultvalue--%><eidea:label key="base.field.label.defaultvalue"/></td>
                <td class="form-group"><input type="text" class="form-control" id="defaultvalue"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.defaultvalue" type="label"/></eidea:message>"
                                              ng-model="fieldPo.defaultvalue" ng-maxLength="2000"></td>
            </tr>
            <tr>
                <td class="control-label"><%--isDisplayed--%><eidea:label key="base.field.label.isDisplayed"/></td>
                <td class="form-group"><input type="checkbox" id="isDisplayed" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.isDisplayed"></td>
                <td class="control-label"><%--isreadonly--%><eidea:label key="base.field.label.isreadonly"/></td>
                <td class="form-group"><input type="checkbox" id="isreadonly" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.isreadonly"></td>
            </tr>
            <tr>
                <td class="control-label"><%--displayedlogic--%><eidea:label
                        key="base.field.label.displayedlogic"/></td>
                <td class="form-group" colspan="3"><input type="text" class="form-control" id="displayedlogic"
                                                          placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.displayedlogic" type="label"/></eidea:message>"
                                                          ng-model="fieldPo.displayedlogic" ng-maxLength="2000"></td>
            </tr>
            <tr>
                <td class="control-label"><%--displaylength--%><eidea:label key="base.field.label.displaylength"/></td>
                <td class="form-group"><input type="text" class="form-control" id="displaylength"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.displaylength" type="label"/></eidea:message>"
                                              ng-model="fieldPo.displaylength" ng-maxLength="11"></td>
                <td class="control-label"><%--seqnogrid--%><eidea:label key="base.field.label.seqnogrid"/></td>
                <td class="form-group"><input type="text" class="form-control" id="seqnogrid"
                                              placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.seqnogrid" type="label"/></eidea:message>"
                                              ng-model="fieldPo.seqnogrid" ng-maxLength="11"></td>
            </tr>
            <tr>
                <td class="control-label"><%--isdisplaygrid--%><eidea:label key="base.field.label.isdisplaygrid"/></td>
                <td class="form-group"><input type="checkbox" id="isdisplaygrid" ng-true-value="'TRUE'" ng-false-value="'FALSE'" ng-model="fieldPo.isdisplaygrid"></td>
                <td class="control-label"><%--isprinted--%><eidea:label key="base.field.label.isprinted"/></td>
                <td class="form-group"><input type="checkbox" id="isprinted" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.isprinted"></td>
            </tr>
            <tr>
                <td class="control-label"><%--isallowcopy--%><eidea:label key="base.field.label.isallowcopy"/></td>
                <td class="form-group"><input type="checkbox" id="isallowcopy" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.isallowcopy"></td>
                <td class="control-label"><%--isreport--%><eidea:label key="base.field.label.isreport"/></td>
                <td class="form-group"><input type="checkbox" id="isreport" ng-true-value="'TRUE'" ng-false-value="'FALSE'"
                                              ng-model="fieldPo.isreport"></td>
            </tr>
            <tr>
                <td class="control-label"><%--tabId--%><eidea:label key="base.field.label.tabId"/></td>
                <td class="form-group"><input type="text" class="form-control" id="tabId" ng-model="fieldPo.tabId" placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.tabId" type="label"/></eidea:message> " ng-minlength="1" ng-maxlength="11"> </td>
                <td class="control-label"><%--elementId--%><eidea:label key="base.field.label.elementId"/></td>
                <td class="form-group"><input type="text" class="form-control" id="elementId" ng-model="fieldPo.elementId" placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.elementId" type="label"/></eidea:message>" ng-minlength="1" ng-maxlength="11"> </td>
            </tr>
            <tr>
                <td class="control-label"><%--是否可添加--%><eidea:label key="base.field.label.isadded"/></td>
                <td class="form-group"><input type="checkbox" id="isadded" ng-model="fieldPo.isadded" ng-true-value="'TRUE'" ng-false-value="'FALSE'"></td>
                <td class="control-label"><%--是否可更新--%><eidea:label key="base.field.label.isupdated"/> </td>
                <td class="form-group"><input type="checkbox" id="isupdated" ng-model="fieldPo.isupdated" ng-true-value="'TRUE'" ng-false-value="'FALSE'"></td>
            </tr>
            <tr>
                <td class="control-label"><%--自动填充值--%><eidea:label key="base.field.label.autowiredValue"/></td>
                <td class="form-group"><input type="text" class="form-control" id="autowired" ng-model="fieldPo.autowiredValue" placeholder="<eidea:message key="common.please.input">
                                                        <eidea:param value="base.field.label.autowiredValue" type="label"/></eidea:message> " ng-maxlength="2000"> </td>
                <td class="control-label"><%--显示类型--%><eidea:label key="base.field.label.showType"/></td>
                <td class="form-group"><select class="form-control" id="showType" ng-model="fieldPo.showType" ng-options="option.value as option.desc for option in showTypeList"></select> </td>
            </tr>
        </table>
        <div class="form-group">
            <p class="text-right">
                <a ui-sref="editField({fieldId:null})" ui-sref-active="active" class="btn btn-default btn-sm" ng-show="canAdd"><%--新建--%><eidea:label key="common.button.create"/> </a>
                <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label
                        key="common.button.save"/></button>
               <a ui-sref="listField" ui-sref-active="active" class="btn btn-default btn-sm"><%--返回--%><eidea:label key="common.button.back"/></a>
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