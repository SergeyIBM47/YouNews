angular.module('myApp.profile', ['ngRoute', 'myApp.services', 'myApp.user.services'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/profile', {
                    templateUrl: "static/app/public/profile/profile.html",
                    controller: "ProfileCtrl"
                }
            )
    }])
    .controller('ProfileCtrl', ['$scope', '$timeout','UserService', function ($scope, $timeout, UserService) {

        $scope.user = {
            firstName: "",
            lastName: "",
            nickName: "",
            email: "",
            phoneNumber: "",
            description: ""
        };

        $scope.accountSuccessFlag = false;
        $scope.accountErrorFlag = false;

        $scope.getCurrentUser = function () {
            $scope.user = UserService.get();

            console.log("ProfileCtrl: Get Current User");
            console.log($scope.user)
        };

        $scope.getCurrentUser();

        $scope.saveAccountProfile = function () {
            // UserService.save($scope.user);
            // $scope.user.id = 1;
            UserService.update($scope.user, function (responce) {
                console.log(responce);
                $scope.accountSuccessFlag = true;
                $timeout(function () {
                    $scope.accountSuccessFlag = false;
                }, 2500);
            }, function (error) {
                console.log(error);
                $scope.accountErrorFlag = true;
                $timeout(function () {
                    $scope.accountErrorFlag = false;
                }, 2500);
            });
        };

    }])
    .controller('UserProjectCtrl', ['$scope', 'ProjectService',function($scope, ProjectService){

        $scope.userProjects = ProjectService.query();

        console.log($scope.userProjects)

    }]);