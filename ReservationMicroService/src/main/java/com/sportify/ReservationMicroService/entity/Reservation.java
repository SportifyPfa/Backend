package com.sportify.ReservationMicroService.entity;

import java.util.Date;

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
	 @Column (name = "date_From")

	 private Date dateFrom;
	 
	 @Column (name = "date_To")

	 private Date dateTo;
	 
@Transient
    private AppUser user;

private Long userId;

@Transient
private Terrain terrain;

private Long terrainId;
	 


	
	

}
