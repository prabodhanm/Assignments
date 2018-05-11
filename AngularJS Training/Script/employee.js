var myapp = angular.module("myApp",[])
             	.controller("myController", function($scope){
		var emp = [
			{empid:100,empname:"Aniket",gender:"Male",salary:50000},
			{empid:200,empname:"Deepa",gender:"Female",salary:60000},
			{empid:300,empname:"Vinay",gender:"Male",salary:55000},
			{empid:400,empname:"Nilesh",gender:"Male",salary:70000},
			{empid:500,empname:"Aditi",gender:"Female",salary:45000}
		];
		$scope.employees = emp;
	});