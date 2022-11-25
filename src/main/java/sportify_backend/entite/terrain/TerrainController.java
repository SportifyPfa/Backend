package sportify_backend.entite.terrain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terrain")
public class TerrainController {

	private TerrainService terrainService;

	public TerrainController(TerrainService terrainService) {
		this.terrainService = terrainService;
	}


	@PostMapping("/save")
	public ResponseEntity<Terrain> saveTerrain(@RequestBody Terrain t) {
		return new ResponseEntity(terrainService.saveTerrain(t), HttpStatus.CREATED);
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
	public ResponseEntity<Terrain> updateTerrain(@RequestBody Terrain terrain) {
		return ResponseEntity.accepted().body(terrainService.updateTerrain(terrain));
	}
	
}
