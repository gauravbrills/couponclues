(function(){
'use strict';
couponFetcher.controller('mainSearchController',function($scope ,couponSearchService,$routeParams ){
	console.log($routeParams);
	$scope.rowCount=[];
    $scope.count=0;
    $scope.username = window.sessionStorage.getItem("username");
	$scope.search = function(){
		couponSearchService.search($scope.username).then( 
			 function(response){ $scope.resultData=response; $scope.emptyPage=false;} , 
			 function(status){$scope.emptyPage=true;alert(status);}
		)
	}
	$scope.search();

	$scope.addRow = function(){
		$scope.rowCount.push($scope.count);
		$scope.count++;
		couponSearchService.offlineData().then( 
			 function(response){
				$scope.offlineData=response;
			} , 
			 function(status){$scope.emptyPage=true;alert("failed");}
		);
	}

	$scope.removeRow = function(){
	  if($scope.rowCount.length>=0){
	  	$scope.rowCount.pop();
	  }
	}

	$scope.getFilterResult = function(filter){
		var data = {};
		    data.categories = [];
		    angular.forEach(filter, function(value, key) {
			  data.categories.push(value);
			});
		couponSearchService.filterSearch(data,$scope.username).then( 
			 function(response){
				$scope.resultData=response;
			} , 
			 function(status){$scope.emptyPage=true;alert("failed");}
		);

	}

    
    $scope.colourIncludes = [];
    
    $scope.includeColour = function(colour) {
        var i = $.inArray(colour, $scope.colourIncludes);
        if (i > -1) {
            $scope.colourIncludes.splice(i, 1);
        } else {
            $scope.colourIncludes.push(colour);
        }
    }
    
    $scope.colourFilter = function(fruit) {
        if ($scope.colourIncludes.length > 0) {
            if ($.inArray(fruit.colour, $scope.colourIncludes) < 0)
                return;
        }
        
        return fruit;
    }

})

})();