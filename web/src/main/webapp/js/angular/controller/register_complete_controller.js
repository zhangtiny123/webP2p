/**
 * Created by taozhang on 12/26/14.
 */
angular.module('webP2p')
    .controller('RegisterCompleteController', function ($scope, $resource, $location) {
        var User = $resource("/web/api/v1/users/");
        var temp = {};
        $scope.user = {};

        temp = JSON.parse(localStorage["user_base_info"]);

        $scope.create = function(){
            $scope.user.email = temp.email;
            $scope.user.password = temp.password;
            var localUser = new User($scope.user);
            localUser.$save(function(result){
                $location.path("/");
                localStorage["user_base_info"] = "";
            });
        };

        $scope.is_disabled = function() {
            return $scope.user.name == null || $scope.user.idNumber == null || $scope.user.birthday == null || $scope.user.role == null;
        }



        $scope.go_to_welcome_page = function() {
            $location.path("/")
        };

        $scope.go_to_login_page = function() {
            $location.path("/login")
        }
    });