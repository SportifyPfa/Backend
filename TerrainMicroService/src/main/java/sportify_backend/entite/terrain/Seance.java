package sportify_backend.entite.terrain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int jour;
	private int mois;
	private int annee;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date heureDebut;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date heureFin;
	private Boolean disponibilite;
	private int nbreParticipant;
	

	public Seance() {
		super();
	}
	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}


	
	public Seance(Date dateDebut, Date dateFin) {
		super();
		this.heureDebut = dateDebut;
		this.heureFin = dateFin;
		System.out.println(this.heureDebut.getDate());
		this.jour = this.heureDebut.getDate();
		this.mois = this.heureDebut.getMonth()+1;
		this.annee = this.heureDebut.getYear()+1900;
		this.disponibilite = true;
		this.nbreParticipant = 0;
	
	}

	public Boolean getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}
	public int getNbreParticipant() {
		return nbreParticipant;
	}
	public void setNbreParticipant(int nbreParticipant) {
		this.nbreParticipant = nbreParticipant;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	

}