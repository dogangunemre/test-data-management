package org.springframework.boot.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConnectionDataBase {
    private final EntityManagerService entityManagerService;
    private final EntityManagerFactory entityManagerFactory;
    private final JpaTransactionManager transactionManager;


    public void connectionDb(String dbName) {
        try {
            DataSourceContextHolder.clearDataSourceType();
            entityManagerService.clearEntityManager();
            DataSourceContextHolder.setDataSourceType(dbName);
            transactionManager.setEntityManagerFactory(entityManagerFactory);
            log.info("Switched to database: {}", DataSourceContextHolder.getDataSourceType());
        } catch (Exception e) {
            log.error("Error connecting DB Name: {}", dbName, e);
        }
    }


}