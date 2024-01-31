package cl.auter.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;

public class DecodeJwt {
	
	private String jwt;
	
	public DecodeJwt() {
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	public JWTResponse validateToken(String jwt) throws ParseException {
		Boolean response = false;
		long currentTime = System.currentTimeMillis() / 1000;
		JWT  token       = JWTParser.parse(jwt.replace("Bearer ", ""));
		JWTResponse jwtr = new JWTResponse();
		String sub    = token.getJWTClaimsSet().getStringClaim("sub");
		String groups = token.getJWTClaimsSet().getStringClaim("role");
		Date exp = (Date) token.getJWTClaimsSet().getExpirationTime(); 
		
		jwtr.setRole(groups);
		
		if ( sub.equals("auter") ) {
			response = true;
			jwtr.setGenMessage("authorized");
		}
		else {
			jwtr.setGenMessage("Token inválido");
		}
		
		/* Verificamos expiración*/
		
		if ( exp.getTime() < currentTime ) {
			response = false;
			jwtr.setGenMessage("Token expirado");
		}
		else {
			response = true;
			jwtr.setGenMessage("authorized");
		}		
		
		return jwtr;
	}
	
	
}
