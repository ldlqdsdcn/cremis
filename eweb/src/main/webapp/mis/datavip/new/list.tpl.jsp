<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="listCtrl">
    <div class="page-header">
        <form role="form" name="editForm" novalidate ng-submit="pageChanged()" class="form-inline form-label-left">
            <table class="table table-borderless">
                <tr>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.user.useruid"/>:</td>
                    <td class="form-group"><input type="text" class="form-control" ng-model="uid"
                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="cityre.mis.datavip.user.useruid" type="label"/></eidea:message>">
                    </td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.userlist.userType"/>:</td>
                    <td class="form-group"><select class="form-control" ng-model="userType" ng-init="userType='null'"><option value=null>请选择</option><option ng-repeat="userType in userTypeList" value="{{userType.userTypeName}}">{{userType.userTypeName}}</option></select>
                    </td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.userlist.userTel"/>:</td>
                    <td class="form-group"><input type="text" class="form-control" ng-model="payTel"
                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="cityre.mis.datavip.userlist.userTel" type="label"/></eidea:message>">
                    </td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.userlist.payState"/>:</td>
                    <td class="form-group"><select class="form-control" ng-model="payFlag" ng-init="payFlag='null'"><option value="null">请选择</option><option ng-repeat="option in billsFlagList" value="{{option.key}}">{{option.value}}</option></select>
                    </td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.user.newUser"/>:</td>
                    <td class="form-group"><input type="checkbox" class="form-control" ng-model="newUser"
                                                  ng-true-value="'true'"
                                                  ng-false-value="'false'"></td>

                </tr>
                <tr>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.userlist.regTime"/></td>
                    <td class="input-group date bootstrap-datetime"><input type="datetime" class="form-control" ng-model="regStartTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.userlist.regTime"/>结束</td>
                    <td class="input-group date bootstrap-datetime"><input type="datetime" class="form-control" ng-model="regEndTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.paymentinfo.starttime"/>:</td>
                    <td class="input-group date bootstrap-datetime"><input type="datetime" class="form-control" ng-model="serviceStartTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.paymentinfo.endtime"/>:</td>
                    <td class="input-group date bootstrap-datetime"><input type="datetime" class="form-control" ng-model="serviceEndTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss"><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </td>
                    <td class="control-label">
                        <button type="submit" class="btn"><eidea:label key="common.button.search"/></button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-bordered table-hover table-striped table-condensed">
                <thead class="">
                <tr>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--账号--%><eidea:label key="cityre.mis.datavip.user.useruid"/></th>
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
                        {{model.regTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.payTel}}
                    </td>
                    <td>
                        {{model.email}}
                    </td>
                    <td ng-if="model.flag==1">
                        激活
                    </td>
                    <td ng-if="model.flag==-1">
                        无效
                    </td>
                    <td ng-if="model.flag==0">
                        未激活
                    </td>
                    <td>
                        {{model.bills.billCode}}
                    </td>
                    <td ng-if="model.bills.payFlag==0">
                        已申请
                    </td>
                    <td ng-if="model.bills.payFlag==1">
                        已支付
                    </td>
                    <td ng-if="model.bills.payFlag==2">
                        已进入支付页
                    </td>
                    <td>
                        <a href="#/list">{{model.userPaymentInfo.payAmount}}</a>
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
                    <%--租售类型 1=出售 2=出租 3=新楼盘 4=新楼盘开工在售 5=新楼盘已竣工 6=新楼盘未售--%>
                    <td ng-if="model.bills.dealType==1">
                        出售
                    </td>
                    <td ng-if="model.bills.dealType==2">
                        出租
                    </td>
                    <td ng-if="model.bills.dealType==3">
                        新楼盘
                    </td>
                    <td ng-if="model.bills.dealType==4">
                        新楼盘开工在售
                    </td>
                    <td ng-if="model.bills.dealType==5">
                        新楼盘已竣工
                    </td>
                    <td ng-if="model.bills.dealType==6">
                        新楼盘未售
                    </td>
                    <td ng-if="model.bills.dealType==null">
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