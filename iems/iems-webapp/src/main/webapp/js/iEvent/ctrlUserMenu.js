/**
 * ctrlUserMenu
 */

iEventApp.controller('ctrlUserMenu', ["$scope", "$http", "$location", function($scope, $http, $location) {
	var sUrl = iEvent.api_url("/v1/users/current");
	$http.get(sUrl).success(
		function(json) {
			angular.extend($scope, json);
		}
	);
	
}]
);
