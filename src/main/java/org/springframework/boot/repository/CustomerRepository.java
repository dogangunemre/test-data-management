package org.springframework.boot.repository;

import org.springframework.boot.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Override
    List<CustomerEntity> findAll();

    List<CustomerEntity> findByUserType(String userType);

    @Query(value = "SELECT * FROM customer ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    CustomerEntity findRandomEntity();

    @Query(value = "SELECT id FROM customer ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Long findRandomId();

    @Query(value = "SELECT * FROM customer WHERE user_type = :userType AND in_used = false ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<CustomerEntity> findRandomEntityByUserType(@Param("userType") String userType);

    @Query(value = "SELECT * FROM customer WHERE is_approved_agreement = false AND in_used = false  ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<CustomerEntity> findUnapprovedUserAgreements();

    @Query(value = "SELECT * FROM customer WHERE phone_number = :phoneNumber LIMIT 1", nativeQuery = true)
    Optional<CustomerEntity> getUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = "SELECT * FROM customer WHERE user_type = :userType", nativeQuery = true)
    List<CustomerEntity> getByUserTypeList(@Param("userType") String userType);

}
