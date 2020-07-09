package com.vcarpool.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vcarpool.entity.registeredUserEntity;
import com.vcarpool.repos.registeredUserRepo;

@Service
public class authUserDetailService implements UserDetailsService{

	@Autowired
    registeredUserRepo regUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		//load hospital.employee details
		registeredUserEntity employee=regUserRepo.findByEmpId( Integer.parseInt(username) );
		
		User user=new User(""+employee.getEmpId()+"",employee.getPassword(),new ArrayList<>());
		return user;
		
	}
	
	
}
