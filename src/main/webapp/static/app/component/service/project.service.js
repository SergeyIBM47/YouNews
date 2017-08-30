angular.module("myApp.services", ['ngResource'])
    .factory('ProjectService', ['CONTEXT', 'API', '$resource', function (CONTEXT, API, $resource) {
        return $resource(CONTEXT + API.projectPath, {}, {
            "get": {
                method: "GET",
                url: CONTEXT + API.projectPath + "/:id"
            },
            "categories": {
                method: "GET",
                url: CONTEXT + API.categoriesProjectPath,
                isArray: true
            },
            "byCategoryList": {
                method: "GET",
                url: CONTEXT + API.projectPath + "/categories/:category",
                isArray: true
            },
            "byUserList": {
                method: "GET",
                url: CONTEXT + API.projectPath + "/all",
                isArray: true
            }
        })
    }]);