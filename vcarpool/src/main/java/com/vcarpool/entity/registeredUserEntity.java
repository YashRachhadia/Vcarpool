package com.vcarpool.entity;
import javax.persistence.*;

@Entity
@Table(name="registered_users")
public class registeredUserEntity {
	@Id
    private int empId;
    
     
    @Column(name="password", nullable=false)
    private String password;//password will be stored in md5 hash


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
