package org.springframework.boot.domain.service.spec;

import org.springframework.boot.api.request.PhoneNumberRequest;
import org.springframework.boot.domain.service.input.CustomerInput;
import org.springframework.boot.domain.service.output.CustomerOutput;

import java.util.List;

public interface CustomerService {
  
  CustomerOutput create(CustomerInput customerInput);

  CustomerOutput update(Long id, CustomerInput customerInput);

  List<CustomerOutput> getAll();

  CustomerOutput getById(Long id);

  CustomerOutput getByUserType(String userType);

  CustomerOutput getUserByPhoneNumber(String phoneNumber);

  List<CustomerOutput> getByUserTypeList(String userType);

  void delete(Long id);
}
