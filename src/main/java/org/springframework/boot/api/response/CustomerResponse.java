package org.springframework.boot.api.response;

import lombok.Data;

@Data
public class CustomerResponse {

    private String id;
    private String name;
    private String surname;
    private String identityValue;
    private String phoneNumber;
    private String password;
    private String email;
    private String otpCode;
    private String userType;
    private Boolean isApprovedAgreement;

}
