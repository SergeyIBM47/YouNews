angular.module('myApp.news', ['myApp.news.service'])
    .controller('ProjectNewNewsCtrl', ['$scope', '$timeout', '$location', '$routeParams','CONTEXT', 'API', 'NewsService',
        function ($scope, $timeout, $location, $routeParams, CONTEXT, API, NewsService) {
        $scope.blogCategory = {};
        $scope.newsEntity = {
            projectId: $routeParams.id,
            topic: "",
            category: "",
            photoUrl: "",
            description: "",
            content: ""
        };

        $scope.postNewBlog = function () {
            NewsService.save($scope.newsEntity, function (news) {
                console.log(news);
                $location.path("/project/news/" + news.id)
            }, function (error) {
                console.log("ProjectNewsCtrl: Post New News Error!!");
                console.log(error)
            });
        };

    }])
    .controller('ProjectNewsCtrl', ['$scope', '$timeout', '$location', '$routeParams','CONTEXT', 'API', 'NewsService',
        function ($scope, $timeout, $location, $routeParams, CONTEXT, API, NewsService) {

            $scope.news = NewsService.get({id: $routeParams.id}, function (responce) {
                angular.element("#bdescription").html(responce.description);
                angular.element("#bcontent").html(responce.content);

            });

            $scope.postComment = function () {
                NewsService.addComment(
                    {
                        "comment": $scope.newComment,
                        "newsId": $scope.news.id
                    }, function (responce) {
                        $scope.news.comments.push(responce);
                        angular.element("#add_comment").val("")
                    }, function (error) {
                        console.log("ProjectNewsCtrl: Post Comment Error!!");
                        console.log(error)
                    });
            };

    }]);