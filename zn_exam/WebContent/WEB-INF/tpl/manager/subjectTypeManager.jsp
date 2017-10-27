<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="editingarea">
    <div class="c_flex"><span class="c_flexible"></span></div>
    <div class="c_editview">
        <h3>题型管理</h3>
        <div class="divtable">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab1">
                <tbody>
                <tr>
                    <th width="30" class="tc">选择</th>
                    <th class="tc"><span>名称（英文）</span></th>
                    <th  class="tc" width="300" ><span>真实名称（中文）</span></th>
                </tr>
                <tr ng-repeat="type in types">
                    <td class="tc"><input type="checkbox" value="{{type.id}}"></td>
                    <td class="tc">{{type.name}}</td>
                    <td class="tc">{{type.realName}}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="btn_left">
            <span class="btnL"><em class="btnR">添加</em></span>
            <span class="btnL"><em class="btnR">修改</em></span>
            <span class="btnL"><em class="btnR">删除</em></span>
        </div>
    </div>
</div>
</div>
<div class="clear"></div>
</div>