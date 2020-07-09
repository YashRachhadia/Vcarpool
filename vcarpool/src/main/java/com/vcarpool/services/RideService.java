package com.vcarpool.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcarpool.entity.paymentEntity;
import com.vcarpool.entity.rideDetailsEntity;
import com.vcarpool.entity.riderDetailsEntity;
import com.vcarpool.jwtAuth.jwtAuthentication;
import com.vcarpool.repos.AddRideDetailRepository;
import com.vcarpool.repos.employeeRepo;
import com.vcarpool.repos.paymentsRepo;
import com.vcarpool.repos.riderDetailsRepo;


@Service
@Transactional
public class RideService {
	
	
	public String cancelRide(Map<String, String> body,AddRideDetailRepository repository,
			riderDetailsRepo repoRiders,paymentsRepo repoPay,jwtAuthentication jwtObject) {
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		//get ride.ride_status
		rideDetailsEntity ride=repository.findByRideId( Integer.parseInt(body.get("rideId") ) );
		if(ride.getRideStatus().equals("TO BE COMPLETED")) {
			//change payment_status in payments
			//get list of tid and change payment status
			List<riderDetailsEntity> riders=repoRiders.findByRide( ride );
			 for(riderDetailsEntity r:riders)
			 {
				if(r.getRideStatus().equals("BOOKED")) {
					paymentEntity payment=repoPay.findByTid(r.getPayment().getTid() );
					 if( payment.getMode().equals("CASH") ) {
						 if( payment.getStatus().equals("PENDING") )
							 payment.setStatus("REFUNDED");
						 else if( payment.getStatus().equals("PAID") )
							 payment.setStatus("REFUND");
					 }
					 else if( payment.getMode().equals("CARD") ) {
						 payment.setStatus("REFUNDED");
					 }
					 //update lastUpdate
					 payment.setLastUpdate(body.get("timestamp") );
					 
					//change ride status in riders entity
					 //to ride cancelled
					 r.setRideStatus("RIDE CANCELLED");
				} 
				 
			 }
			 //finally change status of rideEntity
			 repository.findByRideId( Integer.parseInt( body.get("rideId") ) ).setRideStatus("CANCELLED");;
			 return " {\"response\" : \"RIDE CANCELLED\"} ";
		}
		else {
			return " {\"response\" : \"RIDE ALREADY CANCELLED\"} ";
		}
		
		
		 
		
		
	}
	
	@Transactional
	public paymentEntity book(Map<String, String> body,rideDetailsEntity ride,int empId,
			AddRideDetailRepository repository,riderDetailsRepo repoRiders,
			employeeRepo repoEmp,paymentsRepo repoPay
			) throws Exception{
		
		//create a new payment
		paymentEntity pay=new paymentEntity();
		pay.setMode( body.get("mode") );
		
		if( body.get("mode").equals("CASH") )
			pay.setStatus("PENDING");
		else if( body.get("mode").equals("CARD") )
			pay.setStatus("PAID");
		
		repoPay.save(pay);
		
		//create rider entity
		riderDetailsEntity rider=new riderDetailsEntity();
		rider.setPayment(pay);
		rider.setRideStatus("BOOKED");
		rider.setEmployee( repoEmp.findByEmpId( empId ) );
		

		//reduce capacity by 1
		ride.setCapacity(ride.getCapacity()-1);		
		List<riderDetailsEntity> riders=ride.getRiders();
		riders.add(rider);
		ride.setRiders( riders );
		
		rider.setRide(ride);
		
		repoRiders.save(rider);
		repository.save(ride);
		
		return pay;
	}
	
	public String getCashPayingRiders(Map<String, String> body,
			AddRideDetailRepository repository,riderDetailsRepo repoRiders,
			paymentsRepo repoPay,jwtAuthentication jwtObject) throws Exception{
		
		//body, repository, repoRiders, repoPay, jwtObject
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		
		rideDetailsEntity ride=repository.findByRideId( Integer.parseInt( body.get("rideId") ) );
		
		if(ride!=null) {
			List<riderDetailsEntity> result=repoRiders.findCashPayingRiderAfterRideComplete(
					ride );
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonRes = mapper.writeValueAsString(result);
			
			return jsonRes;
		}
		
		return " {\"response\":\"NO RIDE FOUND\"} ";
	}
	
	//rideCompletion(body, repository, repoRiders, repoPay, jwtObject)
	@Transactional
	public String rideCompletion(Map<String, String> body,
			AddRideDetailRepository repository,riderDetailsRepo repoRiders,
			paymentsRepo repoPay,jwtAuthentication jwtObject) throws Exception{
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		//change rider_details.rideStatus to completed
		//cash payment statuses to paid
		//ride status to completed
		rideDetailsEntity ride=repository.findByRideId(Integer.parseInt(body.get("rideId")));
		ride.setRideStatus("COMPLETED");
		ride.setEndDateTime(body.get("endDateTime"));
		repository.save(ride);
		
		List<riderDetailsEntity> riders=repoRiders.findByRide(ride);
		for( riderDetailsEntity rider:riders)
		{
			rider.setRideStatus("COMPLETED");
			paymentEntity payment=rider.getPayment();
			if(payment.getMode().equals("CASH") && payment.getStatus().equals("PENDING") )
			{
				payment.setStatus("PAID");
				repoPay.save(payment);
			}
			
			repoRiders.save(rider);
		}
			
		return " {\"response\":\"RIDE COMPLETED\"} ";
	}
	
	public String rideUpcoming(Map<String, String> body,
			AddRideDetailRepository repository,riderDetailsRepo repoRiders,
			paymentsRepo repoPay,employeeRepo empRepo,jwtAuthentication jwtObject) throws Exception{
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		
		//get page no. and send rideDetailsEnt,rider.status,payment
		int pageNo=Integer.parseInt( body.get("pageNo") );
		Pageable PageWithTwentyFiveElements = PageRequest.of(pageNo-1, 25);
		Page<riderDetailsEntity> riders=repoRiders.
				findByEmployeeInnerJoinRide( empRepo.findByEmpId( empId )
				,PageWithTwentyFiveElements);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonRes = mapper.writeValueAsString(riders);
		
			
		//return " {\"response\":\"RIDE COMPLETED\"} ";
		return jsonRes;
	}
	
	@Transactional
	public String cancelRidership(Map<String, String> body,
			AddRideDetailRepository repository,riderDetailsRepo repoRiders,
			paymentsRepo repoPay,employeeRepo empRepo,jwtAuthentication jwtObject) throws Exception{
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		//when can a ridership be cancelled
		//mode=cash,riderStatus=booked,paymentStatus=pending,rideStatus=to be completed
		//mode=card,riderStatus=booked,paymentStatus=paid,rideStatus=to be completed
		String paymentMode=body.get("paymentMode");
		String riderStatus=body.get("riderStatus");
		String paymentStatus=body.get("paymentStatus");
		String rideStatus=body.get("rideStatus");
		int rideId=Integer.parseInt(body.get("rideId"));
		if( riderStatus.equals("BOOKED") && rideStatus.equals("TO BE COMPLETED") ) {
			if( ( paymentMode.equals("CASH") && paymentStatus.equals("PENDING") ) 
			 || ( paymentMode.equals("CARD") && paymentStatus.equals("PAID") ) )
			{
				//change riderStatus to rider cancelled and payment status to refunded
				riderDetailsEntity rider=repoRiders.findByEmployeeAndRide( empRepo.findByEmpId(empId),
						repository.findByRideId(rideId) );
				rider.setRideStatus("RIDER CANCELLED");
				paymentEntity payment=rider.getPayment();
				payment.setStatus("REFUNDED");
				
				repoRiders.save(rider);
				repoPay.save(payment);
				
				return " {\"response\":\"RIDERSHIP CANCELLED\"} ";
			}
			
				
		}
			
		return " {\"response\":\"COULD NOT CANCEL RIDERSHIP\"} ";
		//return jsonRes;
	}
 
	
	
	public String bookRide(Map<String, String> body,jwtAuthentication jwtObject,
			AddRideDetailRepository repository,riderDetailsRepo repoRiders,
			employeeRepo repoEmp,paymentsRepo repoPay) throws Exception{
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		//check if seats left
		rideDetailsEntity ride=repository.findByRideId( Integer.parseInt( body.get("rideId") ) );
		int seatLeft=ride.getCapacity();
		
		String response="";
		if(seatLeft>0) {
			paymentEntity pay=book(body,ride,empId,repository,repoRiders,repoEmp,repoPay);
			response="{\"response\":\"SUCCESS\", \"tid\":\""+pay.getTid()+"\"}";
			
		}
		else {
			response="{\"response\":\"SEATS FULL\"}";
			
		}
		
	    return response;
		
	}
}
