package com.sportify.ReservationMicroService.service;

import com.sportify.ReservationMicroService.entity.AppUser;
import com.sportify.ReservationMicroService.security.AccessTokenGetter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.entity.Seance;
import com.sportify.ReservationMicroService.entity.Terrain;
import com.sportify.ReservationMicroService.feignclient.JoueurClient;
import com.sportify.ReservationMicroService.feignclient.TerrainClient;
import com.sportify.ReservationMicroService.repository.ReservationRepository;
import com.sportify.ReservationMicroService.repository.ReservationRepository.countMois;



@Service
@Transactional
public class ReservationService {
	@Autowired
    private ReservationRepository orderRepository;
	@PersistenceContext
	EntityManager em;
	@Autowired
	TerrainClient tClient;
	@Autowired 
	JoueurClient jclient;


@PermitAll
    public Reservation saveReservation(Long seanceId, Long id) {
    	AppUser user = jclient.profile(AccessTokenGetter.getBearerTokenHeader());
		 Terrain terrain = tClient.findTerrainById(id, AccessTokenGetter.getBearerTokenHeader());
		 System.out.println("hguo"+terrain.getName());
		
		 //orderRepository.saveTerrain(terrain.getId(),terrain.getName(),terrain.getPrice(),terrain.getEntity());
		 Reservation res1 = findByTerrainByDate(seanceId,id);	
		 if(res1 == null) {
			 Query q = em.createNativeQuery("insert into terrain(id,name,price,entity) values(:id,:name,:price,:entity)",Terrain.class);
			 q.setParameter("name", terrain.getName());
			 q.setParameter("id", terrain.getId());
			 q.setParameter("price", terrain.getPrice());
			 q.setParameter("entity", terrain.getEntity());
	q.executeUpdate();
	  		 Reservation res = new Reservation();
	  			res.setUser(user);
	  			res.setUserId(user.getId());
	  			res.setTerrainId(id);
	  			res.setTerrain(terrain);
	  			Seance s = tClient.findSeanceById(seanceId,AccessTokenGetter.getBearerTokenHeader());
	  			s.setNbreParticipant(1);
	  			res.setSeance(s);
	  			//tClient.updateSeance(s,s.getId(), AccessTokenGetter.getBearerTokenHeader());
	  			res.setSeanceId(seanceId);
	  			 Query qq = em.createNativeQuery("insert into seance(id,annee,disponibilite,heure_debut,heure_fin,jour,mois,nbre_participant) values(:id,:annee,:disponibilite,:heureDebut,:heureFin,:jour,:mois,:nbreParticipant)",Seance.class);
				 qq.setParameter("annee", s.getAnnee());
				 qq.setParameter("id", s.getId());
				 qq.setParameter("disponibilite", s.getDisponibilite());
				 qq.setParameter("heureDebut", s.getHeureDebut());
				 qq.setParameter("heureFin", s.getHeureFin());
				 qq.setParameter("jour", s.getJour());
				 qq.setParameter("mois", s.getMois());
				 qq.setParameter("nbreParticipant", 1);
				 qq.executeUpdate();
	  			
	  			List<String> us = res.getJoueurs();
	  			if(us == null) {
	  				us = new ArrayList<String>() ;
	  			}
	  			us.add(user.getUsername());
	  			res.setJoueurs(us);
	  			
	          return orderRepository.save(res);
	   	}
	   	List<String> us = res1.getJoueurs();
			us.add(user.getUsername());
			
			res1.setJoueurs(us);
			res1.setUser(user);
			res1.setUserId(user.getId());
			Seance s = tClient.findSeanceById(seanceId,AccessTokenGetter.getBearerTokenHeader());
			System.out.println("hhhhhhh"+s.getNbreParticipant());
			s.setNbreParticipant(s.getNbreParticipant()+1);
			if(us.size() == 10) {
				s.setDisponibilite(false);
			}
			res1.setSeance(s);
			res1.setTerrainId(id);
  			res1.setTerrain(terrain);
			//tClient.updateSeance(s,s.getId(), AccessTokenGetter.getBearerTokenHeader());
			Query qq = em.createNativeQuery("update seance set annee= :annee,disponibilite= :disponibilite,heure_debut = :heureDebut,heure_fin = :heureFin,jour = :jour,mois = :mois,nbre_participant = :nbreParticipant where id =:id",Seance.class);
			 qq.setParameter("annee", s.getAnnee());
			 qq.setParameter("id", s.getId());
			 if(us.size() == 10) {
				 qq.setParameter("disponibilite", false);
				}
			 else {
				 qq.setParameter("disponibilite", true);
			 }
			 qq.setParameter("heureDebut", s.getHeureDebut());
			 qq.setParameter("heureFin", s.getHeureFin());
			 qq.setParameter("jour", s.getJour());
			 qq.setParameter("mois", s.getMois());
			 qq.setParameter("nbreParticipant", s.getNbreParticipant()+1);
			 qq.executeUpdate();
			//orderRepository.saveSeance(s.getId(),s.getAnnee(),s.getDisponibilite(),s.getHeureDebut(),s.getHeureFin(),s.getJour(),s.getMois(),s.getNbreParticipant());
			return orderRepository.save(res1);
    }

    
    public List<Reservation> findReservationUser() {
    	AppUser user = jclient.profile(AccessTokenGetter.getBearerTokenHeader());			
        return orderRepository.findReservationUser(user.getUsername());
    }
    
    public List<Terrain> findTerrainNonReserve() {
    	return orderRepository.findTerrainNonReserve();
    }
    
    public List<countMois> nbreTerrainReserveParMois() {
    	AppUser user = jclient.profile(AccessTokenGetter.getBearerTokenHeader());
    	List<Terrain> terrain = tClient.getEntityTerrain(user.getUsername(), AccessTokenGetter.getBearerTokenHeader());
    	List<Long> t = new ArrayList<Long>();
    	for(Terrain tt: terrain) {
    		t.add(tt.getId());
    	}
    
    	return orderRepository.nbreTerrainReserveParMois(t);
    }


	public List<Terrain> findTerrainNonComplet() {
		// TODO Auto-generated method stub
		return orderRepository.findTerrainNonComplet();
	}
	public List<Terrain> findTerrainByEntityDate(String name, Date d){
	
		return orderRepository.findTerrainByEntityDate(name,  d.getDay(), d.getMonth(),  d.getYear());
	}
	
	 public Reservation findByTerrainByDate(Long seanceId, Long id) {
	    			
	        return orderRepository.findByTerrainByDate(id,seanceId);
	    }
}
