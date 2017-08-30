angular.module('myApp.blog')
    .controller('BlogNewsCtrl', ['$scope', '$timeout', '$routeParams', '$compile', 'BlogService', 'NewsService', function ($scope, $timeout, $routeParams, $compile, BlogService, NewsService) {

        $scope.news = [];

        $scope.getAllNews = function () {
            NewsService.query({}, function (response) {
                $scope.news = response;
                console.log(response)
            }, function (error) {
                console.log("BlogCategoryCtrl: GET All Blog By Category Error!!");
                console.log(error)
            })
        };

        $scope.getAllNews();

    }]);