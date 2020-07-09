package com.vcarpool.controllers;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vcarpool.services.authUserDetailService;
import com.vcarpool.services.loginService;
import com.vcarpool.jwtAuth.*;


@RestController
public class loginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	jwtAuthentication jwtAuthentication; 
	
	@Autowired
	loginService loginService;
	
	@Autowired
	private authUserDetailService authUserDetailService;
	
	@CrossOrigin 
	@PostMapping(value = "/api/login",consumes = "application/json")
	public String checkLogin(@RequestBody Map<String, String> body,
			HttpServletResponse response) throws Exception{
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(body.get("empId"), body.get("password")
							));
				
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect mobile no. or password", e);
		}
		
		//get a JWT token
		//get fname
		final UserDetails userDetails = authUserDetailService
				.loadUserByUsername(body.get("empId"));
		
		final String jwt = jwtAuthentication.generateToken(userDetails);
		final String fname=loginService.getFname( Integer.parseInt(body.get("empId")) );
		
		//create a cookie
		Cookie cookieUserName = new Cookie("username",fname );
		cookieUserName.setMaxAge(1 * 10 * 60 * 60); // expires in 1 day
		cookieUserName.setPath("/"); // global cookie accessible every where
		
		Cookie cookieJwt = new Cookie("token",jwt );
		cookieJwt.setMaxAge(1 * 10 * 60 * 60); // expires in 1 day
		cookieJwt.setPath("/"); // global cookie accessible every where

		
		//add cookie to response
		response.addCookie(cookieUserName);
		response.addCookie(cookieJwt);
		
		String responseText= "{\"response\":\"AUTHENTICATED\"}";
		return responseText;
			}

	
}
