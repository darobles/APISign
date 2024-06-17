package cl.auter.VMSAPI.controller;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.JWSSigner;

import cl.auter.VMSAPI.model.ChangePasswordEntity;
import cl.auter.VMSAPI.model.UsersEntity;
import cl.auter.VMSAPI.service.UsersService;
import cl.auter.util.DecodeJwt;
import cl.auter.util.Util;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "JWT")
public class UsersController {
	@Autowired
	private  UsersService usrRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	private       DecodeJwt       decJwt = new DecodeJwt();
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	
	
	@GetMapping("")
	//public List<UsersEntity> findAll(@RequestHeader(name = "Authorization", required = false) String token) throws ParseException{
	public List<UsersEntity> findAll() throws ParseException {
		//JWTResponse jwtResponse = decJwt.validateToken(token);
		//if ( jwtResponse.getGenMessage().equals("authorized") ) {
			return usrRepository.findAll();
		/*}
		else {
			return null;
		}*/
		
	}
	
	@GetMapping("/{username}")
	//public UsersEntity findByUsername(@PathVariable String username, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
	public UsersEntity findByUsername(@PathVariable String username) throws ParseException{
		/*JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") ) {*/
			return usrRepository.findByUsername(username);
		/*}
		else {
			return null;
		}*/
		
	}
	
	@PostMapping("/validate_password")
	//public ResponseEntity<JsonObject> validatePassword(@RequestBody ChangePasswordEntity json, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
	public ResponseEntity<JsonObject> validatePassword(@RequestBody ChangePasswordEntity json) throws ParseException{
		/*JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") && jwtResponse.getRole().equals("ADMINISTRATOR") ) {*/
			JsonObjectBuilder jsn  = Json.createObjectBuilder();
			try {
				UsersEntity user = usrRepository.findByUsername(json.getUsername());
				if ( user != null ) {
					/*authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
							user.getUsername(), user.getPassword()));*/
					UsersEntity newuser = new UsersEntity();
					newuser.setId(user.getId());
					newuser.setUsername( user.getUsername());
					newuser.setNombre(   user.getNombre() );
					PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String encodedPassword = passwordEncoder.encode(json.getNewPassword());
					newuser.setPassword(encodedPassword);
					newuser.setUser_type(user.getUser_type() );
					newuser.setRole_id(user.getRole_id());
					usrRepository.save(newuser);
					jsn.add("result","success");
				}
				else {
					System.out.println("Error1");
					jsn.add("result","error");
				}
			}
			catch(Exception e) {
				System.out.println("Error2");
				System.out.println(e);
				jsn.add("result","error");
			}
			return ResponseEntity.ok(jsn.build());
		/*}
		else {
			return null;
		}*/
	}
	
	@PutMapping("")
	//public ResponseEntity<JsonObject> updateUser(@RequestBody UsersEntity json, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException{
	public ResponseEntity<JsonObject> updateUser(@RequestBody UsersEntity json) throws ParseException{
		/*JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") && jwtResponse.getRole().equals("ADMINISTRATOR") ) {*/
			JsonObjectBuilder jsn  = Json.createObjectBuilder();
			try {
				UsersEntity user = usrRepository.findByUsername(json.getUsername());
				UsersEntity newuser = new UsersEntity();
				newuser.setId(user.getId());
				newuser.setUsername( user.getUsername());
				newuser.setNombre(   json.getNombre() );
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(json.getPassword());
				if(!encodedPassword.equals(user.getPassword()))
				{
					newuser.setPassword(encodedPassword);					
				}
				else{
					newuser.setPassword(user.getPassword());
				}
				newuser.setUser_type(json.getUser_type() );
				usrRepository.save(newuser);
				jsn.add("result","success");
	
			}
			catch(Exception e) {
				System.out.println(e);
				jsn.add("result","error");
			}
			return ResponseEntity.ok(jsn.build());
		/*}
		else{
			return null;
		}*/
			
	}
	
	@DeleteMapping("/{username}")
	//public ResponseEntity<JsonObject> deleteUser(@PathVariable String username, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
	public ResponseEntity<JsonObject> deleteUser(@PathVariable String username) throws ParseException {
		/*JWTResponse jwtResponse = decJwt.validateToken(authorizationHeader);
		if ( jwtResponse.getGenMessage().equals("authorized") && jwtResponse.getRole().equals("ADMINISTRATOR") ) {*/
			JsonObjectBuilder jsn     = Json.createObjectBuilder();
	        UsersEntity       usuario = usrRepository.findByUsername(username);
	        if (usuario != null) {
	        	try {
	        		usrRepository.delete(usuario);
	        		jsn.add("result", "success");
	        	}
	        	catch(Exception e) {
	        		System.out.println(e);
	        		jsn.add("result", "error");
	        	}        	
	        }
	        else {
	        	jsn.add("result", "usuario no encontrado");
	        }
	        return ResponseEntity.ok(jsn.build());
		/*}
		else {
			return null;
		}*/
			
    }
	
	
	@PostMapping("")
	//public ResponseEntity<JsonObject> saveNewUser(@RequestBody UsersEntity json, @RequestHeader(value="authorization") String authorizationHeader) throws ParseException {
	public ResponseEntity<JsonObject> saveNewUser(@RequestBody UsersEntity json) throws ParseException {
			JsonObjectBuilder jsn = Json.createObjectBuilder();			
			try {
				UsersEntity newuser = new UsersEntity();
				
				List<UsersEntity> users = usrRepository.findAll();
				Boolean find = false;
				for ( int i = 0 ; i < users.size() ; i ++ ) {
					if ( users.get(i).getUsername().equals(json.getUsername()) ) {
						find = true;
						break;
					}
				}
				
				if ( find == false ) {
					PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String encodedPassword = passwordEncoder.encode(json.getPassword());
					newuser.setUsername( json.getUsername());
					newuser.setNombre(   json.getNombre() );
					newuser.setPassword( encodedPassword );
					newuser.setUser_type(json.getUser_type() );
					newuser.setRole_id(json.getRole_id());
					usrRepository.save(newuser);
					jsn.add("result", "success");
				}
				else {
					jsn.add("result", "duplicated username");
				}
			}
			catch(Exception e) {
				System.out.println(e);
				jsn.add("result", "error");
				jsn.add("detail", e.toString());
			}
			
			return ResponseEntity.ok(jsn.build());
    }
	
	
	@PostMapping("/auth")
	public ResponseEntity<JsonObject> getAuth(@RequestBody UsersEntity json) throws Exception {
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		String            tkn = "";
		PrivateKey privateKey = null;
        if(OS.contains("win")){
            privateKey = loadPrivateKey("c:/private.pem");
        }
        else{
            privateKey = loadPrivateKey("/usr/local/sictrav/private.pem");
        }

        JWSSigner signer = new RSASSASigner(privateKey);
		Util util = new Util();
		try {
			String  passwd = util.createMD5Hash(json.getPassword());
			boolean found  = false;
			
			List<UsersEntity> usersList = usrRepository.findAll();
			for ( int i = 0 ; i < usersList.size() ; i ++ ) {
				if ( usersList.get(i).getUsername().equals(json.getUsername()) ) {
					if ( passwd.equals(usersList.get(i).getPassword()) ) {
						long currentTime = System.currentTimeMillis() / 1000;
				        //long exp = (System.currentTimeMillis() + 43200000) / 1000;
				        long exp = (System.currentTimeMillis() + 57600000) / 1000;
				        
				        JsonObjectBuilder claimsBuilder = Json.createObjectBuilder()
			                .add("sub",  usersList.get(i).getUsername())
			                //.add("upn",  usersList.get(i).getUsername())  // ?
			                .add("iat", currentTime)
			                .add("iss", "quickstart-jwt-issuer")
			                .add("aud", "jwt-audience")
			                .add("exp", exp)
			                .add("birthdate","27-07-1989")
			                .add("zone", 1)
			                .add("groups", usersList.get(i).getUser_type());
				        			        
				        
				        JWSObject jwsObject = new JWSObject(new JWSHeader.Builder(JWSAlgorithm.RS256)
				                .type(new JOSEObjectType("jwt"))
				                .keyID("Autersa2021").build(),
				                new Payload(claimsBuilder.build().toString()));
				        jwsObject.sign(signer);
				        tkn = jwsObject.serialize();
						found = true;
						break;
					}
				}
			}
			if ( found == false ) {
				jsn.add("result", "Credenciales inválidas");
			}
			else {
				jsn.add("token", tkn);
				jsn.add("result", "success");
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
			jsn.add("result", "error");
			jsn.add("detail", e.toString());
		}
		
		return ResponseEntity.ok(jsn.build());
    }
	
	private static PrivateKey loadPrivateKey(final String fileName) throws Exception {
        try (InputStream is = new FileInputStream(fileName)) {
            byte[] contents = new byte[4096];
            int length = is.read(contents);
            String rawKey = new String(contents, 0, length, StandardCharsets.UTF_8)
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)----", "")
                    .replaceAll("\r\n", "").replaceAll("\n", "").trim();

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rawKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }
    }
	

}
