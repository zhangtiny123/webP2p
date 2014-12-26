angular.module('webP2p')
    .controller('RegisterController', function ($scope, $resource, $location) {
        var User = $resource("/web/api/v1/users/");
        $scope.user = {};

        $scope.to_complete_information = function() {
            localStorage["user_base_info"] = JSON.stringify($scope.user);
            $location.path("/register_complete")
        }

        $scope.go_to_welcome_page = function() {
            $location.path("/")
        }

        $scope.go_to_login_page = function() {
            $location.path("/login")
        }
    });