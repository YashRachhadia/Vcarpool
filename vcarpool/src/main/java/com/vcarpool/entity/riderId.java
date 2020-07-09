package com.vcarpool.entity;

import java.io.Serializable;

public class riderId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int empId;
	
    private rideDetailsEntity ride;
    
    
    public riderId() {
    	
    }
    
	public riderId(int empId, rideDetailsEntity ride) {
		this.empId = empId;
		this.ride=ride;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empId;
		result = prime * result + ((ride == null) ? 0 : ride.hashCode());
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
		riderId other = (riderId) obj;
		if (empId != other.empId)
			return false;
		if (ride == null) {
			if (other.ride != null)
				return false;
		} else if (!ride.equals(other.ride))
			return false;
		return true;
	}


	


	
    
    
}