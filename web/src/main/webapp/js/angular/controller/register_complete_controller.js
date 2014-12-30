/**
 * Created by taozhang on 12/26/14.
 */
angular.module('webP2p')
    .controller('RegisterCompleteController', function ($scope, $resource, $location) {
        var User = $resource("/web/api/v1/users/");
        console.info(User);
        var temp = {};
        $scope.user = {};

        temp = JSON.parse(localStorage["user_base_info"]);

        $scope.create = function(){
            $scope.user.email = temp.email;
            $scope.user.password = temp.password;
            console.log($scope.user);
            var localUser = new User($scope.user);
            console.log(typeof localUser);
            console.log(localUser);
            localUser.$save(function(result){
                console.log(result);
                $location.path("/");
                localStorage["user_base_info"] = "";
            });
        };

        $scope.is_disabled = function() {
            return $scope.user.name == null || $scope.user.idNumber || $scope.user.birthday || $scope.user.role;
        }



        $scope.go_to_welcome_page = function() {
            $location.path("/")
        };

        $scope.go_to_login_page = function() {
            $location.path("/login")
        }
    });