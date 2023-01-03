package com.sportify.ReservationMicroService.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sportify.ReservationMicroService.entity.Terrain;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SPORTIFYENTITY")
public interface TerrainClient {
	@GetMapping(value = "/terrain/{id}")
	    public Terrain findTerrainById(@PathVariable(name = "id") Long id,@RequestHeader("Authorization") String bearerToken);

}