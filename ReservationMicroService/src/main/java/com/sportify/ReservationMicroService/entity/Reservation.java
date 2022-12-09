package com.sportify.ReservationMicroService.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
	 @Column (name = "date_From")
	 @NotNull
	 private Date dateFrom;
	 
	 @Column (name = "date_To")
	 @NotNull
	 private Date dateTo;
	 
	@ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;
	 
	@ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "terrain_id")
    private Terrain terrain;

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

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	

}
