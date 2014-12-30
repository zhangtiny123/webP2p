angular.module('webP2p')
    .controller('RegisterController', function ($scope, $resource, $location) {
        var User = $resource("/web/api/v1/users/");
        $scope.user = {};

        $scope.to_complete_information = function() {
            if ($scope.user.password != $scope.user.re_password) {
                alert("twice typed password are not same, retype please!")
            }
            else if (!is_mail_legal($scope.user.email)) {
                alert("illegal email, retype please!")
            }
            else {
                localStorage["user_base_info"] = JSON.stringify($scope.user);
                $location.path("/register_complete");
            }
        }

        function is_mail_legal(email) {
            var email_reg =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            return email_reg.test(email);
        }

        $scope.go_to_welcome_page = function() {
            $location.path("/")
        }

        $scope.go_to_login_page = function() {
            $location.path("/login")
        }
    });