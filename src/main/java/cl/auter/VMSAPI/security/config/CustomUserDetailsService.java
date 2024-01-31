package cl.auter.VMSAPI.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.UsersEntity;
import cl.auter.VMSAPI.service.UsersService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		UsersEntity user = userService.findByUsername(username);
		roles = Arrays.asList(new SimpleGrantedAuthority(user.getUser_type()));		

		return new User(user.getUsername(), user.getPassword(), true, true, true, true, roles);

		
		
	}

}
