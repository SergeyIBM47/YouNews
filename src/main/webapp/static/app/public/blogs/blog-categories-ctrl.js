angular.module('myApp.blog')
    .controller('BlogCategoryCtrl', ['$scope', '$timeout', '$routeParams', '$compile', 'BlogService', function ($scope, $timeout, $routeParams, $compile, BlogService) {

        $scope.blogs = [];

        $scope.category = $routeParams.category;

        $scope.getAllBlogByCategory = function () {
            BlogService.getAllBlogCategory({categoryName: $routeParams.category}, {}, function (response) {
                $scope.blogs = response;
                console.log(response)
            }, function (error) {
                console.log("BlogCategoryCtrl: GET All Blog By Category Error!!");
                console.log(error)
            })
        };

        $scope.getAllBlogByCategory();

        $scope.subscribe = function () {
            BlogService.addSubscription({categoryName: $routeParams.category},{}, function (response) {
                console.log("BlogCategoryCtrl: Subscribe response - " + response)
            }, function (error) {
                console.log("BlogCategoryCtrl: Subscribe error - " + error)
            })
        }

    }]);