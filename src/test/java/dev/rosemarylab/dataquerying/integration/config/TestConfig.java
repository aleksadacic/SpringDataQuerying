package dev.rosemarylab.dataquerying.integration.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "dev.rosemarylab.dataquerying.integration.repository")
@ComponentScan(basePackages = {"dev.rosemarylab.dataquerying.integration", "dev.rosemarylab.dataquerying.config"})
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    @SuppressWarnings("unused")
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernatePersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
        return persistenceProvider.createEntityManagerFactory("test-unit", new Properties());
    }

    @Bean
    public JpaTransactionManager transactionManager(@Autowired EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
}
