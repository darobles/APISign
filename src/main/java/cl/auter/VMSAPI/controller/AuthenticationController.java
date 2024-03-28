package cl.auter.VMSAPI.controller;

import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import cl.auter.VMSAPI.model.AuthenticationRequest;
import cl.auter.VMSAPI.model.AuthenticationResponse;
import cl.auter.VMSAPI.model.UsersEntity;
import cl.auter.VMSAPI.security.config.CustomUserDetailsService;
import cl.auter.VMSAPI.security.config.JwtUtil;
import cl.auter.VMSAPI.service.UsersService;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Security service", description = "Service that provides a JWT to use in other endpoints")
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UsersService userService;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	

	@Schema(name = "login")
	@RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			
			//String tkn = this.wcPost();
			//TokenAWS token = new ObjectMapper().readValue(tkn, TokenAWS.class);
			//String[] chunks = token.getIdToken().split("\\.");
			Base64.Decoder decoder = Base64.getUrlDecoder();

			//String header = new String(decoder.decode(chunks[0]));
			//String payload = new String(decoder.decode(chunks[1]));
			
			//IdToken idtoken = new ObjectMapper().readValue(payload, IdToken.class);

			
			//SignatureAlgorithm sa = SignatureAlgorithm.HS256;
			//SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());
			
			
			UsersEntity user = userService.findByUsername(authenticationRequest.getUsername());
			
			if(user == null) {
				//user = userService.findByEmail(user.getEmail());
			}
			
			if (user != null) {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			}
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println("Exception ");
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	

	
	

}