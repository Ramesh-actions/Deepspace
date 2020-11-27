flyingStarsApp.service('imagesSvc', function($resource) {
  var imageResources = $resource('./api/images/:id');
  this.getImages = function() {
    return imageResources.query();
  }
});
