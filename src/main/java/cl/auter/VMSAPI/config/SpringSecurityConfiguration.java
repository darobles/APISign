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
			"/v3/api-docs/**", "/swagger-ui/**", "/authenticate", "/sma-ui.html", "/api-docs", "/sma/**",
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
		System.out.println("SpringConf authentication");
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().authorizeRequests()
				// auth
				.antMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
				.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN","USER").antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN","USER")
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
		return source;
	}

}