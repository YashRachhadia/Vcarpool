package com.vcarpool.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcarpool.entity.employeeEntity;
import com.vcarpool.entity.rideDetailsEntity;
import com.vcarpool.jwtAuth.jwtAuthentication;
import com.vcarpool.repos.AddRideDetailRepository;
import com.vcarpool.repos.employeeRepo;
import com.vcarpool.repos.riderDetailsRepo;


@Service
@Transactional
public class ContRideService {
	
	
	public String addRide(Map<String, String> body,AddRideDetailRepository repository,
			employeeRepo repoEmp,jwtAuthentication jwtObject) {
		
		int empId=0;
		
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		rideDetailsEntity entity=new rideDetailsEntity();
		entity.setCapacity( Integer.parseInt(body.get("capacity")) );
		entity.setCarModel(body.get("carModel"));
		entity.setCarType(body.get("carType"));
		entity.setDestination(body.get("destination"));
		
		 employeeEntity employee=repoEmp.findByEmpId(empId);
		
		entity.setFare(Integer.parseInt(body.get("fare")));
		entity.setStartDateTime(body.get("startDateTime"));
		entity.setVehicleNo(body.get("vehicleNo"));
		
		//check if any pending ride present for the current empId
		String response="{\"response\":\"ERROR\"}";
		
		if(employee!=null)
		{	
			if( repository.findByEmployeeAndRideStatus(employee ,"TO BE COMPLETED" )==null )
			{	
				entity.setEmployee(employee);
				
				repository.save(entity);
				response="{\"response\":\"RIDE ADDED\"}";
			
			}
			else
			{	
				response="{\"response\":\"RIDE NOT COMPLETED\"}";
			}
		}
		
		return response;
	
	}
	
	public String checkLegibility1(Map<String, String> body,jwtAuthentication jwtObject,
			employeeRepo repoEmp,AddRideDetailRepository repository,riderDetailsRepo repoRiders) {
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		String response="";
		if( repository.findByEmployeeAndRideStatus(repoEmp.findByEmpId(empId) ,"TO BE COMPLETED")!=null 
				|| repoRiders.findByEmpIdAndRideStatus(empId,"BOOKED")!=null )
		{
			response="{\"response\":\"NOT ELIGIBLE\"}";
			
		}
		else
		{	
			response="{\"response\":\"ELIGIBLE\"}";
			
		}
		return response;
		
	}
	
	public String getRideDetails(Map<String, String> body,jwtAuthentication jwtObject,
			employeeRepo repoEmp,AddRideDetailRepository repository) {
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		int rideId=Integer.parseInt( body.get("rideId") );
		
		rideDetailsEntity result=repository.findByRideId(rideId);
		
		employeeEntity emp=repoEmp.findByEmpId( (int)result.getEmployee().getEmpId() );
		
		ObjectMapper mapper = new ObjectMapper();
	      //Converting the Object to JSONString
	      String jsonRide="";
	      String jsonEmp="";
		try {
			jsonRide = mapper.writeValueAsString(result);

		      jsonEmp = mapper.writeValueAsString(emp);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      String response="["+ jsonRide+","+jsonEmp +"]";
		
	      return response;
	      
	} 
	
	
	@Transactional	
	public String modifyCreatedRide(Map<String, String> body,jwtAuthentication jwtObject,
		AddRideDetailRepository repository) throws Exception{
		
		int empId=0;
		empId=Integer.parseInt( jwtObject.extractUsername( body.get("token") ) );
		
		rideDetailsEntity ride=repository.findByRideId( Integer.parseInt(body.get("rideId") ) );
		ride.setCarType( body.get("carType") );
		ride.setVehicleNo( body.get("vehicleNo") );
		ride.setCapacity( Integer.parseInt(body.get("capacity")));
		ride.setCarModel( body.get("carModel") );
		repository.save(ride);
		
		String response=" { \"response\":\"RIDE MODIFIED\" } ";
		return response;
	}
	
	

}