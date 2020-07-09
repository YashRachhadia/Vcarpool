package com.vcarpool.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcarpool.entity.rideDetailsEntity;
import com.vcarpool.jwtAuth.jwtAuthentication;
import com.vcarpool.repos.AddRideDetailRepository;
import com.vcarpool.repos.employeeRepo;
import com.vcarpool.repos.paymentsRepo;
import com.vcarpool.repos.riderDetailsRepo;
import com.vcarpool.services.RideService;


@RestController
public class rideDetailsController {
	@Autowired
    AddRideDetailRepository repository;
	
	@Autowired
    riderDetailsRepo repoRiders;
	
	@Autowired
    employeeRepo repoEmp;
	
	@Autowired
    paymentsRepo repoPay;
	
	@Autowired
	jwtAuthentication jwtObject;
	
	@Autowired
	RideService rideService;
	@Autowired
	com.vcarpool.services.ContRideService ContRideService;
	
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/addRideDetails",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<String> addRide(@RequestBody Map<String, String> body) throws Exception {
			
		String response=ContRideService.addRide(body, repository, repoEmp, jwtObject);
		return new ResponseEntity<String>(response,new HttpHeaders(), HttpStatus.OK);
	    
	}			
	
	
	
	
	
	@CrossOrigin 
	@GetMapping(value = "/api/searchRide")
	public ResponseEntity<?> searchRide(@RequestParam String destination,@RequestParam String startDateTime,
			@RequestParam int pageNo)
			throws Exception{
		
		Pageable PageWithFiftyElements = PageRequest.of(pageNo-1, 50);
		
		Page<rideDetailsEntity> result=repository.
				findByDestinationAndStartDateTimeGreaterThanEqualAndRideStatusAndCapacityGreaterThan(destination.toUpperCase(),
				startDateTime,"TO BE COMPLETED",0,PageWithFiftyElements );
		
		return new ResponseEntity< Page<rideDetailsEntity> >(result,new HttpHeaders(), HttpStatus.OK);
		
	}
	
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/checkLegibility1",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<String> checkLegibility1(@RequestBody Map<String, String> body) throws Exception{
		
		String response=ContRideService.checkLegibility1(body, jwtObject, repoEmp, repository, repoRiders);
		
		return new ResponseEntity<String>(response,new HttpHeaders(), HttpStatus.OK);
		
	}
	
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/getRideDetails",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> getRideDetails(@RequestBody Map<String, String> body) throws Exception{
		
		String response=ContRideService.getRideDetails(body, jwtObject, repoEmp, repository);
		return new ResponseEntity< String >(response,new HttpHeaders(), HttpStatus.OK);
		
	}
	
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/bookRide",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> bookRide(@RequestBody Map<String, String> body) throws Exception{
		
		String response=rideService.bookRide(body, jwtObject, repository, repoRiders, repoEmp, repoPay);
		return new ResponseEntity< String >(response,new HttpHeaders(), HttpStatus.OK);
	    
	}
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/getCreatedRides",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> getCreatedRides(@RequestBody Map<String, String> body) throws Exception{
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		String fromDate=body.get("date");
		int pageNo=Integer.parseInt( body.get("pageNo") );
		Pageable PageWithTwentyFiveElements = PageRequest.of(pageNo-1, 25);
		
		Page<rideDetailsEntity> result=repository.findByEmployeeAndStartDateTimeGreaterThanEqualOrderByStartDateTimeDesc(
				repoEmp.findByEmpId(empId),fromDate,PageWithTwentyFiveElements );
		
		return new ResponseEntity< Page<rideDetailsEntity> >(result,new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	
	//modifyCreatedRide
	@CrossOrigin 
	@PostMapping(value = "/api/modifyCreatedRide",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> modifyCreatedRide(@RequestBody Map<String, String> body) throws Exception{
		
		String response=ContRideService.modifyCreatedRide(body, jwtObject, repository);
		return new ResponseEntity< String >(response,new HttpHeaders(), HttpStatus.OK);
		
	}
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/cancelCreatedRide",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> cancelCreatedRide(@RequestBody Map<String, String> body) throws Exception{
		
		String response=rideService.cancelRide(body, repository, repoRiders, repoPay, jwtObject);
		
		//String response=" { \"response\":\"RIDE MODIFIED\" } ";
		return new ResponseEntity< String >(response,new HttpHeaders(), HttpStatus.OK);
	}

	
	
	///getCashPayingRiders
	@CrossOrigin 
	@PostMapping(value = "api/getCashPayingRiders",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> getCashPayingRiders(@RequestBody Map<String, String> body) throws Exception{
		
		String result=rideService.getCashPayingRiders(body, repository, repoRiders, repoPay, jwtObject);
		
		return new ResponseEntity< String >(result,new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/rideCompletion",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> rideCompletion(@RequestBody Map<String, String> body) throws Exception{
		
		String result=rideService.rideCompletion(body, repository, repoRiders, repoPay, jwtObject);
		
		
		return new ResponseEntity< String >(result,new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/rideUpcoming",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> rideUpcoming(@RequestBody Map<String, String> body) throws Exception{
		
		String result=rideService.rideUpcoming(body, repository, repoRiders, repoPay,repoEmp , jwtObject);
		
		
		return new ResponseEntity< String >(result,new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	
	@CrossOrigin 
	@PostMapping(value = "/api/cancelRiderShip",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> cancelRiderShip(@RequestBody Map<String, String> body) throws Exception{
		
		String result=rideService.cancelRidership(body, repository, repoRiders, repoPay,repoEmp , jwtObject);
		
		
		return new ResponseEntity< String >(result,new HttpHeaders(), HttpStatus.OK);
	}
	
	
}
