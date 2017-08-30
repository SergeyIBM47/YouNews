angular.module('myApp.blog', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        //blogs routing configuration
        $routeProvider
            .when('/about', {
                templateUrl: "static/app/public/about/about.html",
                controller: 'BlogCtrl'
            })
            .when('/blog', {
                templateUrl: "static/app/public/blogs/blogs.html",
                controller: "BlogCtrl"
            })
            .when('/news/', {
                templateUrl: "static/app/public/blogs/blog-news-list.html",
                controller: "BlogNewsCtrl"
            })
            .when('/news/category/:category', {
                templateUrl: "static/app/public/blogs/blog-category.html",
                controller: "BlogCategoryCtrl"
            })
            .when("/news/:id", {
                templateUrl: "static/app/public/blogs/blog-page.html",
                controller: "BlogCtrl"
            })
            .when("/blog/add/new", {
                templateUrl: "static/app/public/blogs/blog-new-blog.html",
                controller: "BlogNewCtrl"
            })
            .when("/:category/add/new", {
                templateUrl: "static/app/public/blogs/blog-new-news.html",
                controller: "BlogNewCtrl"
            })
    }])
    .controller('BlogCtrl', ['$scope', '$timeout', '$routeParams', '$compile', 'BlogService', function ($scope, $timeout, $routeParams, $compile, BlogService) {
        $scope.newComment = "";

        $scope.currentPage = 1;
        $scope.numPerPage = 10;
        $scope.maxSize = 5;

        $scope.blogCategories = [];

        $scope.blog = BlogService.get({id: $routeParams.id}, function (responce) {
            angular.element("#bdescription").html(responce.description);
            angular.element("#bcontent").html(responce.content);
        });

        $scope.getAllBlogCategories = function(){
            BlogService.categories({}, function(responce){
                $scope.blogCategories = responce;
                console.log(responce)
            }, function(error){
                console.log("BlogCtrl: GET All Blog Categories Error!!");
                console.log(error)
            })
        };

        $scope.getAllBlogCategories();

        $scope.postComment = function () {
            BlogService.addComment(
                {
                    "comment": $scope.newComment,
                    "blogId": $scope.blog.id
                }, function (responce) {
                    $scope.blog.comments.push(responce);
                    angular.element("#add_comment").val("")
                }, function (error) {
                    console.log("BlogCtrl: Post Comment Error!!");
                    console.log(error)
                });
        };
    }]);