package cl.auter.VMSAPI.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.LogEntity;
import cl.auter.VMSAPI.model.VMSResponseEntity;
import cl.auter.VMSAPI.repository.LogRepository;
import cl.auter.util.DecodeJwt;
import cl.auter.util.JWTResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/log")
public class LogController {
	
	private final LogRepository logRepository;
	private       DecodeJwt     decJwt = new DecodeJwt();
	
	@Autowired
	public LogController(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	@GetMapping("")
	public List<LogEntity> findAll(@RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return logRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		}
		else {
			return null;
		}
		
	}
	
	@PostMapping("")
	public VMSResponseEntity newLogRegister(@RequestBody LogEntity json, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		VMSResponseEntity response = new VMSResponseEntity();
		response.setMessage("error: Not authorized");
		response.setStatus(401);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			//JsonObjectBuilder jsn = Json.createObjectBuilder();
			
			try {
				logRepository.save(json);
				response.setMessage("success");
				response.setStatus(200);
			}
			catch(Exception e) {
				System.out.println(e);
				response.setMessage("error");
				response.setStatus(500);
			}
			
			
		}
		return response;
    }
}
