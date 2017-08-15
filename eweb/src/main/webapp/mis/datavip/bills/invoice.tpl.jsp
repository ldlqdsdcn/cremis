<%--
  Created by IntelliJ IDEA.
  User: cityre
  Date: 2017/8/3
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="invoiceCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon icon-tasks"></i>服务操作</a></li>
        </ol>
    </div>
    <div>
        <h3>您正在操作的订单号：</h3><h4>{{billsPo.billCode}}</h4>
    </div>
    <form role="form" name="addInvoiceForm" novalidate ng-submit="addInvoice()"
          class="form-horizontal form-label-left input_mask">
        <table class="table table-borderless">
            <tr>
                <td class="control-label"><%--开票号--%>开票号：</td>
                <td class="form-group"><input type="text" class="form-control" id="updated"
                                              ng-model="bill.invoiceNo">
                </td>
            </tr>
            <tr>
                <td class="control-label"><%--开票时间：--%>开票时间：</td>
                <td class="input-group date bootstrap-datetime">
                    <input type="text" class="form-control" ng-model="bill.kpInvoiceTime"
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </td>
                </td>
            </tr>

        </table>
        <div class="form-group">
            <p class="text-right">
                <a href="#/list" class="btn btn-default btn-sm"><eidea:label key="common.button.back"/><%--返回--%></a>
                <button type="submit" class="btn btn-default btn-sm">开票</button>
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