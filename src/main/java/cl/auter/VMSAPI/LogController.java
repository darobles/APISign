package cl.auter.VMSAPI;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/log")
public class LogController {
	
	private final LogRepository logRepository;
	
	@Autowired
	public LogController(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	@GetMapping("")
	public List<LogEntity> findAll(){
		return logRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@PostMapping("")
	public ResponseEntity<JsonObject> newLogRegister(@RequestBody LogEntity json) {
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		try {
			logRepository.save(json);
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
