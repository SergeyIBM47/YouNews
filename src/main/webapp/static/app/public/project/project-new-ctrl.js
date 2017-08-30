angular.module('myApp.project'/*, ['myApp.tag.service', 'myApp.skill.service']*/)
    .controller('ProjectNewCtrl', ['$scope', '$timeout', '$location', 'CONTEXT', 'API', 'ProjectService', 'SkillService', 'TagService',
        function ($scope, $timeout, $location, CONTEXT, API, ProjectService, SkillService, TagService) {

            $scope.projectCategory = {};

            $scope.selSkill = {};
            $scope.selTag = {};

            $scope.tagsList = [];
            $scope.skillsList = [];

            $scope.projectEntity = {
                name: "",
                description: "",
                webSiteUrl: "",
                photoUrl: "",
                category: "",
                requiredSkills: "",
                owners: {},
                rate: {},
                createdAt: "",
                projectNews: [],
                members: [],
                necessarySkills: [],
                tags: []
            };

            $scope.createNewProject = function () {
                ProjectService.save($scope.projectEntity, function (project) {
                    console.log(project);
                    $location.path("/project/" + project.id)
                }, function (error) {
                    console.log("ProjectNewCtrl: Post New Blog Error!!");
                    console.log(error)
                });
            };

            $scope.categories = ProjectService.categories({}, function (responce) {
                $scope.projectEntity.category = $scope.categories[0];
            });

            $scope.tagsList = TagService.query();
            $scope.skillsList = SkillService.query();

            $scope.onSelectCategory = function (model, item) {
                $scope.projectCategory = model;
            };

            $scope.selectTag = function (item, sel) {
                $scope.projectEntity.tags.push(item);
                console.log("Select Tag - Item: " + item + " Selected: " + sel);
            };

            $scope.removeTag = function (item, sel) {
                var tags = $scope.projectEntity.tags;
                for (var i = 0; i < tags.length; i++)
                    if (tags[i] == item) {
                        tags.splice(i, 1);
                        break;
                    }
                console.log("Remove Tag - Item: " + item + " Selected: " + sel);
            };

            $scope.selectSkill = function (item, sel) {
                $scope.projectEntity.necessarySkills.push(item);
                console.log("Select skill - Item: " + item + " Selected: " + sel);
            };

            $scope.removeSkill = function (item, sel) {
                var skill = $scope.projectEntity.necessarySkills;
                for (var i = 0; i < skill.length; i++)
                    if (skill[i] == item) {
                        skill.splice(i, 1);
                        break;
                    }
                console.log("Remove Skill - Item: " + item + " Selected: " + sel);
            };

        }]);