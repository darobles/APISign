package cl.auter.VMSAPI;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.annotations.enums.*;

@OpenAPIDefinition
@SpringBootApplication
@SecurityScheme(name = "JWT", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class VmsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmsapiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
			}
		};

	}
	
	@Bean
	public OpenAPI customOpenAPI() {
	    Server server = new Server();
	    server.setUrl("http://localhost:8080");
	    //server.setUrl("https://apisma.auter.cl");
	    return new OpenAPI().servers(List.of(server));
	}

}
