package com.vcarpool.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcarpool.entity.paymentEntity;

@Repository
public interface paymentsRepo extends JpaRepository<paymentEntity,Long>{
	paymentEntity findByTid(int tid);
}
