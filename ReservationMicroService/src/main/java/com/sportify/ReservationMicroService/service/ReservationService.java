package com.sportify.ReservationMicroService.service;

import com.sportify.ReservationMicroService.entity.AppUser;
import com.sportify.ReservationMicroService.security.AccessTokenGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.entity.Terrain;
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



    public Reservation saveReservation(Reservation res, Long id) {
    	AppUser user = jclient.profile(AccessTokenGetter.getBearerTokenHeader());
		 Terrain terrain = tClient.findTerrainById(id, AccessTokenGetter.getBearerTokenHeader());
			res.setUser(user);
			res.setUserId(user.getId());
			res.setTerrainId(id);
			res.setTerrain(terrain);
			res.
        return orderRepository.save(res);
    }

    
    public Reservation findById(int id) {
        return orderRepository.findById(id);
    }
    
    public Reservation findReservationUser(int id) {
    	 	Reservation r = this.findById(id);
			AppUser user = jclient.profile(AccessTokenGetter.getBearerTokenHeader());
			Terrain terrain = tClient.findTerrainById(r.getTerrainId(),AccessTokenGetter.getBearerTokenHeader());
			r.setTerrain(terrain);
			r.setUser(user);
        return r;
    }

}
