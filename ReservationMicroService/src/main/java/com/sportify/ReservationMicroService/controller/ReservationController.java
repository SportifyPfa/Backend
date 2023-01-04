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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.entity.Terrain;
import com.sportify.ReservationMicroService.feignclient.JoueurClient;
import com.sportify.ReservationMicroService.feignclient.TerrainClient;
import com.sportify.ReservationMicroService.repository.ReservationRepository.countMois;
import com.sportify.ReservationMicroService.service.ReservationService;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class ReservationController {
	
	@Autowired 
	JoueurClient jclient;
	
	@Autowired 
	ReservationService rs;
	
	@Autowired
	TerrainClient tClient;
	
	@PostMapping(value = "/{id}/{seanceId}")
    public ResponseEntity<Reservation> saveReservation(@PathVariable(name="id") Long id, @PathVariable(name="seanceId") Long seanceId){
		    return new ResponseEntity(rs.saveReservation(seanceId,id), HttpStatus.CREATED);
		}
	
	
	 @GetMapping("/orders")
     public ResponseEntity<List<Reservation>> getUSerReservation() {
		 return new ResponseEntity(rs.findReservationUser(), HttpStatus.CREATED);
     }
	 
	 @GetMapping("/mois")
     public ResponseEntity<List<countMois>> nbreTerrainReserveParMois() {
		 return new ResponseEntity(rs.nbreTerrainReserveParMois(), HttpStatus.CREATED);
     }
	 
	 @GetMapping("/nonReserve")
     public ResponseEntity<List<Terrain>> terrainNonReserve() {
		 return new ResponseEntity(rs.findTerrainNonReserve(), HttpStatus.CREATED);
     }

	 @GetMapping("/NonComplet")
     public ResponseEntity<List<Terrain>> terrainNonComplet() {
		 return new ResponseEntity(rs.findTerrainNonComplet(), HttpStatus.CREATED);
     }
	 @GetMapping("/{entity}")
	 public ResponseEntity<List<Terrain>> findTerrainByEntityDate(@PathVariable(name="entity") String entity,@RequestPart Date d){
		 return new ResponseEntity(rs.findTerrainByEntityDate(entity,d), HttpStatus.CREATED);
		 
	 }


}
