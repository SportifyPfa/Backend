package com.sportify.ReservationMicroService.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
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
	 @Column (name = "date_From")

	 private Date dateFrom;
	 
	 @Column (name = "date_To")

	 private Date dateTo;
	 
@Transient
    private User user;

private int userId;

@Transient

private Terrain terrain;

private int terrainId;
	 


	public Terrain getTerrain() {
	return terrain;
}

public void setTerrain(Terrain terrain) {
	this.terrain = terrain;
}

	public int getTerrainId() {
	return terrainId;
}

public void setTerrainId(int terrainId) {
	this.terrainId = terrainId;
}

	public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	

}
