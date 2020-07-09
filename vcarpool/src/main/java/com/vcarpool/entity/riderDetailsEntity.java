package com.vcarpool.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
//@IdClass(riderId.class)
@Table(name="rider_details")
public class riderDetailsEntity {
     
	@Id
	@Column(name="transaction_id", nullable=false)
    private int tid;

	@MapsId
	@JsonManagedReference
    @OneToOne(mappedBy = "rider")
    @JoinColumn(name = "transaction_id")   //same name as id @Column
    private paymentEntity payment;
    
    
    @Column(name="ride_status", nullable=false)
    private String rideStatus;
	
    @JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ride_id", nullable=false)
	private rideDetailsEntity ride;
    
    @JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id", insertable=true, updatable=true)
	private employeeEntity employee;
    
	public employeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(employeeEntity employee) {
		this.employee = employee;
	}

	public riderDetailsEntity() {
		
	}
	
	public paymentEntity getPayment() {
		return payment;
	}

	public void setPayment(paymentEntity payment) {
		this.payment = payment;
	}

	public int getTid() {
		return tid;
	}




	public String getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}

	public rideDetailsEntity getRide() {
		return ride;
	}

	public void setRide(rideDetailsEntity ride) {
		this.ride = ride;
	}

	
	
}
