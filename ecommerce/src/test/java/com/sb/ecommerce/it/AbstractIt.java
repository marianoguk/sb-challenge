package com.sb.ecommerce.it;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {AbstractIt.Initializer.class})
@ExtendWith(SpringExtension.class)
public abstract class AbstractIt {

    private static final String DOCKER_IMAGE_NAME = "postgres:11.1";
    private static PostgreSQLContainer sqlContainer;

    static {
        sqlContainer = new PostgreSQLContainer(DOCKER_IMAGE_NAME)
                .withDatabaseName("integration-tests-db")
                // .withDatabaseName("scratch_bling")
                .withUsername("sa")
                .withPassword("sa");
        sqlContainer.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + sqlContainer.getUsername(),
                    "spring.datasource.password=" + sqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}