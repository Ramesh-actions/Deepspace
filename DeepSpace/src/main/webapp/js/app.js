var flyingStarsApp = angular.module('flyingStarsApp', ['ngResource',
    'ngRoute',
    'flyingStarsApp.directives',
    'flyingStarsApp.services'])
    .config(function($routeProvider, $locationProvider) {
        $routeProvider.when('/stars', {
            templateUrl: 'templates/Stars.html',
            controller: 'FlyingStarsController'
        });

        $routeProvider.otherwise({redirectTo: '/stars'});
    });

var fsDirectives = angular.module('flyingStarsApp.directives', []);
var fsServices = angular.module('flyingStarsApp.services', []);
