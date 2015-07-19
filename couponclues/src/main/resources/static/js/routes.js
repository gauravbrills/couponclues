(function(){
'use strict';

couponFetcher.config(['$routeProvider', function($routeProvider) {
  
$routeProvider.when('/offlinePurchase', {templateUrl: 'js/partials/offlinePurchase.html'});
$routeProvider.when('/coupon', {templateUrl: 'js/partials/mainContent.html'});
$routeProvider.otherwise({redirectTo:"/coupon"});
}]);

})();