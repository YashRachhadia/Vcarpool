// moment(`${moment().format('YYYY-MM-DD HH:mm')}`,'YYYY-MM-DD HH:mm').add(20, 'minutes')


function rideCreateCtrlFunc($scope,$http){
	  //first check if authenticated
  
   $scope.submitRide = function() {
      
      var loginPage=userInfo;

	  if( loginPage.authenticated=='yes' ){
	  	//validate ride details


	  	//ride start time shouldn't be after 10 min from now 
	  	//window.alert($scope.startDateTime);
		if( $('#startDateTime').val()==undefined || $('#startDateTime').val()=='' ){
			window.alert('Please give start time/date of departure.');
			return false;
		}

		if($scope.carType==undefined || $scope.carType==''){
			window.alert('Please give valid Car Type.');
			return false;
		}

		if($scope.capacity==undefined || $scope.capacity==0){
			window.alert('Please give valid capacity.');
			return false;
		}

		if($scope.destination==undefined){
			window.alert('Please give a destination.');
			return false;	
		}

		if($scope.vehicleNumber==undefined){
			window.alert('Please give vehicle number.');
			return false;	
		}	

		if($scope.carModel==undefined){
			window.alert('Please give your car model for easy identification.');
			return false;	
		}

		if($scope.fare==undefined ||$scope.fare==0){
			window.alert('Please give a valid fare.');
			return false;	
		}

		

		var rideObj={
			"empId":`${loginPage.emp_id}`,
			"startDateTime":`${$('#startDateTime').val()}:00`,
			"carType":`${$scope.carType}`,
			"capacity":`${$scope.capacity}`,
			"destination":`${$scope.destination}`,
			"vehicleNo":`${$scope.vehicleNumber}`,
			"carModel":`${$scope.carModel}`,
			"fare":`${$scope.fare}`
		};

		//post data to api
		var url = '/api/addRideDetails',
		 data = `${JSON.stringify(rideObj)}`,config=`application/json,\
		Access-Control-Allow-Origin: http://localhost:8080, Authorization: Bearer ${$cookies.get('token')}`;
		//window.alert(data);
		//return false;
		$http.post(url, data, config).then(function (response) {

		// This function handles success
		try {
			  var data=response.data;
			  
			  if(data.response=="RIDE ADDED")
			  {	$scope.postResponse=`
			  	<div class="alert alert-success">
			  		Ride created successfully.
			  	</div>
			  `;
			  
			  }
			  else if( data.response=="RIDE NOT COMPLETED" )
			  	$scope.postResponse=`
			  	<div class="alert alert-danger">
			  		Sorry, you still have an ongoing ride yet to be completed. Either cancel them or complete them 
			  		to create new ride.
			  	</div>
			  `;
			  else if(data.response=="ERROR")
			  	$scope.postResponse=`
			  	<div class="alert alert-danger">
			  		An error occurred.
			  	</div>
			  `;

			}
			catch(err) {
				$scope.postResponse=`
			  	<div class="alert alert-danger">
			  		Sorry, your ride couldn't be created.
			  	</div>
			  `;
			}

		}, function (response) {

		// this function handles error
		$scope.postResponse=`
			  	<div class="alert alert-danger">
			  		Sorry, your ride couldn't be created.
			  	</div>
			  `;

		});

	}
	else{
  	window.alert('You have not logged In.');
  	//redirect to login page
  	}

  };



  
}


