package com.sportify.terrainMicroService.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Terrain {
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	private String location;
	private double prix;
	private int nbrMaxJoueur;
	private boolean disponibilite;
	
	public Terrain(String nom, String location, double prix, boolean disponibilite, int nbrMaxJoueur) {
		super();
		this.nom = nom;
		this.location = location;
		this.prix = prix;
		this.nbrMaxJoueur = nbrMaxJoueur;
		this.disponibilite = disponibilite;
	}
	

	public Terrain() {
		super();
	}


	public int getNbrMaxJoueur() {
		return nbrMaxJoueur;
	}


	public void setNbrMaxJoueur(int nbrMaxJoueur) {
		this.nbrMaxJoueur = nbrMaxJoueur;
	}


	public int getId() {
		
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public boolean isDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	@Override
	public String toString() {
		return "Terrain [id=" + id + ", nom=" + nom + ", location=" + location + ", prix=" + prix + ", disponibilite="
				+ disponibilite + "]";
	}
	
	

}
