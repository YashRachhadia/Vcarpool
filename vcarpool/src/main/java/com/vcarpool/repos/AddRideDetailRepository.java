package com.vcarpool.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vcarpool.entity.*;

@Repository
public interface AddRideDetailRepository extends JpaRepository<rideDetailsEntity,Long>{
	
	rideDetailsEntity findByEmployeeAndRideStatus(employeeEntity emp,String rideStatus);// "TO BE COMPLETED"
	
	Page<rideDetailsEntity> findByDestinationAndStartDateTimeGreaterThanEqualAndRideStatusAndCapacityGreaterThan(String destination,
			String startDateTime,String status,int seatsLeft,Pageable page);
	
	Page<rideDetailsEntity> findByEmployeeAndStartDateTimeGreaterThanEqualOrderByStartDateTimeDesc(employeeEntity emp,String dateTime,Pageable page);
	
	rideDetailsEntity findByRideId(int id); 
}

 