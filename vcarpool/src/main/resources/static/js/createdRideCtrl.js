function createdRideCtrlFunction($scope,$http,$location,$cookies){

	if( $cookies.get('username')=='' || $cookies.get('username')==undefined  )
	{
		$location.path('/login');
	}
	else{
		$scope.username=$cookies.get('username');
	}

	$scope.sel1=``; $scope.sel2=``; $scope.sel3=``;
	$scope.minCap=1;

	var obj=null;

	$scope.data=null;

	$scope.totalPages=0;

	var currPageNo=1;

	var rideObjForRideCompletion=null;

	$scope.getRide=function(pageNo){

		$scope.resultBad=``;

		if( $scope.date==undefined)
			return false;

		//var givenTime=moment(`${ $('#date').val() } 00:
		//	00:00`,'YYYY-MM-DD hh:mm:ss');

		var rideObj={
			'date':`${ $('#date').val() } ${$('#hour').val()}:${$('#minute').val()}:00`,
			'pageNo':pageNo,
			'token':$cookies.get('token')
		};

		//window.alert(JSON.stringify(rideObj));
		var url = '/api/getCreatedRides',data = `${JSON.stringify(rideObj)}`,config=`application/json,\
	Access-Control-Allow-Origin: http://localhost:8080, Authorization: Bearer ${$cookies.get('token')}`;
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

	$scope.cancelRide=function(rid,rideStatus){

		if(rideStatus=='CANCELLED')
		{
			window.alert('Ride already Cancelled.');
			return false;
		}

		if(!confirm('Are you sure you want to cancel?') )
			return false;
		
		var rideObj={
			rideId:rid,
			timestamp:`${moment().format("YYYY-MM-DD HH:mm")}:00`,
			token:	$cookies.get('token')
		};

		var url = '/api/cancelCreatedRide',data = `${JSON.stringify(rideObj)}`,config=`application/json,\
		Access-Control-Allow-Origin: http://localhost:8080, Authorization: Bearer ${$cookies.get('token')}`;

		$http.post(url, data, config).then(function (response) {
		//window.alert('here');
		try{
			response.data;
		   	
		   	if(response.data.response=='UNAUTHORIZED'){
					window.alert(`Your session has expired.`);
					$location.path('/login');
			}
			else if(response.data.response=='RIDE CANCELLED'){
				//window.alert('here');
				window.alert(`Ride Cancelled. All transactions for this ride will be reverted.`);
				$scope.getRide(currPageNo);
			}
			else{
				window.alert(`Ride already Cancelled/Expired.`);
					
			}
		   	
		}
		catch(e){

			window.alert(`Sorry, An error occurred. Please try again.`);
				
		}


		}, function (response) {

			// this function handles error
			window.alert(`Sorry, An error occurred. Please try again.`);

			});


		
	};

	$scope.modifyRide=function(node){

		//open a modal
		obj=node;

		var inp=document.getElementsByClassName('disableModify');
			//for(var i=0;i<inp.length;i++)
			//	inp[i].disabled='false';

		if( moment().add(1, 'h') > moment(`${node.startDateTime}`, "YYYY-MM-DD HH:mm:00") ||
		obj.rideStatus!='TO BE COMPLETED' )
		{

			//var inp=document.getElementsByClassName('disableModify');
			//for(var i=0;i<inp.length;i++)
			//	inp[i].disabled='true';
			document.getElementById('contModifyBtn').style.display='none';

			return false;
		}

		$scope.carType=node.carType;
		if(node.carType=='HATCHBACK')
			$scope.sel1='selected';
		else if(node.carType=='SEDAN')
			$scope.sel2='selected';
		else if(node.carType=='SUV')
			$scope.sel3='selected';

		$scope.carType=node.carType;
		$scope.vehicleNumber=node.vehicleNo;
		$scope.capacity=node.capacity+node.riders.length;
		$scope.carModel=node.carModel;
		$scope.selectedRideStatus=node.rideStatus;

		if(node.riders.length>1)
			$scope.minCap=node.riders.length;
	}

	$scope.contModify=function(){
		
		if( $scope.selectedRideStatus=='CANCELLED' ){
			window.alert('Ride already Cancelled.');
			return false;
		}

		var rideObj={
			rideId:obj.rideId,
			carType:$scope.carType,
			vehicleNo:$scope.vehicleNumber,
			capacity:$scope.capacity-obj.riders.length,
			carModel:$scope.carModel
		};

		if($scope.vehicleNumber==undefined){
			window.alert('Please give vehicle number.');
			return false;	
		}	

		if($scope.carModel==undefined){
			window.alert('Please give your car model for easy identification.');
			return false;	
		}

		if($scope.capacity<$scope.minCap || $scope.capacity==0 ||$scope.capacity==undefined)
		{
			window.alert('capacity cannot be 0 or less than the number of riders booked.');
			return false;
		}

		rideObj.token=$cookies.get('token');

		var url = '/api/modifyCreatedRide',data = `${JSON.stringify(rideObj)}`,config=`application/json,\
		Access-Control-Allow-Origin: http://localhost:8080,Authorization: Bearer ${$cookies.get('token')}`;

		$http.post(url, data, config).then(function (response) {
		//window.alert('here');
		try{
			response.data;
		   	
		   	if(response.data.response=='UNAUTHORIZED'){
					window.alert(`Your session has expired.`);
					$location.path('/login');
			}
			else if(response.data.response=='RIDE MODIFIED'){
				//window.alert('here');
				$scope.message=`
				<div class='alert alert-success'>Ride modified successfully.</div>`;
				$scope.getRide(currPageNo);
			}
		   	
		}
		catch(e){

			window.alert(`Sorry, An error occurred. Please try again.`);
				
		}


		}, function (response) {

			// this function handles error
			window.alert(`Sorry, An error occurred. Please try again.`);

			});

		
	};

	$scope.confirmCashPayment=function(){

		// moment().add(1, 'h').format('YYYY-MM-DD HH:mm:00') -1 hr add

		var getRiderCashPay={
					token:$cookies.get('token'),
					rideId:`${rideObjForRideCompletion.rideId}`,
					endDateTime:`${moment().format('YYYY-MM-DD HH:mm:00')}`
				};
				//post request
				var url = '/api/rideCompletion',data = `${JSON.stringify(getRiderCashPay)}`,
				config=`application/json, Access-Control-Allow-Origin: http://localhost:8080,Authorization: Bearer ${$cookies.get('token')}`;

				$http.post(url, data, config).then(function (response) {
				
				var cashPaying=response.data;

				try{
					
				   	if(cashPaying.response=='UNAUTHORIZED'){
						window.alert(`Your session has expired.`);
						$location.path('/login');
					}
					else if(cashPaying.response=='RIDE COMPLETED'){
						$scope.promptForCashPay=`
						<div class='row'>
							<div class='col-md-12 col-sm-12 col-lg-12 text-center'>
								<img src="https://img.icons8.com/ultraviolet/64/000000/ok.png"/>
								<br><br>
								<div class='alert alert-success'>Ride completed.</div>
							</div>
						</div>
							
						`;
						document.getElementById('confirmPay').style.display="none";
						$scope.getRide(currPageNo);
					}				
				}
				catch(e){

					window.alert(`Sorry, An error occurred. Please try again.`);
				}


				}, function (response) {

					// this function handles error
					window.alert(`Sorry, An error occurred. Please try again.`);

				});


	}

	
	$scope.completeRide=function(ride){

		rideObjForRideCompletion=ride;


		//check if ride.status = "to be completed"
		if(ride.rideStatus=="TO BE COMPLETED"){
			//check if atleast one rider with status "booked" available
			var available=0;
			for(var i=0;i<ride.riders.length;i++)
				if( ride.riders[i].rideStatus=="BOOKED" )
				{
					available=1;
					break;
				}
			if(available==1){
				//continue stopping ride
				var getRiderCashPay={
					token:$cookies.get('token'),
					rideId:`${ride.rideId}`
				};
				//post request
				var url = '/api/getCashPayingRiders',data = `${JSON.stringify(getRiderCashPay)}`,
				config=`application/json, Access-Control-Allow-Origin: http://localhost:8080, Authorization: Bearer ${$cookies.get('token')}`;

				$http.post(url, data, config).then(function (response) {
				
				var cashPaying=response.data;

				try{
					
				   	if(cashPaying.response=='UNAUTHORIZED'){
						window.alert(`Your session has expired.`);
						$location.path('/login');
					}
					else if(cashPaying.response==undefined){

						if(cashPaying.length==0){
						//send ride complete
							$scope.confirmCashPayment();
						}
						else{
							//window.alert('262');
							var txt=`
							<div class='row'>
							<div class='col-sm-12 col-md-12 col-lg-12'>
							<div class='alert alert-info'>
							The following people need to pay cash.
							</div><br>
							<table class='table table-hover table-responsive'>
								<tr>
									<th>Emp ID</th>
									<th>Name</th>
									<th>Mobile</th>
									<th>Email</th>
								</tr>
							`;
							for(var i=0;i<cashPaying.length;i++ )
							{
								txt+=`<tr>
									<td>${cashPaying[i].employee.empId}</td>
									<td>${cashPaying[i].employee.fname} ${cashPaying[i].employee.lname}</td>
									<td>${cashPaying[i].employee.mobile}</td>
									<td>${cashPaying[i].employee.email}</td>
								</tr>`;
							}
							txt+=`<table> 
							</div></div>`;
							$scope.promptForCashPay=txt;
						}	
					}
					else if( cashPaying.response== 'NO RIDE FOUND')
					{
						window.alert('It appears we have an anomaly. The Ride couldn\'t be found. ');
					}
					
					
				}
				catch(e){

					window.alert(`Sorry, An error occurred. Please try again.`);
				}


				}, function (response) {

					// this function handles error
					window.alert(`Sorry, An error occurred. Please try again.`);

				});


			}
		}
		else{

			$scope.promptForCashPay=`
				<div class='row'>
					<div class='col-lg-12 col-sm-12 col-md-12 text-center'>
						<img src="https://img.icons8.com/clouds/100/000000/shrug-emoticon.png"/>
						<div class='alert alert-warning'>Ride already completed.</div>
					</div>
				</div>
			`;
			document.getElementById('confirmPay').style.display="none";
		}
	};
	

	

	$scope.revertDisplayBtn=function(){
		document.getElementById('confirmPay').style.display="";
		document.getElementById('contModifyBtn').style.display="";
	}

	$scope.showHide=function(rideId){
		//window.alert('sdd');

		if( document.getElementById(`${rideId}`).style.display=='none' )
			document.getElementById(`${rideId}`).style.display="";
		else
			document.getElementById(`${rideId}`).style.display="none";

	};
}