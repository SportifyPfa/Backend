package com.sportify.ReservationMicroService.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name="utilisateur")
public class User {
	 @Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column (name = "user_name")
	    @NotNull
	    private String userName;

	    @OneToMany (mappedBy = "user")
	    @JsonIgnore
	    private List<Reservation> reservations;

}
