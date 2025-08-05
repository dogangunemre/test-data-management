package org.springframework.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JpaRepositoriesConfig.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "org.springframework.boot.repository",
        entityManagerFactoryRef = "dynamicEntityManagerFactory",
        transactionManagerRef = "dynamicTransactionManager"
)
public class JpaRepositoriesConfig {

}