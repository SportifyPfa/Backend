package com.sportify.ReservationMicroService.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="utilisateur")
@Data
public class User {
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "user_name", nullable = false, unique = true, length = 50)
    private String userName;
    @Column (name = "user_password", nullable = false, length = 50)
    private String userPassword;
    @ManyToOne
    private UserRole role;
    

	public void setRole(UserRole role2) {
		// TODO Auto-generated method stub
		this.role = role2;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserRole getRole() {
		return role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
