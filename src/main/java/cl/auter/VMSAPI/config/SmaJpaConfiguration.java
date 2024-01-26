package cl.auter.VMSAPI.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
		"cl.auter.VMSAPI" }, entityManagerFactoryRef = "smaEntityManagerFactory", transactionManagerRef = "smaTransactionManager")
public class SmaJpaConfiguration {
    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean smaEntityManagerFactory(
            @Qualifier("smaDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("cl.auter.VMSAPI").build();
    }

    @Primary
    @Bean
    PlatformTransactionManager smaTransactionManager(
            @Qualifier("smaEntityManagerFactory") LocalContainerEntityManagerFactoryBean smaEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(smaEntityManagerFactory.getObject()));
    }
}