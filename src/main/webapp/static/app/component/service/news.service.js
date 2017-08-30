angular.module("myApp.news.service", ['ngResource'])
    .factory('NewsService', ['$resource', 'CONTEXT', 'API', function ($resource, CONTEXT, API) {
        return $resource(CONTEXT + API.newsPath, {}, {
            "get": {
                method: "GET",
                url: CONTEXT + API.newsPath + "/news/:id"
            },
            "update": {method: "PUT"},
            "save": {
                method: "POST",
                url: CONTEXT + API.newsPath + "/add/news"
            },
            "addComment": {
                method: "POST",
                url: CONTEXT + API.newsPath + "/comments"
            }
        });

        //var User = $resource(CONTEXT + API.userPath,{},{
        //    'get':    {method:'GET'},
        //    'save':   {method:'POST'},
        //    'query':  {method:'GET', isArray:true},
        //    'remove': {method:'DELETE'},
        //    'delete': {method:'DELETE'} })
        //
        //return User;
}]);