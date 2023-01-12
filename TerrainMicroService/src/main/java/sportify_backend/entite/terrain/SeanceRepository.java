package sportify_backend.entite.terrain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SeanceRepository extends JpaRepository<Seance,Long> {
	@Query("select s from Seance s where terrain like ?1")
	List<Seance> getSeance(Terrain terrain);
	
	public Seance findById(long id);
@Query("select s.mois from Seance s group by s.mois")
	List<Integer> getMois();
@Query("select s from Seance s where s.jour = ?1 and s.mois=?2 and s.annee = ?3")
List<Seance> findSeanceByDate(int jour, int mois, int annee);

}