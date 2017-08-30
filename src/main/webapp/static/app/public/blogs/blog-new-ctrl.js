angular.module('myApp.blog')
    .controller('BlogNewCtrl', ['$scope', '$routeParams', '$timeout', '$location', 'CONTEXT', 'API', 'BlogService', function ($scope, $routeParams, $timeout, $location, CONTEXT, API, BlogService) {
    $scope.blogCategory = {};
    $scope.blogEntity = {
        name: "",
        photoUrl: "",
        description: "",
        type: "BLOG"
    };

    $scope.newsEntity = {
        topic: "",
        blog: {},
        blogName: "",
        photoUrl: "",
        description: "",
        content: ""
    };

    $scope.categories = [];


    $scope.loadCategory = function () {
        BlogService.categories({}, function (responce) {
            $scope.categories = responce
            angular.forEach($scope.categories, function (item) {
                if(item.name === $scope.newsEntity.blogName) {
                    $scope.newsEntity.blog = item
                }
            });
        });
    };

    $scope.loadCategory();
    $scope.newsEntity.blogName = $routeParams.category;

    $scope.postNewNews = function () {
        BlogService.save($scope.newsEntity, function (news) {
            console.log(news);
            $location.path("/news/" + news.id)
        }, function (error) {
            console.log("BlogNewCtrl: Post New News Error!!");
            console.log(error)
        });
    };

    $scope.postNewBlog = function () {
        BlogService.addBlog($scope.blogEntity, function (blog) {
            console.log(blog);
            $location.path("/news/category/" + blog.name)
        }, function (error) {
            console.log("BlogNewCtrl: Post New News Error!!");
            console.log(error)
        });
    };


    $scope.onSelectCategory = function (model, item) {
        $scope.blogCategory = item;
    }
}]);

