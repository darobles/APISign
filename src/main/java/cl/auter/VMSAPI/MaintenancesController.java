package cl.auter.VMSAPI;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
	
	private final MaintenancesRepository recRepository;
	
	@Autowired
	public MaintenancesController(MaintenancesRepository recRepository) {
		this.recRepository = recRepository;
	}
	
	@GetMapping("")
	public List<MaintenancesEntity> findAll(){
		return recRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@GetMapping("/{id}")
    public MaintenancesEntity findMantById(@PathVariable(value = "id") int id) {
		try {
			return recRepository.findById(id)
				      .orElseThrow(() -> null);
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
    }
	
	@PostMapping("")
	public ResponseEntity<JsonObject> saveNewMaintenance(@RequestBody MaintenancesEntity json) {
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		try {
			recRepository.save(json);
			jsn.add("result", "success");
		}
		catch(Exception e) {
			System.out.println(e);
			jsn.add("result", "error");
			jsn.add("detail", e.toString());
		}
		
		return ResponseEntity.ok(jsn.build());
    }
	
	@PutMapping("")
	public ResponseEntity<JsonObject> editMaintenance(@RequestBody MaintenancesEntity json) {
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		try {
			recRepository.save(json);
			jsn.add("result", "success");
		}
		catch(Exception e) {
			System.out.println(e);
			jsn.add("result", "error");
			jsn.add("detail", e.toString());
		}
		
		return ResponseEntity.ok(jsn.build());
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<JsonObject> deleteMaintenance(@PathVariable(value = "id") int id) {
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		try {
			recRepository.deleteById(id);
			jsn.add("result", "success");
		}
		catch(Exception e) {
			System.out.println(e);
			jsn.add("result", "error");
			jsn.add("detail", e.toString());
		}
		
		return ResponseEntity.ok(jsn.build());
    }

}
