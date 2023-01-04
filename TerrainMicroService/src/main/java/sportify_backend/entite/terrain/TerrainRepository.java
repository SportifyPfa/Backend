package sportify_backend.entite.terrain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain,Long> {
	List<Terrain> findByEntity(String entity);
	Terrain findById(long id);
	
	
	@Query("select s from Seance s where s.id like ?1")
	Seance findSeanceById(Long id);
	@Query(nativeQuery = true, value="select t.* from terrain t, seance s, terrain_seances ts where t.id = ts.terrain_id and s.id = ts.seances_id and t.entity = ?1 and s.jour = ?2 and s.mois=?3 and s.annee=?4 group by t.id")
	List<Terrain> findTerrainByEntityDate(String name, int d, int m, int a);
}
