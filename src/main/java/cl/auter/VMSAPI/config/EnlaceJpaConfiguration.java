package cl.auter.VMSAPI.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
		"cl.auter.sma.enlace" }, entityManagerFactoryRef = "enlaceEntityManagerFactory", transactionManagerRef = "enlaceTransactionManager")
public class EnlaceJpaConfiguration {

    @Bean
    LocalContainerEntityManagerFactoryBean enlaceEntityManagerFactory(
            @Qualifier("enlaceDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("cl.auter.sma.enlace").build();
    }

    @Bean
    PlatformTransactionManager enlaceTransactionManager(
            @Qualifier("enlaceEntityManagerFactory") LocalContainerEntityManagerFactoryBean enlaceEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(enlaceEntityManagerFactory.getObject()));
    }
}