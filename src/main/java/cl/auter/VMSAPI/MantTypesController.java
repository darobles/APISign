package cl.auter.VMSAPI;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.util.DecodeJwt;
import cl.auter.util.JWTResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/maintenances/types")
public class MantTypesController {
	
	private final MantTypeRepository mantTypeRepository;
	private       DecodeJwt     decJwt = new DecodeJwt();
	
	@Autowired
	public MantTypesController(MantTypeRepository mtRepository) {
		this.mantTypeRepository = mtRepository;
	}
	
	@GetMapping("")
	public List<MantTypeEntity> findAll(@RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return mantTypeRepository.findAll();
		}
		else {
			return null;
		}		
	}
}
