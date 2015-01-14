/**
 * ctrlUserMenu
 */

iEventApp.controller('ctrlUserMenu', ["$scope", "$http", "$location", function($scope, $http, $location) {
	$scope.username = "Jane Doe";
	
	$http.get(iEvent.api_prefix+"/users/current", {"access_token" : iEvent.access_token}).success(
		function(json) {
			alert(json.principal.username);
		}
	);
	
}]
);
