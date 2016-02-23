'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ui.bootstrap',
    'myApp.category'
]).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: 'home/home.html',
                controller: 'HomeCtrl'
            })
            .when('/activity', {
                templateUrl: 'home/activity.html',
                controller: 'ActivityCtrl'
            })
            .when('/report', {
                templateUrl: 'home/report.html',
                controller: 'ReportCtrl'
            })
            .otherwise({redirectTo: '/home'});

    }])


    .controller('HomeCtrl', function ($scope) {
    })
