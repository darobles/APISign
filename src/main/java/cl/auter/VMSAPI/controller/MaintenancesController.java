package cl.auter.VMSAPI.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.MaintenancesEntity;
import cl.auter.VMSAPI.repository.MaintenancesRepository;
import cl.auter.util.DecodeJwt;
import cl.auter.util.JWTResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/maintenances")
@SecurityRequirement(name = "JWT")
public class MaintenancesController {
	
	private final MaintenancesRepository recRepository;
	private       DecodeJwt     decJwt = new DecodeJwt();
	
	@Autowired
	public MaintenancesController(MaintenancesRepository recRepository) {
		this.recRepository = recRepository;
	}
	
	@GetMapping("")
	public List<MaintenancesEntity> findAll(@RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return recRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		}
		else {
			return null;
		}
		
	}
	
	@GetMapping("/{id}")
    public MaintenancesEntity findMantById(@PathVariable(value = "id") int id, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			try {
				return recRepository.findById(id)
					      .orElseThrow(() -> null);
			}
			catch(Exception e) {
				System.out.println(e);
				return null;
			}
		}
		else {
			return null;
		}
			
    }
	
	@PostMapping("")
	public ResponseEntity<JsonObject> saveNewMaintenance(@RequestBody MaintenancesEntity json, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") && jwtResponse.getRole().equals("ADMINISTRATOR") ) {
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
		else {
			return null;
		}
			
    }
	
	@PutMapping("")
	public ResponseEntity<JsonObject> editMaintenance(@RequestBody MaintenancesEntity json,@RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") && jwtResponse.getRole().equals("ADMINISTRATOR") ) {
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
		else {
			return null;
		}		
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<JsonObject> deleteMaintenance(@PathVariable(value = "id") int id, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") && jwtResponse.getRole().equals("ADMINISTRATOR") ) {
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
		else {
			return null;
		}	
    }

}
