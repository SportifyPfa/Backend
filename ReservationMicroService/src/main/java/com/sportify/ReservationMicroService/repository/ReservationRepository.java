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
	@Query(value="select * from reservation r where r.terrain_id = ?1",nativeQuery = true)
	public List<Reservation>  findByTerrain(long id);
	@Query(value="select * from reservation r, reservation_joueurs rj where r.id = rj.reservation_id and rj.joueurs like ?1",nativeQuery = true)
	public List<Reservation> findReservationUser(String id);
	//@Query(value="select s.mois,count(t) from reservation r,seance s,terrain t where r.terrain_id = t.id and  s.id = r.seance_id group by s.mois",nativeQuery = true)
	//public List<countMois> nbreTerrainReserveParMois(List<Long> name);
	
	public interface countMois{
		int getMois();
	
		int getCount();
	}
	@Query("select r.terrainId from Reservation r group by r.terrainId")
public List<Long> findTerrains();
}
