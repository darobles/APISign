package cl.auter.VMSAPI;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.util.DecodeJwt;
import cl.auter.util.JWTResponse;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/vms-credentials")
public class VmsCredentialsController {
	
	private final VmsCredentialsRepository vmscRep;
	private       DecodeJwt       decJwt = new DecodeJwt();
	
	@Autowired
	public VmsCredentialsController(VmsCredentialsRepository vmsc) {
		this.vmscRep = vmsc;
	}
	
	@GetMapping("")
	public List<VmsCredentialsEntity> findAll(@RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return vmscRep.findAll();
		}
		else {
			return null;
		}		
	}
	
	@GetMapping("/{vms_id}")
	public VmsCredentialsEntity findByVmsId(@RequestHeader(value="authorization") String authorizationHeader, @PathVariable int vms_id) throws ParseException{	
		JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return vmscRep.findByVmsId(vms_id);
		}
		else {
			return null;
		}	
	}

}
