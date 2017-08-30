angular.module("myApp.home")
    .factory('HomeModel',['$resource', 'CONTEXT', 'API',
        function($resource, CONTEXT, API){
            var HomeModel = $resource(CONTEXT + API.indexPath, {}, {
                "query":{
                    method:'GET'/*,
                    transformResponse:function(data){
                        data = angular.fromJson(data);
                        return data;
                    }*/
                }
            });

            return HomeModel;
        }]);