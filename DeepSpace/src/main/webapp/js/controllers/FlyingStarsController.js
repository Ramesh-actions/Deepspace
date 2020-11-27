flyingStarsApp.controller('FlyingStarsController',
    function FlyingStarsContoller($scope, imagesSvc) {
        'use strict';

        $scope.settings = {
          speed: 50,
          numOfStars: 250,
          images: imagesSvc.getImages()
        }
    }
);
