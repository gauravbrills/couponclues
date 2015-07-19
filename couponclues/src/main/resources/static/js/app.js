(function(){

'use strict';
var couponFetcher = angular.module('couponFetcher', [
  'ngRoute',
  'couponFetcher.filters',
  'couponFetcher.services',
  'couponFetcher.directives',
  'couponFetcher.controllers'
]);

couponFetcher.controller = angular.module('couponFetcher.controllers',[]).controller;
couponFetcher.services = angular.module('couponFetcher.services',[]);
couponFetcher.directive = angular.module('couponFetcher.directives',[]).directive;
couponFetcher.filter = angular.module('couponFetcher.filters',[]).filter;

window.couponFetcher = couponFetcher;
})();