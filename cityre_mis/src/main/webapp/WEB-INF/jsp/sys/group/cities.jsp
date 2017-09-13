<%@include file="/inc/taglib.jsp" %>
<%--
  User: 刘大磊
  Date: 2017/9/12
  Time: 15:26
  用户组城市权限维护
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userPanel" class="easyui-panel"
     style="width:100%;max-width:100%;padding:30px 60px;overflow-y:scroll;height: 95%">
    <c:out value="${groupCity.groupName}"/>&nbsp;&nbsp;
    <input type="hidden" name="groupId" id="groupId" value="${groupCity.groupId}"/>
    <br>
    <shiro:hasPermission name="group:edit">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="groupHelper.saveGroupCity()"
           style="width:80px">保存</a></shiro:hasPermission>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="groupHelper.goList()" style="width:80px">返回</a>
    <br>
    城市列表：<br>
    全选<input type="checkbox" onclick="groupHelper.selectAll(this.checked)">
    <table style="width: 100%">
        <c:forEach items="${groupCity.provinceBoList}" var="groupCity">
            <tr>
                <td style="width: 100px;"><c:out value="${groupCity.name}"/><input name="province"
                                                                                   onclick="groupHelper.citycheck(this.checked,'${groupCity.code}')"
                                                                                   type="checkbox">
                </td>
                <td id="${groupCity.code}">
                    <c:forEach items="${groupCity.cityBoList}" var="city">
                        <c:out value="${city.name}"/>
                        <input type="checkbox" value="${city.code}" ${city.checked?"checked":""} name="city">&nbsp;&nbsp;
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>