angular.module("myApp.project")
    .controller('ProjectDashboardCtrl', ['$scope', 'ProjectService', function ($scope, ProjectService) {

        $scope.projectCategoryList = ProjectService.categories();

        console.log("ProjectDashboardCtrl: GET Projects Category List");
        console.log($scope.projectCategoryList);
    }]);