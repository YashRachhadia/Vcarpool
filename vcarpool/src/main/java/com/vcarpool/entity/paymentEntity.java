package com.vcarpool.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="payments")
public class paymentEntity {
	
	@Id
	@Column(name = "tran_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int tid;
	
	@Column(name="payment_status", nullable=false)
    private String status;
	
	@Column(name="last_update", nullable=false)
    private String lastUpdate;
	
	@Column(name="mode_of_payment", nullable=false)
    private String mode;
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private riderDetailsEntity rider;


	public riderDetailsEntity getRider() {
		return rider;
	}

	public void setRider(riderDetailsEntity rider) {
		this.rider = rider;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
}
