package sportify_backend.entite.terrain;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeanceService {
	@Autowired
	private SeanceRepository sr;
	
	public Seance add(Seance s) {
		Seance ss= sr.save(s);
		
		
		return ss;
	}

	public List<Seance> getSeance(Terrain terrain) {
		// TODO Auto-generated method stub
		return sr.getSeance(terrain);
	}
	public Seance findById(long id) {
		// TODO Auto-generated method stub
		return sr.findById(id);
	}
	public Seance update(Seance s,long id) {
		// TODO Auto-generated method stub
		Seance ss = findById(id);
		ss.setAnnee(s.getAnnee());
		ss.setDisponibilite(s.getDisponibilite());
		ss.setHeureDebut(s.getHeureDebut());
		ss.setHeureFin(s.getHeureFin());
		ss.setJour(s.getJour());
		ss.setMois(s.getMois());
		ss.setNbreParticipant(s.getNbreParticipant());
		
		return sr.save(ss);
	}
	public List<Integer> getMois(){
		return sr.getMois();
	}

	public List<Seance> findSeanceByDate(Date d) {
		// TODO Auto-generated method stub
		
		return sr.findSeanceByDate(d.getDate(),d.getMonth()+1,d.getYear()+1900);
	}
}
