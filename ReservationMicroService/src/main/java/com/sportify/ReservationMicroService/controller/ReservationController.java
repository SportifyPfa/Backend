package com.sportify.ReservationMicroService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.entity.Terrain;
import com.sportify.ReservationMicroService.entity.User;
import com.sportify.ReservationMicroService.feignclient.JoueurClient;
import com.sportify.ReservationMicroService.feignclient.TerrainClient;
import com.sportify.ReservationMicroService.service.ReservationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class ReservationController {
	
	@Autowired 
	JoueurClient jclient;
	
	@Autowired 
	ReservationService rs;
	
	@Autowired
	TerrainClient tClient;
	
	@PostMapping(value = "/{userId}")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation res,@PathVariable int userId,
    		HttpServletRequest request){
		User user = jclient.getUserById(userId);  
		
		res.setUser(user);	 
	    rs.saveReservation(res);
	    return new ResponseEntity(rs.saveReservation(res), HttpStatus.CREATED);
			
	}
	 @GetMapping("/data")
     public ResponseEntity<Reservation> getStudentInfo() {
       
		 return new ResponseEntity(rs.findAll(), HttpStatus.CREATED);
     }


}
