package com.vcarpool.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vcarpool.entity.registeredUserEntity;


@Repository
public interface registeredUserRepo extends JpaRepository<registeredUserEntity,Long>{
	
	registeredUserEntity findByEmpIdAndPassword(int empId,String password);
	registeredUserEntity findByEmpId(int empId);

}

