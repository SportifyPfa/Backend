package com.sportify.UserMicroService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.UserMicroService.entity.User;
import com.sportify.UserMicroService.entity.UserRole;
import com.sportify.UserMicroService.http.header.HeaderGenerator;
import com.sportify.UserMicroService.repository.UserRepository;
import com.sportify.UserMicroService.repository.UserRoleRepository;
import com.sportify.UserMicroService.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
	 /*@Autowired
	    private UserService userService;
	    
	    @Autowired
	    private HeaderGenerator headerGenerator;
	    */
	@Autowired
    private UserRepository ur;
	
	@Autowired
    private UserRoleRepository urole;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
	    @GetMapping (value = "/users")
	    public ResponseEntity<List<User>> getAllUsers(){
	        List<User> users =  ur.findAll();
	        return new ResponseEntity(users, HttpStatus.CREATED);
	        
	    }

	    @GetMapping (value = "/users", params = "name")
	    public ResponseEntity<User> getUserByName(@RequestParam("name") String userName){
	    	User user = ur.findByUserName(userName);
	    	return new ResponseEntity(user, HttpStatus.CREATED);
	    }

	    @GetMapping (value = "/users/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
	        User user = ur.getOne(id);
	   
	        	return new ResponseEntity(user, HttpStatus.CREATED);
	    	
	    }

	    @PostMapping (value = "/users")
	    public ResponseEntity<User> addUser(@RequestBody User user){
	    	urole.save(user.getRole());

	    	
	    			//userService.saveUser(user);
	    			return new ResponseEntity(ur.save(user), HttpStatus.CREATED);
	    				//	new ResponseEntity<User>(
	    				//	user,
	    					//headerGenerator.getHeadersForSuccessPostMethod(request, user.getId()),
	    	
	    }
}
