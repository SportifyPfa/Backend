package com.sportify.UserMicroService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.UserMicroService.entity.User;
import com.sportify.UserMicroService.entity.UserRole;
import com.sportify.UserMicroService.http.header.HeaderGenerator;
import com.sportify.UserMicroService.repository.UserRepository;
import com.sportify.UserMicroService.repository.UserRoleRepository;
//import com.sportify.UserMicroService.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository ur;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
//    private UserService userService;
    
 //   @Autowired
   // private HeaderGenerator headerGenerator;
    
    @PostMapping(value = "/registration")
    public ResponseEntity<User> addUser(@RequestBody User user){
    	if(user != null)
    		try {
    		
    	        
    			//userService.saveUser(user);
    			return new ResponseEntity(ur.save(user), HttpStatus.CREATED);
    				//	new ResponseEntity<User>(
    				//	user,
    					//headerGenerator.getHeadersForSuccessPostMethod(request, user.getId()),
    					//HttpStatus.CREATED);
    		}catch (Exception e) {
    			e.printStackTrace();
    			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

}
