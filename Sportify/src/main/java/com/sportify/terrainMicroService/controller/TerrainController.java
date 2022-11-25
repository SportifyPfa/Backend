package com.sportify.terrainMicroService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sportify.terrainMicroService.dao.TerrainDao;
import com.sportify.terrainMicroService.entity.Terrain;

@RestController
@RequestMapping("terrain")
public class TerrainController {
	
	@Autowired
	private TerrainDao terdao;
	
	@PostMapping("/save")
	public void save(@RequestBody Terrain t){
		terdao.save(t);
	}

	@GetMapping("/all")
	public List<Terrain> findAll(){
		return terdao.findAll();
	}
	@GetMapping(value = "/{id}")
	public Terrain findById(@PathVariable int id) {
		return terdao.findById(id);
	}
	
	@GetMapping(value = "/{nom}")
	public Terrain findById(@PathVariable String nom) {
		return terdao.findByNom(nom);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable(required = true) int id) {
		System.out.println("id = "+id);
		terdao.deleteById(id);
	}
	
	@PostMapping("/{id}")
	public void update(@PathVariable int id,@RequestBody Terrain t) {
		
		System.out.println(id);
		Terrain t1 = terdao.findById(id);
		t1.setDisponibilite(t.isDisponibilite());
		t1.setLocation(t.getLocation());
		t1.setNom(t.getNom());
		t1.setPrix(t.getPrix());
		terdao.save(t1);
		
	}
	
}
