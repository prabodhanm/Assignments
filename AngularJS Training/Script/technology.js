var myapp = angular.module("myApp",[])
			.controller("myController",function($scope){
				var technology=[
					{name:"Java",likes:0,unlikes:0},
					{name:"ASP.Net",likes:0,unlikes:0},
					{name:"Spark",likes:0,unlikes:0},
					{name:"Scala",likes:0,unlikes:0}
				];
				$scope.technologies = technology;
				
				$scope.incrementLikes = function(tech){
					tech.likes++;
				}
				
				$scope.incrementUnlikes = function(tech){
					tech.unlikes++;
				}
			});