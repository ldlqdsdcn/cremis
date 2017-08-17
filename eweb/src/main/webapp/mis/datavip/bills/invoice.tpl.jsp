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
    <form role="form" name="addInvoiceForm" novalidate ng-submit="addInvoice()"
          class="form form-label-left input_mask">
        <div class="form-group"><%--开票号--%>开票号：
            <input type="text" class="form-control" id="updated"
                   ng-model="bill.invoiceNo">
        </div>
        <div class="input-group date bootstrap-datetime"> <%--开票时间：--%>开票时间：
            <input type="text" class="form-control" ng-model="bill.kpInvoiceTime"
                   uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
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