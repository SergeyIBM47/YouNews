'use strict';

// Declare app level module which depends on views, and components
angular.module(
    'myApp', [
        'ngRoute',
        'ngResource',
        'ngPopup',
        'ui.select',
        'myApp.config',
        'myApp.about',
        'myApp.contact',
        'myApp.home',
        'myApp.version',
        'myApp.blog',
        'myApp.profile',
        'myApp.project',
        'myApp.services',
        'myApp.blog.service',
        'myApp.skill.service',
        'myApp.tag.service',
        'myApp.news',
        'myApp.login',
        'angularUtils.directives.dirPagination'
    ])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.otherwise({redirectTo: '/'})
    }]).run(['$rootScope', '$route', '$location', 'CONTEXT', 'API', 'authSrv', 'Popup',
    function ($rootScope, $route, $location, CONTEXT, API, authSrv, Popup) {
        //
        //$rootScope.$on('$routeChangeStart', function (event, next) {
        //    if (!authSrv.authorized() && typeof next != 'undefined' && typeof next.$$route != 'undefined' && next.$$route.originalPath != '/') {
        //
        //        authSrv.getCurrentUser().then(function () { // success
        //                $location.path("/main")
        //            },
        //            function () { // error
        //                $location.path("/")
        //            });
        //    } else if (authSrv.authorized() && typeof next != 'undefined' && typeof next.$$route != 'undefined' && next.$$route.originalPath == '/') {
        //        $location.path("/main")
        //    }
        //});
        //
        //$rootScope.logout = function () {
        //    authSrv.unAuthenticate();
        //    Popup.showPopup('success', 'Garmin', 'See you )', 1600);
        //};
    }
]);