package com.sportify.UserMicroService.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
	
	  @Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	  @Column (name = "role_id")
	    private int id;

	    @Column (name = "role_name")
	    private String roleName;

	    @OneToMany (mappedBy = "role")
	    @JsonIgnore
	    private List<User> users;

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	    

}
