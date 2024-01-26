package cl.auter.VMSAPI.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class SmaDatasourceConfiguration {

	@Bean
	@ConfigurationProperties("spring.datasource.sma")
	DataSourceProperties smaDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean
	DataSource smaDataSource() {
		return smaDataSourceProperties().initializeDataSourceBuilder().build();
	}

}