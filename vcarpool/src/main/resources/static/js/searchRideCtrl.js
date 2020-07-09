function searchRideCtrlFunction($scope,$http,$cookies,$location){
	
	$scope.data=null;

	$scope.totalPages=0;


	$scope.search=function(pageNo){
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
		  	try{
				$scope.resultBad=`
				  	`;
		  		$scope.data=response.data;
			   	$scope.totalPages=$scope.data.totalPages;
			   	$scope.data.content;
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
		   	

		   });
	};
}

