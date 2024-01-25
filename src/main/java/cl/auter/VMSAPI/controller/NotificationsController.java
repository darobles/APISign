package cl.auter.VMSAPI.controller;

import java.text.ParseException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.NotificationsEntity;
import cl.auter.VMSAPI.repository.NotificationsRepository;
import cl.auter.util.DecodeJwt;
import cl.auter.util.JWTResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {
	
	private final NotificationsRepository ntfRep;
	private       DecodeJwt     decJwt = new DecodeJwt();
	
	@Autowired
	public NotificationsController(NotificationsRepository ntfRep) {
		this.ntfRep = ntfRep;
	}
	
	@GetMapping("")
	public List<NotificationsEntity> findAll(@RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return ntfRep.findAll();
		}
		else {
			return null;
		}	
	}
	
	@PutMapping("")
	public ResponseEntity<JsonObject> editNotification(@RequestBody NotificationsEntity json,@RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			JsonObjectBuilder jsn = Json.createObjectBuilder();
			try {
				ntfRep.save(json);
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
	public ResponseEntity<JsonObject> deleteNotification(@PathVariable(value = "id") int id, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			JsonObjectBuilder jsn = Json.createObjectBuilder();
			try {
				ntfRep.deleteById(id);
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
