package org.springframework.boot.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer", schema = "public")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true, nullable = false)
    private String identityValue;

    @Column(unique = true, nullable = false)
    private String tcNumber;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    private String userType;
    private String birthDate;
    private String birthPlace;
    private String gender;
    private String motherName;
    private String fatherName;
    private String address;
}