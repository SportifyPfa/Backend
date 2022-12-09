package com.sportify.ReservationMicroService.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sportify.ReservationMicroService.entity.Terrain;

@FeignClient(name = "Backend", url = "http://localhost:8080/")
public interface TerrainClient {
	
	 @GetMapping(value = "/terrain/{id}")
	    public Terrain getTerrainById(@PathVariable(value = "id") int terrainId);

}
