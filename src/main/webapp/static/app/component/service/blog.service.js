angular.module("myApp.blog.service", ['ngResource'])
    .factory('BlogService', ['$resource', 'CONTEXT', 'API', function ($resource, CONTEXT, API) {
        return $resource(CONTEXT + API.blogPath, {}, {
            "get": {
                method: "GET",
                url: CONTEXT + API.blogPath + "/one/:id"
            },
            "update": {method: "PUT"},
            "categories": {
                method: "GET",
                url: CONTEXT + API.blogPath + "/categories",
                isArray: true
            },
            "getAllBlogCategory": {
                method: "GET",
                url: CONTEXT + API.blogPath + "/categories/:categoryName",
                isArray: true
            },
            "addBlog": {
                method: "POST",
                url: CONTEXT + API.blogPath + "/categories"
            },
            "addSubscription": {
                method: "PUT",
                url: CONTEXT + API.blogPath + "/categories/:categoryName/subscribe"
            },
            "addComment": {
                method: "POST",
                url: CONTEXT + API.blogPath + "/comments"
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