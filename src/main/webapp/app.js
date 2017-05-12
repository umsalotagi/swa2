var app = angular.module('myapp', []);

app.controller('myctrl', function($scope,$http) {
    $scope.carname = "Maruti800";
    
    $scope.select=function(BookTitleId)
	{
    	//$scope.b="Welcome to Angular";
    	//alert($scope.BookTitleId);
    	$http.get("select.jsp?BookTitleId="+$scope.BookTitleId).then(function(response)
				{
	//		var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		//     popupWinindow.document.open();
    		var s="hello";
    		console.log(s);
    		$scope.arr =response;//{name:"Shridevi"}; 
    		
    		//	console.log(arr.bookTitleID);
    			
    			//response;//.tostring().toJSON();
			//$scope.empArr=response.json();
			
				});
    		}
    
});