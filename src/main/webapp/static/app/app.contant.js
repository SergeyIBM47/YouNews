angular.module("myApp.config",[])
    .constant("API", {
        indexPath:"/",
        userPath:"/rest/users",
        blogPath:"/rest/blogs",
        newsPath:"/rest/news",
        projectPath:"/rest/projects",
        categoriesPath:"/rest/news",
        categoriesProjectPath:"/rest/news/projects",
        categoriesBlogPath:"/rest/news/blog",
        categoriesNewsPath:"/rest/news/news",
        skillsPath:"/rest/skills",
        tagsPath:"/rest/tags"
    })
    .factory('CONTEXT', ['$window',
        function($window) {
            var url = $window.location.pathname;
            return url.substring(0, url.lastIndexOf('/'));
        }
    ]);