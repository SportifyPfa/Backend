package com.sportify.ReservationMicroService.entity;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
public class Terrain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	private String disponibility_from;
	private String disponibility_to;
	private int price;
	@Column(name = "imgfilename")
	private String imgFileName;
	private String location;
	private String description;
	private String entity;
	@OneToMany
	private List<Seance> seances;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDisponibility_from() {
		return disponibility_from;
	}
	public void setDisponibility_from(String disponibility_from) {
		this.disponibility_from = disponibility_from;
	}
	public String getDisponibility_to() {
		return disponibility_to;
	}
	public void setDisponibility_to(String disponibility_to) {
		this.disponibility_to = disponibility_to;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public List<Seance> getSeances() {
		return seances;
	}
	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}
	
	
	
}
