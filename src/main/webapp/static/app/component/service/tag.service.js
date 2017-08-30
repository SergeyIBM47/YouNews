angular.module("myApp.tag.service", ['ngResource'])
    .factory('TagService', ['CONTEXT', 'API', '$resource', function (CONTEXT, API, $resource) {
        return $resource(CONTEXT + API.tagsPath, {});
    }]);