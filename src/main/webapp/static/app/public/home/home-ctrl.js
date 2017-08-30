angular.module("myApp.home",['ngRoute'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when("/",{
            templateUrl:"static/app/public/home/home.html",
            controller:"HomeController"
        })
    }])
    .controller("HomeController",['$scope', 'HomeModel', function($scope, HomeModel){

        HomeModel.query({}, function(responce){
            $scope.data = responce;
            console.log(responce)
        });
    }]);