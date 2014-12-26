'use strict';

/**
 * @ngdoc overview
 * @name partyBidApp
 * @description
 * # partyBidApp
 *
 * Main module of the application.
 */
angular
    .module('webP2p', [
        'ngResource',
        'ngRoute',
        'checklist-model'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'WelcomeController'
            })
            .when('/register', {
                templateUrl: 'views/register.html',
                controller: 'RegisterController'
            })
            .when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginController'
            })
            .when('/register_complete', {
                templateUrl: 'views/register_complete.html',
                controller: 'RegisterCompleteController'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
