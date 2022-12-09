package sportify_backend.entite.terrain;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sportify_backend.entite.exception.ResourceNotFoundException;
import sportify_backend.entite.image.ImageService;

import java.util.List;


@Service
public class TerrainService {

    private final TerrainRepository terrainRepository;
    private final ImageService imageService;

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

    public Terrain saveTerrain(Terrain terrain,MultipartFile img) {
        if (img != null){
            String imgName =img.getOriginalFilename();
            terrain.setImgFileName(imgName);
            imageService.storeImg(img,imgName);
        }
        return terrainRepository.save(terrain);
    }

    public Terrain updateTerrain(Terrain terrain, MultipartFile img ){
        terrainRepository.findById(terrain.getId()).orElseThrow(() -> new ResourceNotFoundException("Terrain Not Found with this ID: " + terrain.getId()));
        if(img != null) {
            String imgName = img.getOriginalFilename();
            terrain.setImgFileName(imgName);
            imageService.storeImg(img,imgName);
        }
        return terrainRepository.save(terrain);
    }

    public void deleteTerrain(Long id) {
        terrainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Terrain Not Found with this ID: " + id));
        terrainRepository.deleteById(id);
    }

}