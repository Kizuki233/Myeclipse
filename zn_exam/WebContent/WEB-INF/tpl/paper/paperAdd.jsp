<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--{{pmodel}}-->
<div class="editingarea">
    <div class="c_flex"><span class="c_flexible"></span></div>
    <div class="Topictitle">添加试卷</div>
    <div class="c_editview">
        <div class="information">
            <div class="informationtop">
                <b style="float:left">试卷名称：</b>
                <b><input type="text" ng-model="pmodel.title" style="width: 310px"/></b>
            </div>
            <div class="informationdown">
                <div class="informationdownleft">
                    <b style="float:left">试卷说明：</b>
                    <b><textarea ng-model="pmodel.desc" ></textarea></b>
                </div>

                <div class="clear"></div>
            </div>
            <div class="informationtop">
                <b style="float:left">所属方向：</b>
                <b>
                    <select ng-model="pmodel.departmentId"
                            ng-options="d.id as d.name for d in dps"></select>
                </b>
            </div>
            <div class="informationtop">
                <b style="float:left">考试时间：</b>
                <b>
                    <input type="text"ng-model="pmodel.at"  >小时
                </b>
            </div>
        </div>
        <div class="c_condition">
            <b style="float: right">
                <span class="pl5">试卷总分：</span>
                <span class="pl5">
                    <input type="text" ng-model="pmodel.total" /></span>
            </b>
            <span class="icon_add" style="float: left">
                <em class="icon_r" style="float: left">
                    <a href="#/PaperSubjectList">添加题目</a>
                </em>
            </span>
            <span class="icon_add" style="float: left"><em class="icon_r" style="float: left">试卷预览</em></span>
        </div>
        <div class="divtable">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab1">
                <tbody>
                <tr>
                    <th  class="tc" width="300" ><span>试题描述</span></th>
                    <th class="tc"><span>题型</span></th>
                    <th class="tc"><span>考核知识点</span></th>
                    <th class="tc"><span>认知水平</span></th>
                    <th class="tc"><span>分数</span></th>
                    <th class="tc"><span>操作</span></th>
                </tr>

                <tr ng-repeat="s in pmodel.subjects">
                    <td  class="tc">{{s.stem}}</td>
                    <td class="tc">{{s.type}}</td>
                    <td class="tc">{{s.topic}}</td>
                    <td class="tc">{{s.level}}</td>
                    <td class="tc"><input type="text" ng-model="pmodel.scores[$index]"/></td>
                    <td class="tc"><div class="Onlinee"><a href="#" class="jians">天假</a><a href="#" class="yioan">天假</a></div></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="btn_left">
            <span class="btnL"><em class="btnR" ng-click="savePaper()">保存</em></span>
        </div>
    </div>
</div>
</div>
<div class="clear"></div>
</div>