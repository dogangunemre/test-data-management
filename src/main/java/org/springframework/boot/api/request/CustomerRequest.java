package org.springframework.boot.api.request;

import lombok.Data;

@Data
public class CustomerRequest {

    private String name;
    private String surname;
    private String identityValue;
    private String phoneNumber;
    private String password;
    private String email;
    private String userType;
    private Boolean isApprovedAgreement;

}
