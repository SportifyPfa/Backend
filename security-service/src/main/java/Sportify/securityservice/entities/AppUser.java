package Sportify.securityservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username",unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "age")
    private String age;
    @Column(name = "gendre")
    private String gendre;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> appRoles=new ArrayList<>();
    
    public AppUser(String username, String password, String age,String gender, List<AppRole> appRoles) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.gendre = gender;
		this.appRoles = appRoles;
	}
    




    
}
