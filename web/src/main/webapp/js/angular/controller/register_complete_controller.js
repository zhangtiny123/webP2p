/**
 * Created by taozhang on 12/26/14.
 */
angular.module('webP2p')
    .controller('RegisterCompleteController', function ($scope, $resource, $location) {
        var User = $resource("/web/api/v1/users/");
        console.log(JSON.parse(localStorage["user_base_info"]));
        //$scope.create = function(){
        //    var localUser = new User($scope.user);
        //    localUser.$save().then(function(result){
        //        $location.path("/");
        //    });
        //};

        $scope.to_complete_information = function() {
            localStorage["user_base_info"] = JSON.stringify($scope.user);

        }

        $scope.go_to_welcome_page = function() {
            $location.path("/")
        }

        $scope.go_to_login_page = function() {
            $location.path("/login")
        }
    });