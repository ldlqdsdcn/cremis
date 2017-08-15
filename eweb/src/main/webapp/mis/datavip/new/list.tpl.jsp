<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="listCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon icon-tasks"></i><eidea:label key="mis.datavip.search.title"/></a></li>
        </ol>
        <button type="button" class="btn  btn-primary btn-sm" id="search_but" data-toggle="modal"
                data-target="#searchModal"><eidea:label key="common.button.search"/></button>
        <button type="button" class="btn btn-primary btn-sm"
                ng-click="exportExcel()" >导出</button>
    </div>
    <div class="row-fluid">
        <div class="span12" >
            <table class="table table-hover table-striped table-condensed" >
                <thead class="">
                <tr>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--账号--%><eidea:label key="base.v2017.user.label.userId"/></th>
                    <th><%--姓名--%><eidea:label key="cityre.mis.datavip.userlist.name"/></th>
                    <th><%--用户类型--%><eidea:label key="cityre.mis.datavip.userlist.userType"/></th>
                    <th><%--注册时间--%><eidea:label key="cityre.mis.datavip.userlist.regTime"/></th>
                    <th><%--电话--%><eidea:label key="cityre.mis.datavip.userlist.userTel"/></th>
                    <th><%--Email--%><eidea:label key="cityre.mis.datavip.userlist.userEmail"/></th>
                    <th><%--状态--%><eidea:label key="cityre.mis.datavip.userlist.userState"/></th>
                    <th><%--账单号--%><eidea:label key="cityre.mis.datavip.userlist.payCode"/></th>
                    <th><%--账单状态--%><eidea:label key="cityre.mis.datavip.userlist.payState"/></th>
                    <th><%--账单金额--%><eidea:label key="cityre.mis.datavip.userlist.payCost"/></th>
                    <th><%--服务类型--%><eidea:label key="cityre.mis.datavip.userlist.serviceType"/></th>
                    <th><%--城市--%><eidea:label key="cityre.mis.datavip.userlist.city"/></th>
                    <th><%--查询级别--%><eidea:label key="cityre.mis.datavip.userlist.searchLevel"/></th>
                    <th><%--名称--%><eidea:label key="cityre.mis.datavip.userlist.contentName"/></th>
                    <th><%--附近--%><eidea:label key="cityre.mis.datavip.userlist.around"/></th>
                    <th><%--租售--%><eidea:label key="cityre.mis.datavip.userlist.sale"/></th>
                    <th><%--服务开始时间--%><eidea:label key="cityre.mis.datavip.paymentinfo.starttime"/></th>
                    <th><%--服务结束时间--%><eidea:label key="cityre.mis.datavip.paymentinfo.endtime"/></th>
                    <th><%--发票抬头--%><eidea:label key="cityre.mis.datavip.bill.invoiceTitle"/></th>
                </tr>
                </thead>
                <tbody>

                <tr ng-repeat="model in modelList track by $index" ng-class-even="success">
                    <td>{{(queryParams.pageNo-1)*queryParams.pageSize+$index+1}}</td>
                    <td>
                        {{model.uid}}
                    </td>
                    <td>
                        {{model.userName}}
                    </td>
                    <td>
                        {{model.dicUserType.userTypeName}}
                    </td>
                    <td>
                        {{model.regTime}}
                    </td>
                    <td>
                        {{model.payTel}}
                    </td>
                    <td>
                        {{model.email}}
                    </td>
                    <td>
                        {{model.flag}}
                    </td>
                    <td>
                        {{model.bills.billCode}}
                    </td>
                    <td>
                        {{model.bills.payFlag}}
                    </td>
                    <td>
                        {{model.userPaymentInfo.payAmount}}
                    </td>
                    <td>
                        {{model.dicUserType.note}}
                    </td>
                    <td>
                        {{model.bills.cityCode}}
                    </td>
                    <td>
                        {{model.bills.pLevel}}
                    </td>
                    <td>
                        {{model.bills.contentName}}
                    </td>
                    <td>
                        {{model.bills.pgps}}
                    </td>
                    <td>
                        {{model.bills.dealType}}
                    </td>
                    <td>
                        {{model.userPaymentInfo.startTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.userPaymentInfo.endTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.invoiceTitle}}
                    </td>
                </tr>
                </tbody>
            </table>
            <ul uib-pagination boundary-links="true" total-items="queryParams.totalRecords"
                ng-model="queryParams.pageNo"
                max-size="maxSize" first-text="<eidea:label key="common.label.firstpage"/>"
                previous-text="<eidea:label key="common.label.previouspage"/>"
                next-text="<eidea:label key="common.label.nextpage"/>"
                last-text="<eidea:label key="common.label.lastpage"/>"
                class="pagination-sm" boundary-link-numbers="true" rotate="false" items-per-page="queryParams.pageSize"
                ng-change="pageChanged()"></ul>
            <div class="text-left ng-binding padding_total_banner"><eidea:message key="common.msg.result.prefix"/><span>{{queryParams.totalRecords}}</span><eidea:message
                    key="common.msg.result.suffix"/></div>
        </div>
    </div>
</div>