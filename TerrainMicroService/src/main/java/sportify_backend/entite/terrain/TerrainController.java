package sportify_backend.entite.terrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/terrain")
public class TerrainController {

	private TerrainService terrainService;
	@Autowired
	private SeanceService ss;

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
	public ResponseEntity<Terrain> updateTerrain(@RequestPart Terrain terrain,@RequestPart(required = false) MultipartFile img,@PathVariable(name="id") long id) {
		return ResponseEntity.accepted().body(terrainService.updateTerrain(terrain,img,id));
	}
	@GetMapping("/seances/{id}")
	public ResponseEntity<List<Seance>> getSeance(@PathVariable(required = true,name = "id") Long id) {
		return ResponseEntity.accepted().body(terrainService.findTerrainById(id).getSeances());
	}
	@GetMapping("/seance/{id}")
	public  ResponseEntity<Seance> findSeanceById(@PathVariable(name = "id") long id) {
		return ResponseEntity.accepted().body(ss.findById(id));
	}
	
	@GetMapping("/entity/{name}")
	public ResponseEntity<List<Terrain>> getEntityTerrain(@PathVariable(required = true) String name) {
		return ResponseEntity.accepted().body(terrainService.findTerrainByEntity(name));
	}
	
	@PostMapping("/updateS/{id}")
	public ResponseEntity<Seance> updateSeance(@RequestPart Seance s,@PathVariable(name="id") long id) {
		return ResponseEntity.accepted().body(ss.update(s,id));
	}
	@GetMapping("/mois")
	public ResponseEntity<List<Integer>> getMois() {
		return ResponseEntity.accepted().body(ss.getMois());
	}
	@GetMapping("/seanceDate")	
	public ResponseEntity<List<Seance>> findSeanceByDate(@RequestPart Date d){
		return ResponseEntity.accepted().body(ss.findSeanceByDate(d));

	}
	
	@GetMapping("/entityDate/{name}")
	public ResponseEntity<List<Terrain>> findTerrainByEntityDate(@PathVariable(required = true,name="name") String name, @RequestPart Date d){
		return ResponseEntity.accepted().body(terrainService.findTerrainByEntityDate(name,d));

		
	}
}
