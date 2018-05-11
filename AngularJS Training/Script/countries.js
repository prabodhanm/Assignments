var myModule=angular.module("myApp",[])
             .controller("myController",function($scope) {
			     var countries=[
					{name:"India",
					cities:[
						{name:"Mumbai",location:[
							{name:"Dadar"},
								{name:"Dharavi"}
						]
						},
						{name:"Delhi"},
						{name:"Hydrabad"}
					]
					},
					{name:"UK",
					cities:[
						{name:"London"},
						{name:"Manchester"},
						{name:"Bermingham"}
					]
					},
					{name:"USA",
					cities:[
						{name:"Chicago"},
						{name:"New York"},
						{name:"Los Angeles"}
					]
					},
				 ];
			 $scope.countrydata = countries;
			 });