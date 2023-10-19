package cl.auter.VMSAPI;

import java.util.List;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
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
		return recRepository.findAll();
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

}
