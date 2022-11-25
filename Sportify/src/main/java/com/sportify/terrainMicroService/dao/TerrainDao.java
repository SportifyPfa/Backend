package com.sportify.terrainMicroService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.sportify.terrainMicroService.entity.Terrain;

@Repository
public interface TerrainDao extends JpaRepository<Terrain,Integer> {
	 
	Terrain findById(int id);
	Terrain findByNom(String nom);
		
}
