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
    <h3>将要操作的订单号{{billsPo.billCode}}的服务，请确认</h3>
    <p style="color: #c83737;text-align: center;font-family: 微软雅黑">
        注：正常情况下，开通服务的开始和结束时间是自动生成的，但在开发房价管家开通服务功能时,市场提出一些线下服务开通是需要人工指定服务开始和结束时间的，</br>
        所以以下两项内容为手动控制服务的开始时间和结束时间
    </p>
    <form role="form" name="editForm" novalidate ng-submit="serviceEdit()"
          class=" form-horizontal form-label-left input_mask">
        <table class="table table-borderless">
            <tr>
                <td class="control-label col-sm-1"><%--支付时间--%>支付时间：</td>
                <td class="col-sm-8">
                    <div class="input-group date bootstrap-datetime "><input type="text" class="form-control" id="updated"
                                                                              ng-model="billsPo.userPaymentInfo.payTime"
                                                                              uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"
                                                                              ng-disabled="billsPo.serviceState==2">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></div>
                </td>
                <td class="control-label"><%--开始时间：--%>服务开始时间：</td>
                <td class="input-group date bootstrap-datetime">
                    <input type="text" class="form-control" ng-model="billsPo.userPaymentInfo.startTime"
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="billsPo.serviceState==2">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
                </td>
            </tr>
            <tr>
                <td class="control-label"><%--结束时间：--%>服务结束时间：</td>
                <td class="input-group date bootstrap-datetime">
                    <input type="text" class="form-control" ng-model="billsPo.userPaymentInfo.endTime"
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="billsPo.serviceState==2">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
            </tr>
        </table>
        <div class="form-group">
            <p class="text-right">
                <a href="#/list" class="btn btn-default btn-sm"><eidea:label
                        key="common.button.back"/><%--返回--%></a>
                <button type="submit" class="btn btn-default btn-sm" ng-if="billsPo.serviceState==2">关闭服务</button>
                <button type="submit" class="btn btn-default btn-sm" ng-if="billsPo.serviceState!=2">开启服务</button>
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
