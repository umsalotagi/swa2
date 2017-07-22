var app = angular.module('myapp', []);

app.controller('myctrl', function($scope,$http) {
    $scope.carname = "Maruti800";
    
    $scope.select=function(BookTitleId)
	{
    	//$scope.b="Welcome to Angular";
    	//alert($scope.BookTitleId);
		$scope.tableData;
		var tempArr;
    	var response=$http.get("select?BookTitleId="+$scope.BookTitleId).then(function success(response){
    		   
    		$scope.tableData = response.data;
    	//	getBooks($scope.BookTitleId);
    		
    		alert($scope.BookTitleId);
    		//tempArr.push(response);
    		//$scope.tableData = tempArr;
    			//	alert(JSON.stringify(response));
    				console.log($scope.tableData);
    				});
				}
    $scope.getBooks=function(BookTitleId)
    {
    	var response=$http.get("getBooks?BookTitleId="+$scope.BookTitleId).then(function success(response){
    		$scope.multipleBooks=response.data;
    		alert("Inside getBooks");
    		console.log($scope.multipleBooks);
    	});
    }
});
    		
    
