angular.module('myApp.project', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when("/projects", {
                templateUrl: "static/app/public/project/project.dashboard.html",
                controller: "ProjectDashboardCtrl"
            })
            .when("/project/:id", {
                templateUrl: "static/app/public/project/project.html",
                controller: "ProjectCtrl"
            })
            .when("/projects/:category", {
                templateUrl: "static/app/public/project/project.list.html",
                controller: "ProjectCategoryCtrl"
            })
            .when("/project/add/new", {
                templateUrl: "static/app/public/project/project-new.html",
                controller: "ProjectNewCtrl"
            })
            .when("/project/add/news/:id", {
                templateUrl: "static/app/public/project/news/project-news-new.html",
                controller: "ProjectNewNewsCtrl"
            })
            .when("/project/news/:id", {
                templateUrl: "static/app/public/project/news/project-news-page.html",
                controller: "ProjectNewsCtrl"
            })
    }])
    .controller('ProjectCtrl', ['$scope', '$location', "$routeParams", 'ProjectService', 'UserService',
        function ($scope, $location, $routeParams, ProjectService, UserService) {

        $scope.getProject = function (projectId) {

            $scope.project = ProjectService.get(projectId);

            console.log("ProjectCtrl: GET Project");
            console.log($scope.project);
        };

        $scope.getProject({id: $routeParams.id});

        $scope.forwardTo = function (path) {
            $location.path(path + "/" + $routeParams.id)
        };

        $scope.joinToProject = function () {
            UserService.addProject({projectId: $routeParams.id},{} ,function(responce){
                console.log("Join is successfully!")
            }, function(error){
                console.log("Join is Failed!")
            })
        };

    }]);