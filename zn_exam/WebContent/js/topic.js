/**
 * Created by lichunyu on 17/1/8.
 */
angular.module("app.topic",["ng"])
    .controller("topicController",["$scope","$routeParams","$location","topicService",
        function($scope,$routeParams,$location,topicService){
            topicService.getAllTopics(function (data) {
                $scope.topics = data;
            })
        }]).factory("topicService",function ($http) {
            return{
                getAllTopics : function (handler) {
                        $http.get("http://127.0.0.1:8888/exam/manager/getAllTopics.action")
                            .success(function (data) {
                                    handler(data);
                            });
                }
            }
        });