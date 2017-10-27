/**
 * Created by lichunyu on 17/1/8.
 */

angular.module("app.department",["ng"])
    .controller("departmentController",["$scope","$routeParams","$location","departmentService",
        function($scope,$routeParams,$location,departmentService){
            departmentService.getAllDepartments(function(data){
                 $scope.departmentes = data;
            });
        }])
    .factory("departmentService",function ($http) {
        return {
                getAllDepartments: function (handler) {
                    $http.get("http://127.0.0.1:8888/exam/manager/getAllDepartmentes.action").success(function (data) {
                            handler(data);
                    });
                }
        }
    });