package org.springframework.boot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.domain.constant.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * DataSourceConfig.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RequiredArgsConstructor
public class DataSourceConfig {

    private final Environment env;
    @Value("${spring.datasource.names}")
    private Set<String> dataSourceNames;

    /**
     * dynamicDataSource.
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();

        for (String name : dataSourceNames) {
            DataSource dataSource = createDataSource(name);
            targetDataSources.put(name, dataSource);
        }

        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }



    private DataSource createDataSource(String name) {
        String prefix = "spring.datasource." + name + ".";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty(prefix + "url"));
        dataSource.setUsername(env.getProperty(prefix + "username"));
        dataSource.setPassword(env.getProperty(prefix + "password"));
        dataSource.setDriverClassName(env.getProperty(prefix + "driverClassName"));
        String dialect = env.getProperty(prefix + "dialect");
        System.setProperty("hibernate.dialect", dialect);
        return dataSource;
    }


    @Bean(name = "dynamicEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dynamicEntityManagerFactory(
            @Qualifier("dynamicDataSource") DataSource dataSource, JpaProperties jpaProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("org.springframework.boot");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(jpaProperties.getProperties());
        return em;
    }

    @Bean(name = "dynamicTransactionManager")
    public JpaTransactionManager dynamicTransactionManager(
            @Qualifier("dynamicEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }

    @Bean(name = "boaDbJdbcTemplate")
    public JdbcTemplate boaDbJdbcTemplate() {
        return new JdbcTemplate(createDataSource(AppConstants.DEFAULT_DB));
    }



}