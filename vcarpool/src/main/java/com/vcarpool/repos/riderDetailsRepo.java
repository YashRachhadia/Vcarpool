package com.vcarpool.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vcarpool.entity.employeeEntity;
import com.vcarpool.entity.rideDetailsEntity;
import com.vcarpool.entity.riderDetailsEntity;


@Repository
public interface riderDetailsRepo extends JpaRepository<riderDetailsEntity,Long>{
	
	//riderDetailsEntity findByRideEmpIdAndRideStatus(int empId,String rideStatuse);
	
	@Query("select r from riderDetailsEntity r inner join r.employee emp where emp.empId = :empId "
			+ "and r.rideStatus= :rideStatus")
	riderDetailsEntity findByEmpIdAndRideStatus(@Param("empId") int empId,@Param("rideStatus") String rideStatus);
	
	List<riderDetailsEntity> findByRide(rideDetailsEntity ride);
	
	@Query("select r from riderDetailsEntity r inner join r.payment"
			+ " pay where pay.status ='PENDING'"
			+ " and  pay.mode='CASH' and r.ride= :ride ")
	List<riderDetailsEntity> findCashPayingRiderAfterRideComplete(@Param("ride") rideDetailsEntity ride);
	
	//Page<riderDetailsEntity> (,Pageable page);
	
	@Query("select r,r.ride from riderDetailsEntity r inner join r.ride ride where  "
			+ "r.employee= :employee")
	Page<riderDetailsEntity> findByEmployeeInnerJoinRide(@Param("employee") employeeEntity emp,
			Pageable page);
	
	riderDetailsEntity findByEmployeeAndRide(employeeEntity emp,rideDetailsEntity ride);
	
}
