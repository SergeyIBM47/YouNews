angular.module("myApp.login",['ngPopup'])
    .service('authSrv', ['$rootScope', '$q', '$route', '$window', '$http', '$location', 'API', 'Popup',
        function ($rootScope, $q, $route, $window, $http, $location, API, Popup) {

            var authorized = false;
            var user;

            function getCurrentUser(email, password) {
                return $http.get(API.currentUserPath).success(function (data, status, headers, config) {
                    if (status === 200) {
                        authorized = true;
                        user = data;
                        angular.element("#myLoginModal").modal("hide");
                        $location.path('/');
                    }
                }).error(function (data, status, headers, config) {
                       Popup.showPopup('error', 'Internal server error', 'Try again', 1600);
                });
            }


            function authenticate(email, password) {
                var data = $.param({
                    email: JSON.stringify(email).replace(/\"/g, ""),
                    password: JSON.stringify(password).replace(/\"/g, "")
                });
                return $http({
                        method: 'POST',
                        url: API.authenticatePath,
                        data: data,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }
                ).success(function (data, status, headers, config) {
                    if (status === 200) {
                        authorized = true;
                        user = data;
                        console.log(user);
                        angular.element("#loginModal").modal("hide");
                        $location.path('/main');
                    } else {
                        Popup.showPopup('error', 'Login Failed', 'Incorrect login or password', 1600);
                    }
                }).error(function (data, status, headers, config) {
                    if (status === 401)
                        Popup.showPopup('error', 'Login Failed', 'Incorrect login or password', 1600);
                    else
                        Popup.showPopup('error', 'Internal server error', 'Try again', 1600);
                    console.log("failure => " + data)
                });
            }

            function registrationUser(email, name, password) {

                return $http.post(API.userRegistrationPath, {
                        email: email, name: name, password: password
                    }
                ).success(function (data, status, headers, config) {
                    if (status === 200 || status === 201) {
                        user = data;

                        console.log(user);
                        angular.element("#registrationModal").modal("hide");
                        authenticate(email, password)
                    }
                }).error(function (data, status, headers, config) {
                    if (status === 401)
                        Popup.showPopup('error', 'Login Failed', 'Incorrect login or password', 1600);
                    else if (status === 409) {
                        Popup.showPopup('error', 'Registration Failed', 'User with the same email already exists', 2500);
                    } else
                        Popup.showPopup('error', 'Unknown error', 'Try again', 1600);
                    console.log("failure => " + data)
                });
            }

            function unAuthenticate(idReason) {
                return $http.get(API.logoutPath)
                    .finally(function () {
                        user = undefined;
                        authorized = false;
                        $location.path("/");
                    });
            }

            return {
                authorized: function () {
                    return authorized;
                },
                getUser: function () {
                    return user;
                },
                unAuthenticate: unAuthenticate,
                authenticate: authenticate,
                registration: registrationUser,
                getCurrentUser: getCurrentUser
            }
        }
]);