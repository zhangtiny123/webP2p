/**
 * Created by taozhang on 12/26/14.
 */
'use strict';

angular.module('webP2p')
    .controller('LoginController', function ($scope, $resource, $location) {
        $scope.go_to_welcome_page = function() {
            $location.path("/");
        };

        $scope.go_to_register_page = function() {
            if(localStorage.length == 0 || localStorage["user_base_info"] =="") {
                $location.path("/register");
            }
            else {
                $location.path("/register_complete");
            }
        }
    });