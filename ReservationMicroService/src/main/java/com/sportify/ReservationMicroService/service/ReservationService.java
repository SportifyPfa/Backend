package com.sportify.ReservationMicroService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.entity.Terrain;
import com.sportify.ReservationMicroService.entity.User;
import com.sportify.ReservationMicroService.feignclient.JoueurClient;
import com.sportify.ReservationMicroService.feignclient.TerrainClient;
import com.sportify.ReservationMicroService.repository.ReservationRepository;



@Service
@Transactional
public class ReservationService {
	@Autowired
    private ReservationRepository orderRepository;
	@Autowired
	TerrainClient tClient;
	@Autowired 
	JoueurClient jclient;


    public Reservation saveReservation(Reservation res,int userId, int id) {
    	User user = jclient.getUserById(userId);  
		Terrain terrain = tClient.findTerrainById(id); 

			System.out.println(terrain.getId());
		
			res.setUser(user);	 
			res.setUserId(userId);
			res.setTerrainId(id);
			res.setTerrain(terrain);
			res.getTerrain();
			   
        return orderRepository.save(res);
    }
    
    public List<Reservation> findAll() {
        return orderRepository.findAll();
    }
    
    public Reservation findById(int id) {
        return orderRepository.findById(id);
    }
    
    public Reservation findReservationUser(int id) {
    	 Reservation r = this.findById(id);
		 User user = jclient.getUserById(r.getUserId());  
			Terrain terrain = tClient.findTerrainById(r.getTerrainId());
			r.setTerrain(terrain);
			r.setUser(user);
        return r;
    }

}
