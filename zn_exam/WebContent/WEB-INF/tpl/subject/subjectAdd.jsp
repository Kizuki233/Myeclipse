<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="editingarea">
    <div class="c_flex"><span class="c_flexible"></span></div>
    <div class="Topictitle">添加题目</div>
    <div class="c_editview">
        <div class="Problem">
            <div class="Attributetit">题目属性</div>
            题目类型
            <select ng-model="subject.typeId"
                    ng-options="type.id as type.realName for type in types"></select>
            &nbsp;&nbsp;
            难度级别
            <select ng-model="subject.levelId"
                    ng-options="level.id as level.realName for level in levels"></select>
            &nbsp;&nbsp;
            所属方向
            <select ng-model="subject.departmentId"
                    ng-options="d.id as d.name for d in departmentes"></select>
            &nbsp;&nbsp;
            所属知识点
            <select ng-model="subject.topicId"
                    ng-options="t.id as t.title for t in topics | selectTopics :subject.departmentId "></select>
            &nbsp;&nbsp;
        </div>
        <div class="Problem">
            <div class="Attributetit">题目题干</div>
            <div class="Problemcontent">
                <textarea ng-model="subject.stem"
                          cols="60" rows="4"></textarea>
            </div>
        </div>
        <!--单选视图-->
        <div class="Answeroptions" ng-show="subject.typeId==1">
            <div class="Attributetit">答案选项<em>(通过勾选每个选项下面的框难吃时间点咳嗽打开)</em></div>
            <div class="c_condition"><span class="icon_add"><em class="icon_r" style="float: left">添加选项</em></span></div>
            <div class="Answercontent">
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>A</p><span>
                        <input select-option value="0" name="correct" type="radio" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[0]"
                                  cols="40" rows="4"></textarea>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>B</p><span>
                        <input select-option  value="1" name="correct" type="radio" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[1]"
                                  cols="40" rows="4"></textarea>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>C</p><span>
                        <input select-option value="2" name="correct" type="radio" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[2]"
                                  cols="40" rows="4"></textarea>
                    </div>

                    <div class="clear"></div>
                </div>
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>D</p><span>
                        <input select-option value="3" name="correct" type="radio" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[3]"
                                  cols="40" rows="4"></textarea>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <!--多选视图-->
        <div class="Answeroptions" ng-show="subject.typeId==2">
            <div class="Attributetit">答案选项<em>(通过勾选每个选项下面的框难吃时间点咳嗽打开)</em></div>
            <div class="c_condition"><span class="icon_add"><em class="icon_r" style="float: left">添加选项</em></span></div>
            <div class="Answercontent">
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>A</p><span>
                        <input select-option value="0" name="correct" type="checkbox" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[0]"
                                  cols="40" rows="4"></textarea>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>B</p><span>
                        <input select-option  value="1" name="correct" type="checkbox" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[1]"
                                  cols="40" rows="4"></textarea>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>C</p><span>
                        <input select-option value="2" name="correct" type="checkbox" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[2]"
                                  cols="40" rows="4"></textarea>
                    </div>

                    <div class="clear"></div>
                </div>
                <div class="Answerpart">
                    <div class="Answerpart_left">
                        <p>D</p><span>
                        <input select-option value="3" name="correct" type="checkbox" /></span>
                    </div>
                    <div class="Answerpart_right">
                        <textarea ng-model="subject.choiceContent[3]"
                                  cols="40" rows="4"></textarea>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="Problem" ng-show="subject.typeId==3">
            <div class="Attributetit">标准答案</div>
            <div class="Problemcontent">
                <textarea ng-model="subject.answer"
                          cols="60" rows="4"></textarea>
            </div>
        </div>

        <div class="Problem">
            <div class="Attributetit">答案解析</div>
            <div class="Problemcontent">
                <textarea ng-model="subject.fx"
                          cols="60" rows="4"></textarea>
            </div>
        </div>

        <div class="btn_left">
            <span class="btnL">
                <em class="btnR" ng-click="submit()">保存并继续</em>
            </span>
            <span class="btnL">
                <em class="btnR" ng-click="submitAndClose()">保存并关闭</em>
            </span>
        </div>
    </div>
</div>