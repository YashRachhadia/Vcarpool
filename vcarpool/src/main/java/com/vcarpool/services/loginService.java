package com.vcarpool.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcarpool.repos.employeeRepo;
import com.vcarpool.repos.registeredUserRepo;
import com.vcarpool.entity.employeeEntity;
import com.vcarpool.entity.registeredUserEntity;

@Service
public class loginService {
	
	@Autowired
    registeredUserRepo regUserRepo;
	
	@Autowired
	employeeRepo employeeRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String registerUser(Map<String, String> body) throws Exception{
		
		String response="";
		int empId=Integer.parseInt(body.get("empId"));
		String password=bCryptPasswordEncoder.encode(body.get("password"));
		
		employeeEntity employee=new employeeEntity();
		employee=employeeRepository.findByEmpId(empId);
		
		if( employee==null ) {
			response="{\"response\":\"EMPID NOT VALID\"}";
		}
		else if( employee.getCarpoolService().equals("YES") ) {
			response="{\"response\":\"ALREADY REGISTERED\"}";
		}
		else {
			//use transaction control
			changeCarpoolStatusAndAddUser(empId,password,employee);
			response="{\"response\":\"REGISTERED\" }";
		}
		
		return  response;
		
	}
	
	@Transactional
	public void changeCarpoolStatusAndAddUser(int empId,String password,employeeEntity employee) {
		
		registeredUserEntity regUser=new registeredUserEntity();
		regUser.setEmpId(empId);
		regUser.setPassword(password);
		
		employee.setCarpoolService("YES");
		
		regUserRepo.save(regUser);
		
	}
	
	public String getFname(int empId) throws Exception{
		//first check if empId registered
		
		String fname=employeeRepository.findByEmpId(empId).getFname();
		
		return fname;
		
	}
}
