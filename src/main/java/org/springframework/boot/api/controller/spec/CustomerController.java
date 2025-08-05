package org.springframework.boot.api.controller.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.api.request.CustomerRequest;
import org.springframework.boot.api.response.CustomerResponse;
import org.springframework.boot.domain.constant.AppConstants;
import org.springframework.boot.domain.constant.UserTypeConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.annotation.Timed;

import java.util.List;


@Tag(name = "CustomerController")
@RequestMapping(AppConstants.API + AppConstants.API)
@Timed(value = "CustomerController")
public interface CustomerController {

    @PostMapping
    ResponseEntity<String> create(
            @RequestBody CustomerRequest domainRequest);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @RequestBody CustomerRequest domainRequest);

    @GetMapping()
    ResponseEntity<List<CustomerResponse>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getById(
            @PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
            @PathVariable("id") Long id);

    @Operation(
            summary = "Kullanıcıyı Tipine Göre Getir",
            description = """
                    Kullanıcı tipini ve ortamı belirterek kullanıcı al. 
                                        
                    ### 🧪 Test Senaryoları:
                    **Geçerli Kullanıcı Tipi ile Başarılı Yanıt**  
                    - **Expected Response:**  
                      ```json
                      {
                         "id": "string",
                         "name": "string",
                         "surname": "string",
                         "identityValue": "string",
                         "phoneNumber": "string",
                         "password": "string",
                         "email": "string",
                         "otpCode": "string"
                      }
                      ```
                                        
                    ** Geçersiz Kullanıcı Tipi**  
                    - **Expected Response:**  
                      ```json
                      {
                        "error": "Invalid userType"
                      }
                      ```
                    """
    )
    @GetMapping(AppConstants.USER_TYPE)
    ResponseEntity<CustomerResponse> getByUserType(
            @Parameter(description = " ### Available values " + UserTypeConstants.BANK, required = true)
            @RequestParam("userType") String userType);

    @Operation(
            summary = "Telefon numarası ile kullanıcı bilgilerini getiren servis"
    )
    @GetMapping("/{phoneNumber}")
    ResponseEntity<CustomerResponse> getUserByPhoneNumber(
            @PathVariable("phoneNumber") String phoneNumber);

    @Operation(
            summary = "Kullanıcıyı Tipine  Göre Liste Getir",
            description = """
                    Kullanıcı tipini ve ortamı belirterek kullanıcı listesi al.
                                        
                    ### 🧪 Test Senaryoları:
                    **Geçerli Kullanıcı Tipi ile Başarılı Yanıt**  
                    - **Expected Response:**  
                      ```json
                        [
                          {
                            "id": "string",
                            "name": "string",
                            "surname": "string",
                            "identityValue": "string",
                            "phoneNumber": "string",
                            "password": "string",
                            "email": "string",
                            "otpCode": "string"
                          }
                        ]          ```
                                        
                    **Geçersiz Kullanıcı Tipi**  
                    - **Expected Response:**  
                      ```json
                      {
                        "error": "Invalid userType"
                      }
                      ```
                    """
    )
    @GetMapping(AppConstants.USER_TYPE_LIST)
    ResponseEntity<List<CustomerResponse>> getByUserTypeList(
            @Parameter(description = "Available values: " + UserTypeConstants.BANK, required = true)
            @RequestParam("userType") String userType);

}
