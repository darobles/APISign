package cl.auter.VMSAPI.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class EnlaceDatasourceConfiguration {

	@Bean
	@ConfigurationProperties("spring.datasource.enlace")
	public DataSourceProperties enlaceDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource enlaceDataSource() {
		return enlaceDataSourceProperties().initializeDataSourceBuilder().build();
	}

}