package cl.auter.VMSAPI.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import cl.auter.VMSAPI.security.config.CustomJwtAuthenticationFilter;
import cl.auter.VMSAPI.security.config.CustomUserDetailsService;
import cl.auter.VMSAPI.security.config.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs","/Admin", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**", "/swagger-ui/**", "/api/authenticate", "/vms-ui.html", "/api-docs", "/vms/**",
			"/task_attention/**", "/websocket/**","/test/**"
			// other public endpoints of your API may be appended to this array
	};

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().authorizeRequests()
				// auth
				.antMatchers(HttpMethod.POST, "/api/authenticate").permitAll()				
				.antMatchers(HttpMethod.GET, "/api/notifications").hasAnyRole("ADMIN","USER")
				.antMatchers(HttpMethod.GET, "/api/maintenances").hasAnyRole("ADMIN","USER")
				.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN","USER").antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN","USER")
				.antMatchers(HttpMethod.GET).hasAnyRole("ADMIN","USER")				
				.antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated()
				// if any exception occurs call this
				.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
		http.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/authenticate", configuration);
		source.registerCorsConfiguration("/api/notifications", configuration);
		source.registerCorsConfiguration("/api/maintenances", configuration);
		source.registerCorsConfiguration("/api/maintenances/*", configuration);
		source.registerCorsConfiguration("/api/sequence", configuration);
		source.registerCorsConfiguration("/api/sequence/*", configuration);
		source.registerCorsConfiguration("/api/sequence/*/message", configuration);
		source.registerCorsConfiguration("/api/sequence/message/*", configuration);
		source.registerCorsConfiguration("/api/log", configuration);
		source.registerCorsConfiguration("/api/group", configuration);
		source.registerCorsConfiguration("/api/signType", configuration);
		source.registerCorsConfiguration("/api/signType/*", configuration);
		source.registerCorsConfiguration("/api/sign", configuration);
		source.registerCorsConfiguration("/api/sign/*", configuration);
		source.registerCorsConfiguration("/api/sign/*/full", configuration);
		source.registerCorsConfiguration("/api/sign/*/message", configuration);
		source.registerCorsConfiguration("/api/sign/*/message/send", configuration);
		source.registerCorsConfiguration("/api/sign/*/sequence/*/send", configuration);
		source.registerCorsConfiguration("/api/sign/*/message/*/send", configuration);
		source.registerCorsConfiguration("/api/sign/*/sequence", configuration);
		source.registerCorsConfiguration("/api/sign/*/group", configuration);
		source.registerCorsConfiguration("/api/sign/*/brightness", configuration);
		source.registerCorsConfiguration("/api/sign/*/brightness/*", configuration);
		source.registerCorsConfiguration("/api/users", configuration);
		source.registerCorsConfiguration("/api/users/*", configuration);

		source.registerCorsConfiguration("/api/cameraType", configuration);
		source.registerCorsConfiguration("/api/message", configuration);
		source.registerCorsConfiguration("/api/message/*", configuration);
		source.registerCorsConfiguration("/api/message/*/image", configuration);
		source.registerCorsConfiguration("/api/message/*/send", configuration);
		source.registerCorsConfiguration("/api/message/*/group", configuration);
		source.registerCorsConfiguration("/api/message/*/image/*", configuration);
		source.registerCorsConfiguration("/api/last-images/*", configuration);
		source.registerCorsConfiguration("/api/maintenances", configuration); //http://localhost:8080/api/sign/21/message
		source.registerCorsConfiguration("/api/sign/*/turnon/*", configuration); 
		source.registerCorsConfiguration("/api/allimages", configuration);
		source.registerCorsConfiguration("/api/sign/*/last", configuration);
		source.registerCorsConfiguration("/api/sign/*/send", configuration);
		
		
		
		//api/sign/23/sequence
		return source;
	}

}