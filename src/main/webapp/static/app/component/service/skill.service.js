angular.module("myApp.skill.service", ['ngResource'])
    .factory('SkillService', ['CONTEXT', 'API', '$resource', function (CONTEXT, API, $resource) {
        return $resource(CONTEXT + API.skillsPath, {});
    }]);