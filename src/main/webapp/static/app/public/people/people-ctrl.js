'use strict';

angular.module('myApp.about', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/people', {
            templateUrl: 'static/app/public/people/people.html',
            controller: 'PeopleCtrl'
        });
    }])

    .controller('PeopleCtrl', ['$scope','UserService', function ($scope,UserService) {

        $scope.people = [];

        $scope.getAllPeople = function(){
            $scope.people = UserService.getAllPeople();
            console.log("PeopleCtrl: Get All People");
            console.log(self.people)

        };

        $scope.getAllPeople();

    }]);