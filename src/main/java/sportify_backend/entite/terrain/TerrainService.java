package sportify_backend.entite.terrain;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sportify_backend.entite.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;


@Service
public class TerrainService {

    private final TerrainRepository terrainRepository;

    public TerrainService(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }


    public List<Terrain> findAllTerrain() {
        return terrainRepository.findAll();
    }

    public Terrain findTerrainById(Long id){
        return terrainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Terrain Not Found with this ID: " + id));
    }

    public Terrain saveTerrain(Terrain terrain){
        return terrainRepository.save(terrain);
    }

    public Terrain updateTerrain(Terrain terrain){
        terrainRepository.findById(terrain.getId()).orElseThrow(() -> new ResourceNotFoundException("Terrain Not Found with this ID: " + terrain.getId()));
        return terrainRepository.save(terrain);
    }

    public void deleteTerrain(Long id) {
        terrainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Terrain Not Found with this ID: " + id));
        terrainRepository.deleteById(id);
    }

}