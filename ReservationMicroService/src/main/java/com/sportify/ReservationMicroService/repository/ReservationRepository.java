package com.sportify.ReservationMicroService.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.entity.Seance;
import com.sportify.ReservationMicroService.entity.Terrain;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	public Reservation findById(int id);
	@Query(value="select * from reservation r where r.terrain_id = ?1 and r.seance_id = ?2",nativeQuery = true)
	public Reservation  findByTerrainByDate(long id, long seanceId);
	@Query(value="select * from reservation r, reservation_joueurs rj where r.id = rj.reservation_id and rj.joueurs like ?1",nativeQuery = true)
	public List<Reservation> findReservationUser(String id);
	@Query(value="select s.mois,count(t) from reservation r,seance s,terrain t where r.terrain_id = t.id and  s.id = r.seance_id group by s.mois",nativeQuery = true)
	public List<countMois> nbreTerrainReserveParMois(List<Long> name);
	@Query("select t from Reservation r right join Terrain t on r.terrainId = t.id where r.id is null")
	public List<Terrain> findTerrainNonReserve();
	@Query("select t from Reservation r,Terrain t,Seance s where r.terrainId = t.id and r.seanceId = s.id and t.entity like ?1 and s.disponibilite = true and  s.jour = ?2 and s.mois = ?3 and s.annee = ?4")
	List<Terrain> findTerrainByEntityDate(String name, int jour,int mois, int annee);
	public interface countMois{
		int getMois();
	
		int getCount();
	}
	@Query("select t from Reservation r,Terrain t, Seance s where r.terrainId = t.id and r.seanceId = s.id and s.disponibilite = true")
	public List<Terrain> findTerrainNonComplet();
	@Query(value="insert into terrain(id,name,price,entity) values(?1,?2,?3,?4);commit;",nativeQuery = true)
	public void saveTerrain(long id,String name,int price,String entity);
	@Query(value="insert into seance(id,annee,disponibilite,heureDebut,heureFin,jour,mois,nbreParticipant) values(?1,?2,?3,?4,?5,?6,?7,?8);commit;",nativeQuery = true)
	public Seance saveSeance(Long id, int annee, Boolean disponibilite, Date heureDebut, Date heureFin, int jour,
			int mois, int nbreParticipant);

}
