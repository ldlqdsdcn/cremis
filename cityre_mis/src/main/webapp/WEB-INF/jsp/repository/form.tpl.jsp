<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/29
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center">
    <input type="hidden" id="id" name="id">
    <table class="editTable">
        <tr>
            <td class="label">编号：</td>
            <td style="text-align: left">
                <input type="text" id="no" name="no" data-toggle="topjui-textbox"
                       data-options="required:true">
            </td>
        </tr>
        <tr>
            <td class="label">
                名称：
            </td>
            <td style="text-align: left">
                <input type="text" id="name" name="name" data-toggle="topjui-textbox"
                       data-options="required:true">
            </td>

        </tr>
        <tr>
            <td class="label">是否有效：</td>
            <td style="text-align: left">
                <select id="isactive" name="isactive" data-toggle="topjui-combobox">
                    <option value="Y">是</option>
                    <option value="N">否</option>
                </select>
            </td>

        </tr>
        <tr>
            <td class="label">备注信息</td>
            <td>
                <input type="text" name="desc" data-toggle="topjui-textarea" data-options="width:700,height:200">
            </td>
        </tr>
    </table>
</div>