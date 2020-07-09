package com.vcarpool.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ride_details")
public class rideDetailsEntity {
	
    @Id
    @Column(name = "ride_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rideId;
    
     
    @Column(name="destination", nullable=false)
    private String destination;
    
   // private String startTime;
   // private String date;
    
    @Column(name="start_datetime", nullable=false)
    private String startDateTime;
    
    
    @JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id", insertable=true, updatable=true)
	private employeeEntity employee;
    
    @Column(name="end_datetime")
    private String endDateTime=null;
    
    @Column(name="car_type", nullable=false)
    private String carType;
    
    @Column(name="car_model", nullable=false)
    private String carModel;
    
    @Column(name="capacity", nullable=false)
    private int capacity;
    
    @Column(name="vehicle_no", nullable=false)
    private String vehicleNo;
    
    @Column(name="fare", nullable=false)
    private int fare;

	@Column(name="ride_status")
    private String rideStatus="TO BE COMPLETED";
	
	@JsonManagedReference
	@OneToMany(mappedBy = "ride", cascade = {
	        CascadeType.ALL
	    })
	    private List < riderDetailsEntity > riders;

	

	public rideDetailsEntity() {
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((carModel == null) ? 0 : carModel.hashCode());
		result = prime * result + ((carType == null) ? 0 : carType.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((endDateTime == null) ? 0 : endDateTime.hashCode());
		result = prime * result + fare;
		result = prime * result + rideId;
		result = prime * result + ((rideStatus == null) ? 0 : rideStatus.hashCode());
		result = prime * result + ((riders == null) ? 0 : riders.hashCode());
		result = prime * result + ((startDateTime == null) ? 0 : startDateTime.hashCode());
		result = prime * result + ((vehicleNo == null) ? 0 : vehicleNo.hashCode());
		return result;
	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		rideDetailsEntity other = (rideDetailsEntity) obj;
		if (capacity != other.capacity)
			return false;
		if (carModel == null) {
			if (other.carModel != null)
				return false;
		} else if (!carModel.equals(other.carModel))
			return false;
		if (carType == null) {
			if (other.carType != null)
				return false;
		} else if (!carType.equals(other.carType))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (endDateTime == null) {
			if (other.endDateTime != null)
				return false;
		} else if (!endDateTime.equals(other.endDateTime))
			return false;
		if (fare != other.fare)
			return false;
		if (rideId != other.rideId)
			return false;
		if (rideStatus == null) {
			if (other.rideStatus != null)
				return false;
		} else if (!rideStatus.equals(other.rideStatus))
			return false;
		if (riders == null) {
			if (other.riders != null)
				return false;
		} else if (!riders.equals(other.riders))
			return false;
		if (startDateTime == null) {
			if (other.startDateTime != null)
				return false;
		} else if (!startDateTime.equals(other.startDateTime))
			return false;
		if (vehicleNo == null) {
			if (other.vehicleNo != null)
				return false;
		} else if (!vehicleNo.equals(other.vehicleNo))
			return false;
		return true;
	}






	public int getRideId() {
		return rideId;
	}




	public String getDestination() {
		return destination;
	}






	public void setDestination(String destination) {
		this.destination = destination;
	}






	public String getStartDateTime() {
		return startDateTime;
	}






	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}






	public employeeEntity getEmployee() {
		return employee;
	}






	public void setEmployee(employeeEntity employee) {
		this.employee = employee;
	}






	public String getEndDateTime() {
		return endDateTime;
	}






	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}






	public String getCarType() {
		return carType;
	}






	public void setCarType(String carType) {
		this.carType = carType;
	}






	public String getCarModel() {
		return carModel;
	}






	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}






	public int getCapacity() {
		return capacity;
	}






	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}






	public String getVehicleNo() {
		return vehicleNo;
	}






	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}






	public int getFare() {
		return fare;
	}






	public void setFare(int fare) {
		this.fare = fare;
	}






	public String getRideStatus() {
		return rideStatus;
	}






	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}






	public List<riderDetailsEntity> getRiders() {
		return riders;
	}






	public void setRiders(List<riderDetailsEntity> riders) {
		this.riders = riders;
	}






	@Override
	public String toString() {
		return "\"rideDetailsEntity\":[rideId=" + rideId + ", destination=" + destination + ", startDateTime="
				+ startDateTime + ", empId=" + employee.getEmpId() + ", endDateTime=" + endDateTime + ", carType=" + carType
				+ ", carModel=" + carModel + ", capacity=" + capacity + ", vehicleNo=" + vehicleNo + ", fare=" + fare
				+ ", rideStatus=" + rideStatus + "]";
	}
    
}


 
