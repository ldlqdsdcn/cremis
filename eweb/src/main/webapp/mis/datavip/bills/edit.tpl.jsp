<%--
  Created by IntelliJ IDEA.
  User: cityre
  Date: 2017/8/3
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon icon-tasks"></i>服务操作</a></li>
        </ol>
    </div>
    <div>
        <h3>您正在操作的订单号：</h3><h4>{{billsPo.billCode}}</h4>
    </div>
    <form role="form" name="editForm" novalidate ng-submit="serviceEdit()"
          class="form-horizontal form-label-left input_mask">
        <table class="table table-borderless">
            <tr>
                <td class="control-label"><%--修改时间--%><eidea:label key="base.field.label.updated"/></td>
                <td class="input-group date bootstrap-datetime"><input type="text" class="form-control" id="updated"
                                                                       ng-model="billsPo.userPaymentInfo.payTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"  ng-disabled="billsPo.serviceState==2">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
            </tr>
            <tr>
                <td class="control-label"><%--开始时间：--%><eidea:label key="base.mis.datavip.userpayinfo.starttime"/></td>
                <td class="input-group date bootstrap-datetime">
                    <input type="text" class="form-control" ng-model="billsPo.userPaymentInfo.startTime"
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="billsPo.serviceState==2">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
                </td>
            </tr>
            <tr>
                <td class="control-label"><%--结束时间：--%><eidea:label key="base.mis.datavip.userpayinfo.endtime"/></td>
                <td class="input-group date bootstrap-datetime">
                    <input type="text" class="form-control" ng-model="billsPo.userPaymentInfo.endTime"
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"  ng-disabled="billsPo.serviceState==2">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
            </tr>
        </table>
        <div class="form-group">
            <p class="text-right">
                <a href="#/list" class="btn btn-default btn-sm"><eidea:label key="common.button.back"/><%--返回--%></a>
                <button type="submit" class="btn btn-default btn-sm" ng-if="billsPo.serviceState==2">关闭服务</button>
                <button type="submit" class="btn btn-default btn-sm"ng-if="billsPo.serviceState!=2">开启服务</button>
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
