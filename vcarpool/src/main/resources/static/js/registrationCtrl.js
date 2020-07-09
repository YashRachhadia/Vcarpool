function registerationCtrlFunction($scope,$http,$location){

$scope.search=function(){
       if( $scope.destinationSearch==undefined || $scope.destinationSearch==''  ){
            window.alert( $scope.destinationSearch );
            return false;
        }
        else{
            //redirect
            $location.path('/searchRides');
        }
    };

	$scope.register=function(){
		
		$scope.registerResponse=``;

		if($scope.empId==undefined || $scope.empId<=0)
		{
			window.alert("Please give a valid EmpID.");
			return false;
		}
		if($scope.password==undefined)
		{
			window.alert("Please give a password.");
			return false;
		}

		if($scope.password.search(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/)==-1){
			$scope.registerResponse=`<div class="alert alert-danger">
				Password doesn't match the given criteria.	
			</div>`;
			return false;	
		}
		if($scope.password!=$scope.confPassword){
			$scope.registerResponse=`<div class="alert alert-danger">
				Confirm Password doesn't match the given password.	
			</div>`;
			return false;	
		}

		var loginObj={
            empId:`${$scope.empId}`,
            password:$scope.password
        };

        //post data to api
        var url = '/api/newRegister',
         data = `${JSON.stringify(loginObj)}`,config='application/json,\
        Access-Control-Allow-Origin: http://localhost:8080';
        //window.alert(data);
        //return false;
        $http.post(url, data, config).then(function (response) {

        // This function handles success
        try {
              var data=response.data;
              
              if(data.response=="EMPID NOT VALID")
              { $scope.postResponse=`<br>
                <div class="alert alert-danger">
                    Sorry, you EMPID is not valid.
                </div>
              `;
              
              }
              else if( data.response=="ALREADY REGISTERED" )
                $scope.postResponse=`<br>
                <div class="alert alert-danger">
                    An account already exists with the given EMPID.
                </div>
              `;

              else if( data.response=="REGISTERED" )
              {
                $scope.postResponse=`<br>
                <div class="alert alert-success">
                    You are now registered. Please login to continue.
                </div>
              `;
              }


            }
            catch(err) {
                $scope.postResponse=`
                <div class="alert alert-danger">
                    Sorry, an error occured.
                </div>
              `;
            }

        }, function (response) {

        // this function handles error
        $scope.postResponse=`
                <div class="alert alert-danger">
                    Sorry, an error occured.
                </div>
              `;

        });


	};

	

}