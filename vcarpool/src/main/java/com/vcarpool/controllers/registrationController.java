package com.vcarpool.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vcarpool.services.loginService;

@RestController
public class registrationController {
	
	@Autowired
	loginService loginService;
	
	@CrossOrigin 
	@PostMapping(value = "/api/newRegister",consumes = "application/json",produces="application/json")
	public ResponseEntity<?> registerUser(@RequestBody Map<String, String> body) throws Exception{
		
		String response=loginService.registerUser(body);
		return  new ResponseEntity<String>(response,new HttpHeaders(), HttpStatus.OK);
		
	}	
	
	
}
