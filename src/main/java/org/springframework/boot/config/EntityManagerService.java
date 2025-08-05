package org.springframework.boot.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class EntityManagerService {

    @PersistenceContext
    private EntityManager entityManager;

    public void clearEntityManager() {
        /*if (entityManager != null && entityManager.isOpen()) {
            entityManager.clear();
        }*/
        entityManager.clear();
    }

}