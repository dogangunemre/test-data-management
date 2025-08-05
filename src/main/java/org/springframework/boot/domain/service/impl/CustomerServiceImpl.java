package org.springframework.boot.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.api.request.PhoneNumberRequest;
import org.springframework.boot.config.ConnectionDataBase;
import org.springframework.boot.config.DataSourceContextHolder;
import org.springframework.boot.config.EntityManagerService;
import org.springframework.boot.domain.constant.AppConstants;
import org.springframework.boot.domain.service.input.CustomerInput;
import org.springframework.boot.domain.service.output.CustomerOutput;
import org.springframework.boot.domain.service.spec.CustomerService;
import org.springframework.boot.entity.CustomerEntity;
import org.springframework.boot.mapper.CustomerMapper;
import org.springframework.boot.props.UserProps;
import org.springframework.boot.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ConnectionDataBase connectionDataBase;

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);
    private final UserProps userProps;
    private final EntityManagerService entityManagerService;

    @Transactional
    @Override
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CustomerOutput create(CustomerInput customerInput) {
        DataSourceContextHolder.setDataSourceType(AppConstants.DEFAULT_DB);
        entityManagerService.clearEntityManager();
        connectionDataBase.connectionDb(AppConstants.DEFAULT_DB);

        CustomerEntity entity = new CustomerEntity();
        mapper.createEntityFromInput(customerInput, entity);
        customerRepository.saveAndFlush(entity);
        return mapper.entity2Output(entity);
    }

    @Override
    public CustomerOutput update(Long id, CustomerInput customerInput) {
        Optional<CustomerEntity> entityOpt = customerRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new EntityNotFoundException(AppConstants.CUSTOMER_NOT_FOUND_MESSAGE + id);
        }
        CustomerEntity entity = entityOpt.get();
        mapper.updateEntityFromInput(customerInput, entity);
        entity.setId(id);
        customerRepository.saveAndFlush(entity);
        return mapper.entity2Output(entity);
    }

    @Override
    public List<CustomerOutput> getAll() {
        List<CustomerEntity> entities = customerRepository.findAll();
        return mapper.entity2OutputList(entities);
    }

    @Override
    public CustomerOutput getById(Long id) {
        Optional<CustomerEntity> entityOpt = customerRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new EntityNotFoundException(AppConstants.CUSTOMER_NOT_FOUND_MESSAGE + id);
        }
        return mapper.entity2Output(entityOpt.get());
    }

    @Override
    public void delete(Long id) {
        Optional<CustomerEntity> entityOpt = customerRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new EntityNotFoundException(AppConstants.CUSTOMER_NOT_FOUND_MESSAGE + id);
        }
        customerRepository.delete(entityOpt.get());
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CustomerOutput getByUserType(String userType) {
        Optional<CustomerEntity> entityOpt;
        DataSourceContextHolder.setDataSourceType(AppConstants.DEFAULT_DB);
        entityManagerService.clearEntityManager();
        connectionDataBase.connectionDb(AppConstants.DEFAULT_DB);
        entityOpt = customerRepository.findRandomEntityByUserType(userType);
        try {
            //entityOpt.ifPresent(customerEntity -> customerRepository.updateInUsed(customerEntity.getId()));
        } catch (Exception e) {
            log.error("Error while querying customer repository", e);
            throw new EntityNotFoundException("Customer entity not found due to an error: " + e.getMessage());
        }

        CustomerOutput customerOutput = entityOpt.map(mapper::entity2Output)
                .orElseThrow(() -> new EntityNotFoundException(AppConstants.DEFAULT_NOT_CUSTOMER));

        return customerOutput;
    }

    @Override
    public CustomerOutput getUserByPhoneNumber(String phoneNumber) {
        DataSourceContextHolder.setDataSourceType(AppConstants.DEFAULT_DB);
        entityManagerService.clearEntityManager();
        connectionDataBase.connectionDb(AppConstants.DEFAULT_DB);

        Optional<CustomerEntity> entityOpt = customerRepository.getUserByPhoneNumber(phoneNumber);
        if (entityOpt.isEmpty()) {
            throw new EntityNotFoundException(AppConstants.CUSTOMER_NOT_FOUND_MESSAGE + phoneNumber);
        }
        return mapper.entity2Output(entityOpt.get());
    }


    @Override
    public List<CustomerOutput> getByUserTypeList(String userType) {
        List<CustomerEntity> entities = null;
        try {
            connectionDataBase.connectionDb(AppConstants.DEFAULT_DB);
            entities = customerRepository.getByUserTypeList(userType);
        } catch (Exception e) {
            return mapper.entity2OutputList(entities);
        }
        return mapper.entity2OutputList(entities);
    }

}
