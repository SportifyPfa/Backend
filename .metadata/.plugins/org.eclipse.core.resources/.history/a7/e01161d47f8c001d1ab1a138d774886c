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
}
