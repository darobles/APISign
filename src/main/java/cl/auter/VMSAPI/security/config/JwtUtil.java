package cl.auter.VMSAPI.security.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.UsersEntity;
import cl.auter.VMSAPI.service.UsersService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtUtil {

	@Autowired
	private UsersService userService;

	private String secret;
	private int jwtExpirationInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		if (userDetails.isEnabled()) {
			UsersEntity user = userService.findByUsername(userDetails.getUsername());
			claims.put("role", user.getUser_type());
			if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
				claims.put("isAdmin", true);
			}
			if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
				claims.put("isAdmin", false);
			}			
			claims.put("user_id", user.getId());
			claims.put("role_id", user.getRole_id());
		}

		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	public boolean validateToken(String authToken) {
		try {
			@SuppressWarnings("unused")
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();

	}

	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		//System.out.println("SimpleGranted ");
		List<SimpleGrantedAuthority> roles = null;

		Integer role_id = claims.get("role_id", Integer.class);
		//System.out.println("role_id " + role_id);
		if (role_id == 1) {

			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return roles;

	}

}
