function upcomingRideCtrlFunction($scope,$http,$location,$cookies,$sanitize){

	if( $cookies.get('username')=='' || $cookies.get('username')==undefined  )
	{
		$location.path('/login');
	}
	else{
		$scope.username=$cookies.get('username');
	}

	$scope.totalPages=0;

	var currPageNo=1;

	$scope.getRide=function(pageNo){

		$scope.resultBad=``;

		//var givenTime=moment(`${ $('#date').val() } 00:
		//	00:00`,'YYYY-MM-DD hh:mm:ss');

		var rideObj={
			'pageNo':pageNo,
			'token':$cookies.get('token')
		};

		//window.alert(JSON.stringify(rideObj));
		var url = '/api/rideUpcoming',data = `${JSON.stringify(rideObj)}`,config=`application/json,\
	Access-Control-Allow-Origin: http://localhost:8080,Authorization: Bearer ${$cookies.get('token')}`;
	//window.alert(data);
	//return false;
	
	$http.post(url, data, config).then(function (response) {
		//window.alert('here');
		try{
			$scope.data=response.data;
		   	$scope.totalPages=$scope.data.totalPages;
		   	$scope.data.content;

		   	currPageNo=pageNo;

		   	if(response.data.content.length==0){
			  	$scope.resultBad=`
			  		<div class='alert alert-warning col-sm-12'>Sorry no rides were found.</div>
			  	`;
			}
		}
		catch(e){
			if(response.data.response=='UNAUTHORIZED'){
				window.alert(`Your session has expired.`);
				$location.path('/login');

			}
		}


	}, function (response) {

		// this function handles error
		window.alert(`Sorry, An error occurred. Please try again.`);

		});

	};

	$scope.getRide(1);



	$scope.cancelRidership=function(paymentMode,riderStatus,paymentStatus,rideStatus,rideId,pageNo){

		if(!confirm('Are you sure?'))
			return false;

		var rideObj={
			'paymentMode':paymentMode,
			'riderStatus':riderStatus,
			'paymentStatus':paymentStatus,
			'rideStatus':rideStatus,
			'rideId':`${rideId}`,
			'token':$cookies.get('token')
		};

		//window.alert(JSON.stringify(rideObj));
		var url = '/api/cancelRiderShip',data = `${JSON.stringify(rideObj)}`,config=`application/json,\
	Access-Control-Allow-Origin: http://localhost:8080,Authorization: Bearer ${$cookies.get('token')}`;
	//window.alert(data);
	//return false;
	
	$http.post(url, data, config).then(function (response) {
		//window.alert('here');
		if(response.data.response=='UNAUTHORIZED'){
			window.alert(`Your session has expired.`);
			$location.path('/login');
		}
		else if(response.data.response=='RIDERSHIP CANCELLED')
			$scope.resultBad=`
			<div class='alert alert-success col-sm-12 col-md-12 col-lg-12'>
			Your Ridership has been cancelled. If you have paid in advanced
			 for this trip via card, it will reverted soon.</div>`;
		else if(response.data.response=='COULD NOT CANCEL RIDERSHIP')
			$scope.resultBad=`
			<div class='alert alert-danger col-sm-12 col-md-12 col-lg-12'>
			Your Ridership could not be cancelled due to following reasons:
			1. You have already cancelled the Ridership or ride has been cancelled.
			2. Ride has expired or completed.
			</div>`;


	}, function (response) {

		// this function handles error
		window.alert(`Sorry, An error occurred. Please try again.`);

		});

		$scope.getRide(pageNo);
	};

	

}