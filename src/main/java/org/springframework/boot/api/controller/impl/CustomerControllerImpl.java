package org.springframework.boot.api.controller.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.api.controller.spec.CustomerController;
import org.springframework.boot.api.request.CustomerRequest;
import org.springframework.boot.api.response.CustomerResponse;
import org.springframework.boot.domain.service.input.CustomerInput;
import org.springframework.boot.domain.service.output.CustomerOutput;
import org.springframework.boot.domain.service.spec.CustomerService;
import org.springframework.boot.mapper.CustomerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService service;
    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Override
    public ResponseEntity<String> create(CustomerRequest request) {
        CustomerInput input = mapper.request2Input(request);
        CustomerOutput domainOutput = service.create(input);
        return new ResponseEntity<>(domainOutput.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, CustomerRequest request) {
        CustomerInput input = mapper.request2Input(request);
        service.update(id, input);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getAll() {
        List<CustomerOutput> outputList = service.getAll();
        List<CustomerResponse> responseList = mapper.output2ResponseList(outputList);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponse> getById(Long id) {
        CustomerOutput output = service.getById(id);
        CustomerResponse response = mapper.output2Response(output);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponse> getByUserType(String userType) {
        CustomerOutput output = service.getByUserType(userType);
        CustomerResponse response = mapper.output2Response(output);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponse> getUserByPhoneNumber(String phoneNumber) {
        CustomerOutput output = service.getUserByPhoneNumber(phoneNumber);
        CustomerResponse response = mapper.output2Response(output);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getByUserTypeList(String userType) {
        List<CustomerOutput> outputList = service.getByUserTypeList(userType);
        List<CustomerResponse> responseList = mapper.output2ResponseList(outputList);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
