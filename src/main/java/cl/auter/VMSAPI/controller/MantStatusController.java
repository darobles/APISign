package cl.auter.VMSAPI.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.MantStatusEntity;
import cl.auter.VMSAPI.repository.MantStatusRepository;
import cl.auter.util.DecodeJwt;
import cl.auter.util.JWTResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/maintenances/status")
public class MantStatusController {
	
	private final MantStatusRepository mantStatusRepository;
	private       DecodeJwt     decJwt = new DecodeJwt();
	
	@Autowired
	public MantStatusController(MantStatusRepository msRepository) {
		this.mantStatusRepository = msRepository;
	}
	
	@GetMapping("")
	public List<MantStatusEntity> findAll(@RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return mantStatusRepository.findAll();
		}
		else {
			return null;
		}
	}
}
