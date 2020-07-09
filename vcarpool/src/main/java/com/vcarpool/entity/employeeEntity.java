package com.vcarpool.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="employee")
public class employeeEntity{
	

	@Id
	@Column(name="emp_id", nullable=false)
    private int empId;
    
     
    @Column(name="fname_mname", nullable=false)
    private String fname;
    
    @Column(name="lname", nullable=false)
    private String lname;
    
    @Column(name="carpool_service", nullable=false)
    private String carpoolService="NO";
    
    @Column(name="email", nullable=false)
    private String email;
    
    @Column(name="mobile", nullable=false)
    private String mobile;
    
    @JsonBackReference
	@OneToMany(mappedBy = "employee", cascade = {
	        CascadeType.ALL})
	private List<riderDetailsEntity> rider;
	
	@JsonBackReference
	@OneToMany(mappedBy = "employee", cascade = {
	        CascadeType.ALL})
	private List<rideDetailsEntity> rides;
	

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCarpoolService() {
		return carpoolService;
	}

	public void setCarpoolService(String carpoolService) {
		this.carpoolService = carpoolService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<riderDetailsEntity> getRider() {
		return rider;
	}

	public void setRider(List<riderDetailsEntity> rider) {
		this.rider = rider;
	}

	public List<rideDetailsEntity> getRides() {
		return rides;
	}

	public void setRides(List<rideDetailsEntity> rides) {
		this.rides = rides;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carpoolService == null) ? 0 : carpoolService.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + empId;
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((rider == null) ? 0 : rider.hashCode());
		result = prime * result + ((rides == null) ? 0 : rides.hashCode());
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
		employeeEntity other = (employeeEntity) obj;
		if (carpoolService == null) {
			if (other.carpoolService != null)
				return false;
		} else if (!carpoolService.equals(other.carpoolService))
			return false;
		
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empId != other.empId)
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (rider == null) {
			if (other.rider != null)
				return false;
		} else if (!rider.equals(other.rider))
			return false;
		if (rides == null) {
			if (other.rides != null)
				return false;
		} else if (!rides.equals(other.rides))
			return false;
		return true;
	}

	public int getEmpId() {
		return empId;
	}


    
    
}
