package com.vcarpool.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vcarpool.entity.employeeEntity;


@Repository
public interface employeeRepo extends JpaRepository<employeeEntity,Long> {
	
	employeeEntity findByEmpId( int id );
	
}
