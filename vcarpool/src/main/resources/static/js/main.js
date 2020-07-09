

var app = angular.module("vcarpool",["ngSanitize","ngRoute","ngCookies","ngAnimate"]);
app.config(['$routeProvider', function($routeProvider) {
            $routeProvider
            
            .when('/login', {
               templateUrl: 'loginAndLogout/login.html',
               controller: 'loginPageCtrl'
            })
            .when('/userHomePage', {
               templateUrl: 'userViews/registeredUserHomePage.html',
               controller: 'rideCreateCtrl'
            })
            .when('/logout', {
               templateUrl: 'loginAndLogout/logout.html',
               controller: 'logoutCtrl'
            })
            .when('/register', {
               templateUrl: 'loginAndLogout/register.html',
               controller: 'registerationCtrl'
            })
            .when('/searchRide', {
               templateUrl: 'searchRideView/search.html',
               controller: 'searchRideCtrl'
            })
            .when('/loggedInSearchRide', {
               templateUrl: 'userViews/searchRides.html',
               controller: 'loggedInSearchRideCtrl'
            })
            .when('/bookRide', {
               templateUrl: 'userViews/bookRide.html',
               controller: 'bookRideCtrl'
            })//loggedInCreatedRides
            .when('/loggedInCreatedRides', {
               templateUrl: 'userViews/createdRides.html',
               controller: 'createdRideCtrl'
            })
            .when('/loggedInUpcomingRides', {
               templateUrl: 'userViews/upcomingRides.html',
               controller: 'upcomingRideCtrl'
            })
            .otherwise({
               redirectTo: '/logout'
            });

         }]);


//############################Services###############################################################
app.service('passRideId', function() {
  
  var rideId = {}
   function set(data) {
     rideId = data;
   }
   function get() {
    return rideId;
   }

   return {
    set: set,
    get: get
   }

});




//############################controllers###############################################################
app.controller('searchRideCtrl', function($scope,$http,$cookies,$location){

    searchRideCtrlFunction($scope,$http,$cookies,$location);
});

app.controller('loginPageCtrl', function($scope,$http,$location){
    loginPageCtrlFunction($scope,$http,$location);
});

app.controller('registerationCtrl', function($scope,$http,$location){
    registerationCtrlFunction($scope,$http,$location);
});

app.controller('rideCreateCtrl', function($scope,$http,$cookies,$location){

    rideCreateCtrlFunction($scope,$http,$cookies,$location);
});
app.controller('searchRideCtrl', function($scope,$http,$location){

    searchRideCtrlFunction($scope,$http,$location);
});
app.controller('loggedInSearchRideCtrl', function($scope,$http,$location,$cookies,passRideId){

    loggedInSearchRideCtrlFunction($scope,$http,$location,$cookies,passRideId);
});
app.controller('bookRideCtrl', function($scope,$http,$location,$cookies,passRideId){

    bookRideCtrlFunction($scope,$http,$location,$cookies,passRideId);
});

app.controller('logoutCtrl', function($scope,$http,$cookies,$location){

    //delete all cookies then redirect to login page
    $cookies.remove('username');
    $cookies.remove('token');
    $location.path('/login');
    

});

app.controller('createdRideCtrl', function($scope,$http,$location,$cookies){

    createdRideCtrlFunction($scope,$http,$location,$cookies);
});

app.controller('upcomingRideCtrl', function($scope,$http,$location,$cookies,$sanitize){

    upcomingRideCtrlFunction($scope,$http,$location,$cookies);
});