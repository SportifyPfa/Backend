package com.sportify.ReservationMicroService.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.sportify.ReservationMicroService.entity.Seance;
import com.sportify.ReservationMicroService.entity.Terrain;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "SPORTIFYENTITY")
public interface TerrainClient {
	@GetMapping(value = "/terrain/{id}")
	    public Terrain findTerrainById(@PathVariable(name = "id") Long id,@RequestHeader("Authorization") String bearerToken);
	@GetMapping("/terrain/seance/{id}")
	public  Seance findSeanceById(@PathVariable(name = "id") long id, @RequestHeader("Authorization") String bearerToken);
	@PostMapping("/terrain/updateS/{id}")
	public Seance updateSeance(@RequestPart Seance s,@PathVariable(name="id") long id, @RequestHeader("Authorization") String bearerToken) ;
	
	@GetMapping("/terrain/entity/{name}")
	public List<Terrain> getEntityTerrain(@PathVariable(required = true,name="name") String name, @RequestHeader("Authorization") String bearerToken);
	@GetMapping("/terrain/mois")
	public List<Integer> getMois( @RequestHeader("Authorization") String bearerToken);
}
