<%--
  Created by IntelliJ IDEA.
  User: cityre
  Date: 2017/8/3
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="serviceCtrl">
    <form role="form" name="editForm" novalidate ng-submit="openService()" class="form-horizontal form-label-left input_mask">
        <table class="table table-borderless">
            <tr>
                <td class="control-label"><%--修改时间--%><eidea:label key="base.field.label.updated"/></td>
                <td class="input-group date bootstrap-datetime"><input type="text" class="form-control" id="updated"
                                                                       placeholder="<eidea:message key="common.please.input"><eidea:param value="base.field.label.updated" type="label"/></eidea:message>"
                                                                       ng-model="fieldPo.updated"
                                                                       uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"
                                                                       required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></td>
            </tr>
            <tr>
                <td class="control-label">
                    开始时间：
                </td>
                <td class="input-group date bootstrap-datetime">
                    <input type="text" class="form-control" ng-model="" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
            </tr>
            <tr>
                <td class="control-label">
                    结束时间：
                </td>
                <td class="input-group date bootstrap-datetime">
                    <input type="text" class="form-control" ng-model="" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
            </tr>
        </table>
        <div class="form-group">
            <p class="text-right">
                <a href="#/list" class="btn btn-default btn-sm" ><eidea:label key="common.button.back"/><%--返回--%></a>
                <button type="submit" class="btn btn-default btn-sm"><eidea:label key="common.button.save"/></button>
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
