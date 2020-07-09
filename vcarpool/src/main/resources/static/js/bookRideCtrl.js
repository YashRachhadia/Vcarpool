function bookRideCtrlFunction($scope,$http,$location,$cookies,passRideId){

	if( $cookies.get('username')=='' || $cookies.get('username')==undefined  )
	{
		$location.path('/login');
	}
	else{
		$scope.username=$cookies.get('username');
	}

	var rideId=passRideId.get();

	var rideObj={
		"rideId":`${ rideId }`,
		"token":`${$cookies.get('token')}`
	};

	var email='';
	var mobile='';
	//post data to api
	var url = '/api/getRideDetails', data = `${JSON.stringify(rideObj)}`,config=`application/json,\
	Access-Control-Allow-Origin: http://localhost:8080, Authorization: Bearer ${$cookies.get('token')}`;
	//window.alert(data);
	//return false;
	$http.post(url, data, config).then(function (response) {

	// This function handles success
		try{
			var data=response.data;
			email=data[1].email;
			mobile=data[1].mobile;

		  $scope.fullName=`${data[1].fname} ${data[1].lname}`;
		  $scope.seatLeft=data[0].capacity;
		  $scope.destination=data[0].destination;
		  $scope.startDateTime=`${ data[0].startDateTime.split(" ")[0].split("-")[2] } /
		 				${data[0].startDateTime.split(" ")[0].split("-")[1]} /
		 				${data[0].startDateTime.split(" ")[0].split("-")[0]}  
		 				${data[0].startDateTime.split(" ")[1].slice(0,5)}`;

		 $scope.carType=data[0].carType;
		 $scope.carModel= data[0].carModel;
		 $scope.vehicle=data[0].vehicleNo;
		 $scope.fare=data[0].fare;	
		}
		catch(e){
			var data=response.data;
			if(data.response=="UNAUTHORIZED"){
				window.alert(`Please login again.`);
				$location.path('/login');			
			}
		}
		  


	}, function (response) {

	// this function handles error
	window.alert(`Sorry, An error occurred. Please try again.`);
	$location.path('/loggedInSearchRide');

	});
		

	$scope.pay=function(cardCash){
		if(cardCash==1){
			var card=$scope.cardNum;
			var cvv=$scope.cvv;
			
			var patt = /\d/g;
			
			if(card==undefined)
				var result = card.match(patt);
			
			$scope.errorCard=``;
			$scope.errorCvv=``;

			if(result.length!=16){
				$scope.errorCard=`<div class='alert alert-danger'>Please give a valid Card number.</div>`;
				return false;
			}
			result=cvv.match(patt);
			if(result.length!=3){
				$scope.errorCvv=`<div class='alert alert-danger'>Please give a valid CVV.</div>`;
				return false;
			}
			
			
		}

		if(!confirm('Are you sure?') ){
			return false;
		}

		var mode="";
		if(cardCash==1)
			mode='CARD';
		else if(cardCash==2)
			mode='CASH';

		var payObj={
			'rideId':`${rideId}`,
			
			'mode':`${mode}`,
			'token':$cookies.get('token')
		};

		//post data to api
		var url = '/api/bookRide', data = `${JSON.stringify(payObj)}`,config=`application/json,\
		Access-Control-Allow-Origin: http://localhost:8080, Authorization: Bearer ${$cookies.get('token')}`;

		$http.post(url, data, config).then(function (response) {

			var data=response.data;

			if( data.response=='UNAUTHORIZED' )
			{
				window.alert(`Please login again.`);
				$location.path('/login');
			}
			else if(data.response=='SEATS FULL'){
				$scope.response=`
					<div class='alert alert-danger'>
						<strong>Unfortunately seats got full during the time of booking.</strong>
						Please try again for different ride.
					</div>
				`;	
			}
			else if(data.response=='SUCCESS'){
				$scope.response=`
					<div class='alert alert-success'>
						You ride has been booked. Please use transaction ID ${data.tid} for future reference.
						<strong>Ride Owner's email:${email}, mobile: ${mobile}</strong>
					</div>
				`;
			}


		}, function (response) {

			$scope.response=`
					<div class='alert alert-danger'>
						An error occurred.Please try again later.
					</div>
				`;	

		});
		


	}

}