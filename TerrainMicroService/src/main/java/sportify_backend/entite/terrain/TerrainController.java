package sportify_backend.entite.terrain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/terrain")
public class TerrainController {

	private TerrainService terrainService;

	public TerrainController(TerrainService terrainService) {
		this.terrainService = terrainService;
	}


	@PostMapping("/save")
	public ResponseEntity<Terrain> saveTerrain(@RequestPart Terrain terrain,@RequestPart(required = false)MultipartFile img) {
		return new ResponseEntity(terrainService.saveTerrain(terrain,img), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Terrain>> findAllTerrain(){
		return new ResponseEntity(terrainService.findAllTerrain(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Terrain>  findTerrainById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity(terrainService.findTerrainById(id),HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteTerrain(@PathVariable(required = true) Long id) {
		terrainService.deleteTerrain(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Terrain> updateTerrain(@RequestPart Terrain terrain,@RequestPart(required = false) MultipartFile img) {
		return ResponseEntity.accepted().body(terrainService.updateTerrain(terrain,img));
	}
	
}