<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
    .Catalogcontentup ul{
        margin: 15px;
    }
    .Catalogcontentup ul li{
        line-height: 22px;
    }
</style>
<!--路由参数：{{params}}-->
<div class="editingarea">
    <div class="c_flex"><span class="c_flexible"></span></div>
    <div class="c_editview">
        <div class="c_condition">
            <span><select><option>题干</option></select></span>
            <span class="pl5 " ><input type="text"></span>
        </div>
        <div class="divtable">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablea">
                <tr>
                    <td align="center" style="width: 50px;">题型</td>
                    <td align="left">
                        <div class="chose">
                            <a href="#/AllSubject/a/0/b/{{params.b}}/c/{{params.c}}/d/{{params.d}}"
                               class="{{params.a==0?'active3':''}}">全部</a>
                            <a href="#/AllSubject/a/{{type.id}}/b/{{params.b}}/c/{{params.c}}/d/{{params.d}}"
                               class="{{params.a==type.id?'active3':''}}"
                               ng-repeat="type in types">
                                {{type.realName}}
                            </a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="center">难度</td>
                    <td align="left">
                        <div class="chose">
                            <a href="#/AllSubject/a/{{params.a}}/b/0/c/{{params.c}}/d/{{params.d}}"
                               class="{{params.b==0?'active3':''}}">全部</a>
                            <a href="#/AllSubject/a/{{params.a}}/b/{{level.id}}/c/{{params.c}}/d/{{params.d}}"
                               class="{{params.b==level.id?'active3':''}}"
                               ng-repeat="level in levels">{{level.realName}}</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="center">方向</td>
                    <td align="left">
                        <div class="chose">
                            <a href="#/AllSubject/a/{{params.a}}/b/{{params.b}}/c/0/d/{{params.d}}"
                               class="{{params.c==0?'active3':''}}">全部</a>
                            <a href="#/AllSubject/a/{{params.a}}/b/{{params.b}}/c/{{department.id}}/d/{{params.d}}"
                               class="{{params.c==department.id?'active3':''}}"
                               ng-repeat="department in departmentes">{{department.name}}</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="center">知识点</td>
                    <td align="left">
                        <div class="chose">
                            <a href="#/AllSubject/a/{{params.a}}/b/{{params.b}}/c/{{params.c}}/d/0"
                               class="{{params.d==0?'active3':''}}">全部</a>
                            <a href="#/AllSubject/a/{{params.a}}/b/{{params.b}}/c/{{params.c}}/d/{{topic.id}}"
                               class="{{params.d==topic.id?'active3':''}}" ng-repeat="topic in topics">{{topic.title}}</a>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div class="Catalog">

            <div class="Catalog_right">
                <div class="Catalogtitle">
                    总计<em>15552</em>道题&nbsp;&nbsp;
                    提示：单击体面可显示答案和解析&nbsp;&nbsp;
                    <input type="checkbox" ng-model="isShow"/><b>显示答案和解析</b>&nbsp;&nbsp;
                    <div><i>1</i><a href="#" class="pageone">前</a><a href="#" class="pagetwo">后</a></div></div>

                <div class="Catalog_rightnei" ng-repeat="subject in subjects">
                    <div class="Catalogtitwo">
                        <b>题号：</b>{{subject.id}}&nbsp;&nbsp;
                        <b>题型：</b>{{subject.subjectType.realName}}&nbsp;&nbsp;
                        <b>难度：</b>{{subject.subjectLevel.realName}}&nbsp;&nbsp;
                        <b>审核状态：</b><span style="color:red">{{subject.checkState}}</span>&nbsp;&nbsp;
                        <b>上传人：</b>{{subject.user}}&nbsp;&nbsp;
                        <b>上传时间:</b>{{subject.uploadTime}}&nbsp;&nbsp;
                    </div>
                    <div class="Catalogcontent">
                        <div class="Catalogcontentup">
                            <!--题干-->
                            {{$index+1}} 、{{subject.stem}}
                            <!--题目选项-->
                            <ul>
                                <li ng-repeat="choice in subject.choices">
                                    {{choice.no}}
                                    {{choice.content}}
                                </li>
                            </ul>
                            <div ng-show="isShow">
                                <b>正确答案：</b>
                                {{subject.answer}}
                                <br>
                                <b>答案解析：</b>
                                {{subject.analysis}}
                            </div>
                        </div>
                        <div class="Catalogcontentdown">
                            <a href="#/PaperAdd/id/{{subject.id}}/stem/{{subject.stem}}/type/{{subject.subjectType.realName}}/topic/{{subject.topic.title}}/level/{{subject.subjectLevel.realName}}">加入试卷</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>