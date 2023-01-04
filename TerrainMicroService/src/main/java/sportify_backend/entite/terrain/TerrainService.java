package sportify_backend.entite.terrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sportify_backend.entite.exception.ResourceNotFoundException;
import sportify_backend.entite.feignClient.JoueurClient;
import sportify_backend.entite.image.ImageService;
import sportify_backend.entite.security.AccessTokenGetter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TerrainService {

    private final TerrainRepository terrainRepository;
    private final ImageService imageService;
    @Autowired
    private JoueurClient jc;
    @Autowired
    private SeanceService ss;

    public TerrainService(TerrainRepository terrainRepository, ImageService imageService) {
        this.terrainRepository = terrainRepository;
        this.imageService = imageService;
    }


    public List<Terrain> findAllTerrain() {
        return terrainRepository.findAll();
    }

    public Terrain findTerrainById(Long id){
        return terrainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Terrain Not Found with this ID: " + id));
    }
    
private List<Seance> calculSeance(Date ouverture,Date Fermeture){
    	
    	int ouvSec = ouverture.getSeconds()+ ouverture.getMinutes()*60 + ouverture.getHours()*60*60;
    	int ferSec = Fermeture.getSeconds()+ Fermeture.getMinutes()*60 + Fermeture.getHours()*60*60;
    	int duree = (ferSec-ouvSec)/(60*60);
    	List<Seance> s = new ArrayList<Seance>();
    	
    	Date debut = (Date) ouverture.clone();
    	Date fin = (Date) debut.clone();
    /*	fin.setDate(debut.getDate());
		fin.setMinutes(debut.getMinutes());
		fin.setMonth(debut.getMonth());
		fin.setSeconds(debut.getSeconds());
		fin.setTime(debut.getTime());
		fin.setYear(debut.getYear());
		*/
    	for(int i =0;i<duree;i++) {
    
    		fin.setHours(fin.getHours()+1);
    	
    		Seance se = new Seance(debut,(Date) fin.clone());
    	
    		ss.add(se);
    		s.add(se);
    		debut = (Date) fin.clone();
    		
    		/*
    		debut.setDate(fin.getDate());
    		debut.setMinutes(fin.getMinutes());
    		debut.setMonth(fin.getMonth());
    		debut.setSeconds(fin.getSeconds());
    		debut.setTime(fin.getTime());
    		debut.setYear(fin.getYear());
    		debut.setHours(fin.getHours());*/
    		
    	}
    	
    	return s;
    }

    public Terrain saveTerrain(Terrain terrain,MultipartFile img) {
    	AppUser user = jc.profile(AccessTokenGetter.getBearerTokenHeader());
    	List<Seance> s = this.calculSeance(terrain.getDisponibility_from(), terrain.getDisponibility_to());
    	System.out.println(terrain.getId());
    	terrain.setSeances(s);
    	terrain.setEntity(user.getUsername());
    	
    	
        if (img != null){
            String imgName =img.getOriginalFilename();
            terrain.setImgFileName(imgName);
            
            imageService.storeImg(img,imgName);
        }
        System.out.println("hhhhhh"+terrain.getDisponibility_from());
        return terrainRepository.save(terrain);
    }

    public Terrain updateTerrain(Terrain terrain, MultipartFile img,long id ){
        Terrain t = terrainRepository.findById(id);
        t.setDescription(terrain.getDescription());
        t.setDisponibility_from(terrain.getDisponibility_from());
        t.setDisponibility_to(terrain.getDisponibility_to());
        t.setEntity(terrain.getEntity());
        t.setLocation(terrain.getLocation());
        t.setName(terrain.getName());
   
        t.setPrice(terrain.getPrice());
        t.setSeances(t.getSeances());
        if(img != null) {
            String imgName = img.getOriginalFilename();
            terrain.setImgFileName(imgName);
            imageService.storeImg(img,imgName);
        }
        return terrainRepository.save(t);
    }

    public void deleteTerrain(Long id) {
        terrainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Terrain Not Found with this ID: " + id));
        terrainRepository.deleteById(id);
    }
    
    public List<Terrain> findTerrainByEntity(String name) {
		// TODO Auto-generated method stub
		
		return terrainRepository.findByEntity(name);
	}
    

	public Seance findSeanceById(long id) {
		// TODO Auto-generated method stub
		
		return terrainRepository.findSeanceById( id);
	}

}