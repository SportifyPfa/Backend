package com.sportify.ReservationMicroService.controller;

import com.sportify.ReservationMicroService.security.AccessTokenGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.feignclient.JoueurClient;
import com.sportify.ReservationMicroService.feignclient.TerrainClient;
import com.sportify.ReservationMicroService.service.ReservationService;

import java.security.Principal;

@RestController
@RequestMapping("/order")
public class ReservationController {
	
	@Autowired 
	JoueurClient jclient;
	
	@Autowired 
	ReservationService rs;
	
	@Autowired
	TerrainClient tClient;
	
	@PostMapping(value = "/{id}")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation res, @PathVariable Long id){
		    return new ResponseEntity(rs.saveReservation(res,id), HttpStatus.CREATED);
		}
	
	
	 @GetMapping("/{id}")
     public ResponseEntity<Reservation> getUSerReservation(@PathVariable int id) {
		 return new ResponseEntity(rs.findReservationUser(id), HttpStatus.CREATED);
     }


}
