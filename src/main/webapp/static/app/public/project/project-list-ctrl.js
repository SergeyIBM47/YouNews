angular.module('myApp.project')
    .controller('ProjectCategoryCtrl', ['$scope', 'ProjectService',"$routeParams", function ($scope, ProjectService, $routeParams) {

        $scope.projectCategoryList = ProjectService.byCategoryList({category:$routeParams.category});

        console.log("ProjectCategoryCtrl: GET Projects List");
        console.log($scope.projectCategoryList);
    }]);