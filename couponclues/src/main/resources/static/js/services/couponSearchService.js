(function(){
	'use strict';
   couponFetcher.services.service('couponSearchService',['$http','$q','backendURLs',function($http,$q,backendURLs){

	     this.search = function(username){
				var deferred = $q.defer();
				$http({
							method : 'GET',
							url : backendURLs.output+"/"+username
						})
						.success(function(data, status, headers,	config) {deferred.resolve(data)})
						.error(function(data, status, headers,config) {deferred.reject(status);});
				
				return deferred.promise;
			}
	    this.offlineData = function(){
				var deferred = $q.defer();
				$http({
							method : 'GET',
							url : backendURLs.productMaster
						})
						.success(function(data, status, headers,	config) {deferred.resolve(data)})
						.error(function(data, status, headers,config) {deferred.reject(status);});
				
				return deferred.promise;
		}
		this.filterSearch = function(data,username){
			console.log(data);
			var deferred = $q.defer();
				$http({
							method : 'POST',
							url : backendURLs.output+"/"+username,
							data : data
						})
						.success(function(data, status, headers,	config) {deferred.resolve(data)})
						.error(function(data, status, headers,config) {deferred.reject(status);});
				
				return deferred.promise;

		}



   }]);
})();