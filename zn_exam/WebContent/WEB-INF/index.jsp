<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html ng-app="app">
<head>
    <meta charset="utf-8">
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/1/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/1/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/1/css/EditingArea.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/1/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/1/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/1/css/zy.css">
    <script src="${pageContext.request.contextPath}/frameworks/jquery-2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/frameworks/angular-1.4.0/angular.min.js"></script>
    <script src="${pageContext.request.contextPath}/frameworks/angular-1.4.0/angular-route.min.js"></script>
    <!--自定义js-->
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/js/subject.js"></script>
    <script src="${pageContext.request.contextPath}/js/paper.js"></script>
    <script src="${pageContext.request.contextPath}/js/subjectType.js"></script>
    <script src="${pageContext.request.contextPath}/js/subjectLevel.js"></script>
    <script src="${pageContext.request.contextPath}/js/department.js"></script>
    <script src="${pageContext.request.contextPath}/js/topic.js"></script>
</head>

<body>
<div class="main">
	<div id="top">
    	<div class="top">
        	<div class="tk_logo"></div>
            <div class="t_sign">
            	<span>你好，管理员</span><a href="#" class="t_exit">退出</a>
            </div>
            <div class="t_icon">
            	<ul>
                	<li><a href="#" class="t1"><span>桌面</span></a></li>
                    <li><a href="#" class="t2"><span>换肤</span></a></li>
                    <li><a href="#" class="t3"><span>关于</span></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="cont">
    	<div class="centent">
        	<div class="left c_view">
            	<ul class="baseUI">
                	<li><a href="javascript:void(0);"><em class="base_basicset"></em><span>题库</span></a>
                        <ul>
                            <li class="current"><a href="#/AllSubject/a/0/b/0/c/0/d/0">题目管理</a></li>
                        </ul>
                    </li>
                    <li><a href="javascript:void(0);"><em class="base_userset"></em><span>试卷管理</span></a>
                        <ul>
                            <li><a href="#/PaperList">试卷列表</a></li>
                            <li><a href="#/PaperAdd/id/0/stem/0/type/0/topic/0/level/0">手工组卷</a></li>
                        </ul>
                    </li>
                    <li><a href="javascript:void(0);"><em class="base_teachergroup"></em><span>考试管理</span></a>
                        <ul>
                            <li><a href="#/subjectTypeManager">题型管理</a></li>
                            <li><a href="#/subjectLevelManager">难度管理</a></li>
                            <li><a href="#/departmentManager">方向管理</a></li>
                            <li><a href="#/topicManager">知识管理</a></li>
                        </ul>
                    </li>
                   
                </ul>
            </div>
            <!--右侧页面区-->
            <div class="right" ng-view>

            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div id="foot"><a href="#">上海师悦信息科技有限公司</a></div>
</div>
</body>
</html>
