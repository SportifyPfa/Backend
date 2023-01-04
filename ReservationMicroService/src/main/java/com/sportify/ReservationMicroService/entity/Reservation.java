package com.sportify.ReservationMicroService.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
	@Transient
	private Seance seance;
	private Long seanceId;
	 
@Transient
    private AppUser user;

private Long userId;

@Transient
private Terrain terrain;

private Long terrainId;
@ElementCollection
private List<String> joueurs;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Seance getSeance() {
	return seance;
}
public void setSeance(Seance seance) {
	this.seance = seance;
}
public Long getSeanceId() {
	return seanceId;
}
public void setSeanceId(Long seanceId) {
	this.seanceId = seanceId;
}
public AppUser getUser() {
	return user;
}
public void setUser(AppUser user) {
	this.user = user;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public Terrain getTerrain() {
	return terrain;
}
public void setTerrain(Terrain terrain) {
	this.terrain = terrain;
}
public Long getTerrainId() {
	return terrainId;
}
public void setTerrainId(Long terrainId) {
	this.terrainId = terrainId;
}
public List<String> getJoueurs() {
	return joueurs;
}
public void setJoueurs(List<String> joueurs) {
	this.joueurs = joueurs;
} 


	
	

}
