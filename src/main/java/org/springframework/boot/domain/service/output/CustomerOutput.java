package org.springframework.boot.domain.service.output;

import lombok.Data;

@Data
public class CustomerOutput {

  private String id;
  private String name;
  private String surname;
  private String identityValue;
  private String phoneNumber;
  private String password;
  private String email;
  private String userType;
  private Boolean isApprovedAgreement;

}
