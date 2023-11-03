package cl.auter.VMSAPI;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

import cl.auter.util.Util;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private final UsersRepository usrRepository;
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	@Autowired
	public UsersController(UsersRepository usrRepository) {
		this.usrRepository = usrRepository;
	}
	
	
	@PostMapping("/insert")
	public ResponseEntity<JsonObject> saveNewUser(@RequestBody UsersEntity json) {
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		Util util = new Util();
		try {
			UsersEntity newuser = new UsersEntity();
			
			newuser.setUsername( json.getUsername());
			newuser.setNombre(   json.getNombre() );
			newuser.setPassword( util.createMD5Hash(json.getPassword()) );
			
			usrRepository.save(newuser);
			jsn.add("result", "success");
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
			                .add("upn",  usersList.get(i).getUsername())
			                .add("iat", currentTime)
			                .add("iss", "quickstart-jwt-issuer")
			                .add("aud", "jwt-audience")
			                .add("exp", exp)
			                .add("birthdate","27-07-1989")
			                .add("zone", 1)
			                .add("groups", "ADMINISTRATOR");
				        
				        
				        
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
