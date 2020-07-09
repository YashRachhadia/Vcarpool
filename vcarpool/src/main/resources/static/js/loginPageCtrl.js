

function loginPageCtrlFunction($scope,$http,$location){
    $scope.page_heading = "Member Login";
    $scope.changeHead = function(){
        $scope.page_heading = "Sign Up";
    };
    $scope.btn = "Login";
    $scope.changebtn = function(){
        $scope.btn = "Sign Up";
    };    

    $scope.link = "Create your account";
    $scope.go_back = "Create your account";
    $scope.linkclass = "fa fa-long-arrow-right m-l-5";
    $scope.changeLink = function(){
        $scope.go_back = "#";
        if($scope.link === "Go Back"){
            $scope.go_back = "Login.html";
        }
        $scope.link = "Go Back";
        $scope.linkclass = "fa fa-long-arrow-left m-l-5";
    };
    
    $scope.showcheck = true;
    $scope.hideCheckBox = function(){
        $scope.showcheck = false;
    };

    $scope.login= function(){
        if($scope.empId==undefined || $scope.empId<=0 || $scope.empId=='' ){
            window.alert('Please give valid Employee ID.');
            return false;
        }

        if($scope.password==undefined || $scope.password=='' ){
            window.alert('Please give a password.');
            return false;
        }

        var role="user";
                      

        var loginObj={
            empId:`${$scope.empId}`,
            password:$scope.password,
            role:`${role}`
        };

        //post data to api
        var url = '/api/login',
         data = `${JSON.stringify(loginObj)}`,config='application/json,\
        Access-Control-Allow-Origin: http://localhost:8080';
        //window.alert(data);
        //return false;
        $http.post(url, data, config).then(function (response) {

        // This function handles success
        try {
              var data=response.data;
              
              if(data.response=="EMPID NOT REGISTERED")
              { $scope.postResponse=`<br>
                <div class="alert alert-danger">
                    Sorry, you aren't registered yet.
                </div>
              `;
              
              }
              else if( data.response=="WRONG PASSWORD" )
                $scope.postResponse=`<br>
                <div class="alert alert-danger">
                    Wrong Credentials.
                </div>
              `;

              else if( data.response=="AUTHENTICATED" )
              {
                //if role is user redirect to userHomePage
                if( loginObj.role=="user" ){
                    $location.path('/userHomePage');
                }
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
                    Wrong Credentials. 
                </div>
              `;

        });

    };

}