function loggedInSearchRideCtrlFunction($scope,$http,$location,$cookies,passRideId){

	if( $cookies.get('username')=='' || $cookies.get('username')==undefined  )
	{
		$location.path('/login');
	}
	else{
		$scope.username=$cookies.get('username');
	}

	$scope.data=null;

	$scope.totalPages=0;


	$scope.search=function(pageNo){

		$scope.resultBad=``;

		if( $scope.destinationSearch==undefined)
			return false;

		if( $scope.date==undefined)
			return false;

		var givenTime=moment(`${ $('#date').val() } ${$('#hour').val()}:
			${$('#minute').val()}:00`,'YYYY-MM-DD hh:mm:ss');

		var minDiff=moment();

		if( givenTime<minDiff )
			return false;

		$http.get(`/api/searchRide?destination=${ $scope.destinationSearch }
			&startDateTime=${ $('#date').val() } ${$('#hour').val()}:
			${$('#minute').val()}:00&pageNo=${pageNo}`)
		  .then(function(response) {
		   	$scope.data=response.data;
		   	$scope.totalPages=$scope.data.totalPages;
		   	
		   	if(response.data.content.length==0){
			  	$scope.resultBad=`
			  		<div class='alert alert-warning col-sm-12'>Sorry no rides were found.</div>
			  	`;
			}
		   });
	};

	$scope.book=function(rideId){
		//CHECK IF USER HAS ANY INCOMPLETE RIDE WHICH HE HAS CREATED
		var rideObj={
		"token":`${$cookies.get('token')}`,
		"rideId":rideId
	};

	//post data to api
	var url = '/api/checkLegibility1', data = `${JSON.stringify(rideObj)}`,config=`application/json,\
	Access-Control-Allow-Origin: http://localhost:8080, Authorization: Bearer ${$cookies.get('token')}`;
	//window.alert(data);
	//return false;
	$http.post(url, data, config).then(function (response) {

	// This function handles success
	
		  var data=response.data;

		  if( data.response=="NOT ELIGIBLE")
		  {
		  	window,alert(`You already have a trip which is not completed/cancelled. Please cancel/complete the trip first.`);
		  	return false;
		  }
		  else if( data.response=="ELIGIBLE" ){
		  	passRideId.set( rideId );
		  	$location.path('/bookRide');
		  }
	
		

	}, function (response) {

	// this function handles error
	$scope.postResponse=`
		  	<div class="alert alert-danger">
		  		Sorry, your ride couldn't be created.
		  	</div>
		  `;

	});

	};

}