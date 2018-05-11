var myapp = angular.module("myApp",[])
             	.controller("myController", function($scope){
		var emp = [
			{empname:"Aniket",DOB:new Date("December 30, 1980"),gender:"Male",salary:50000},
			{empname:"Deepa",DOB:new Date("March 21, 1990"),gender:"Female",salary:60000},
			{empname:"Vinay",DOB:new Date("January 05, 1970"),gender:"Male",salary:55000},
			{empname:"Nilesh",DOB:new Date("August 24, 1983"),gender:"Male",salary:70000},
			{empname:"Aditi",DOB:new Date("December 30, 1980"),gender:"Female",salary:45000}
		];
		$scope.employees = emp;
		
		$scope.sortColumn = "empname";
		$scope.reverseOrder = false;
		$scope.sortData = function(column){
			if($scope.sortColumn == column){
				$scope.reverseOrder = !($scope.reverseOrder)
			}
			else{
				$scope.reverseOrder = false;
			}
			$scope.sortColumn = column;
		}
	});