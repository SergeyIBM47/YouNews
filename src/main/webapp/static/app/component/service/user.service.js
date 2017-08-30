angular.module("myApp.user.services", ['ngResource'])
    .factory('UserService', ['$resource', 'CONTEXT', 'API', function ($resource, CONTEXT, API) {
        return $resource(CONTEXT + API.userPath, {}, {
            update: {method: "PUT"},
            addProject: {
                method: "POST",
                url: CONTEXT + API.userPath + "/add/project/:projectId"
            },
            getAllPeople:{
                method: "GET",
                url: CONTEXT + API.userPath + "/all-people",
                isArray:true
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