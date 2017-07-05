<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="angular.min.js"></script>
<script type="text/javascript" src="app.js"></script>

</head>
<body>
<div ng-app="myapp" ng-controller="myctrl"> 

<!-- <a ng-href="#" ng-click="select(BookTitleId='.Net')">.Net</a> -->
<button ng-click="select(BookTitleId='.Net')">Click Here</button>
<table>
<tr ng-repeat="data in tableData">
				<td ng-repeat="(key,value) in data">{{value}}</td>
			</tr>
			</table>
</div>

</body>
</html>