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
				.antMatchers(HttpMethod.POST, "/authenticate").permitAll()
				// Regiones
				.antMatchers(HttpMethod.GET, "/regions").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/regions").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/regions")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/regions").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/regions/**/communes").hasRole("ADMIN")
				// cruces
				.antMatchers(HttpMethod.GET, "/junctions").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/junctions").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/junctions")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/junctions").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/junctions/search").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.POST, "/junctions/devices/search").hasAnyRole("ADMIN", "USER")
				// devices
				.antMatchers(HttpMethod.GET, "/devices").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/devices").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/devices")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/devices").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/devices/contracts/search").hasAnyRole("ADMIN", "USER")
				// device types
				.antMatchers(HttpMethod.GET, "/devices_type").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/devices_type").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/devices_type").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/devices_type").hasRole("ADMIN")
				// subsidiaries
				.antMatchers(HttpMethod.GET, "/subsidiaries").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/subsidiaries").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/subsidiaries").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/subsidiaries").hasRole("ADMIN")
				// subsidiarie_commune */
				.antMatchers(HttpMethod.POST, "/commune_subsidiary").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/commune_subsidiary").hasRole("ADMIN")
				// communes
				.antMatchers(HttpMethod.GET, "/communes").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/communes").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/communes")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/communes").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/communes/search").hasAnyRole("ADMIN", "USER")
				// mobiles
				.antMatchers(HttpMethod.GET, "/mobiles").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/mobiles").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/mobiles")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/mobiles").hasRole("ADMIN")
				// contracts
				.antMatchers(HttpMethod.GET, "/contracts").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/contracts").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/contracts")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/contracts").hasRole("ADMIN")
				// users
				.antMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "USER").antMatchers(HttpMethod.PUT, "/users")
				.hasRole("ADMIN").antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/users/search")
				.hasRole("ADMIN")
				// users
				.antMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "USER").antMatchers(HttpMethod.PUT, "/users")
				.hasRole("ADMIN").antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/users/search")
				.hasRole("ADMIN")
				// roles
				.antMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
				// technicians
				.antMatchers(HttpMethod.GET, "/technicians").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/technicians").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/technicians").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/technicians").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/technicians/search").hasRole("ADMIN")
				// departures
				.antMatchers(HttpMethod.GET, "/departures").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/departures").hasAnyRole("ADMIN", "USER").antMatchers(HttpMethod.POST, "/departures")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/departures").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.POST, "/departures/search").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.GET, "/departures/**/departures_details").hasAnyRole("ADMIN", "USER")
				// departures detail
				.antMatchers(HttpMethod.GET, "/departures_details").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/departures_details").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.POST, "/departures_details").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.DELETE, "/departures_details").hasAnyRole("ADMIN", "USER")
				// system_parameters
				.antMatchers(HttpMethod.GET, "/system_parameters").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/system_parameters").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/system_parameters").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/system_parameters").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/system_parameters/search").hasAnyRole("ADMIN", "USER")
				//external_provider
				.antMatchers(HttpMethod.GET, "/external_provider").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/external_provider").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/external_provider").hasRole("USER")
				.antMatchers(HttpMethod.DELETE, "/external_provider").hasRole("USER")
				
				//external_provider_type
				.antMatchers(HttpMethod.GET, "/external_provider_type").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/external_provider_type").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/external_provider_type").hasRole("USER")
				.antMatchers(HttpMethod.DELETE, "/external_provider_type").hasRole("USER")

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
		source.registerCorsConfiguration("/authenticate", configuration);
		source.registerCorsConfiguration("/regions/**", configuration);
		source.registerCorsConfiguration("/users/**", configuration);
		source.registerCorsConfiguration("/junctions/**", configuration);
		source.registerCorsConfiguration("/devices/**", configuration);
		source.registerCorsConfiguration("/system_parameters/**", configuration);
		source.registerCorsConfiguration("/task_attentions/**", configuration);
		source.registerCorsConfiguration("/companies/**", configuration);
		source.registerCorsConfiguration("/technicians/**", configuration);
		source.registerCorsConfiguration("/group_routes/**", configuration);
		source.registerCorsConfiguration("/tasks/**", configuration);
		source.registerCorsConfiguration("/communes/**", configuration);
		source.registerCorsConfiguration("/routeTemplates/**", configuration);
		source.registerCorsConfiguration("/operation_states/**", configuration);
		source.registerCorsConfiguration("/commune_subsidiary/**", configuration);
		source.registerCorsConfiguration("/departures_detail/**", configuration);
		source.registerCorsConfiguration("/view_output_type/**", configuration);
		source.registerCorsConfiguration("/service_request/**", configuration);
		source.registerCorsConfiguration("/task_attention/**", configuration);
		source.registerCorsConfiguration("/distribution/**", configuration);
		source.registerCorsConfiguration("/departures/**", configuration);
		source.registerCorsConfiguration("/workgroups/**", configuration);
		source.registerCorsConfiguration("/attentions/**", configuration);
		source.registerCorsConfiguration("/routes/**", configuration);
		source.registerCorsConfiguration("/routeTemplatesClassification/**", configuration);
		source.registerCorsConfiguration("/severity/**", configuration);
		source.registerCorsConfiguration("/claims/**", configuration);
		source.registerCorsConfiguration("/role/**", configuration);
		source.registerCorsConfiguration("/attentionstates/**", configuration);
		source.registerCorsConfiguration("/coordinations/**", configuration);
		source.registerCorsConfiguration("/subsidiaries/**", configuration);
		source.registerCorsConfiguration("/providers/**", configuration);
		source.registerCorsConfiguration("/attention_task/**", configuration);
		source.registerCorsConfiguration("/device_types/**", configuration);
		source.registerCorsConfiguration("/routes_detail/**", configuration);
		source.registerCorsConfiguration("/statuses/**", configuration);
		source.registerCorsConfiguration("/junctiondevices/**", configuration);
		source.registerCorsConfiguration("/devices/**", configuration);
		source.registerCorsConfiguration("/mobiles/**", configuration);
		source.registerCorsConfiguration("/contracts/**", configuration);
		source.registerCorsConfiguration("/reports/**", configuration);
		source.registerCorsConfiguration("/origin_type/**", configuration); // source type?
		source.registerCorsConfiguration("/deviceoperation/**", configuration);
		source.registerCorsConfiguration("/routes_type/**", configuration);
		source.registerCorsConfiguration("/complaintrecord/**", configuration); //
		source.registerCorsConfiguration("/claim_link/**", configuration);
		source.registerCorsConfiguration("/claim_history/**", configuration);
		source.registerCorsConfiguration("/link/**", configuration);
		source.registerCorsConfiguration("/link2/**", configuration);
		source.registerCorsConfiguration("/origin/**", configuration);
		source.registerCorsConfiguration("/turn/**", configuration); // workshift
		source.registerCorsConfiguration("/technician_type/**", configuration);
		source.registerCorsConfiguration("/source_type/**", configuration);
		source.registerCorsConfiguration("/operation_device/**", configuration);
		source.registerCorsConfiguration("/failure_classification/**", configuration);
		source.registerCorsConfiguration("/alerts/**", configuration);
		source.registerCorsConfiguration("/external_provider/**", configuration);
		source.registerCorsConfiguration("/external_provider_type/**", configuration);
		source.registerCorsConfiguration("/observation/**", configuration);
		source.registerCorsConfiguration("/device_type_cost/**", configuration);
		return source;
	}

}