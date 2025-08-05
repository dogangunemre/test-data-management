package org.springframework.boot.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.boot.api.request.CustomerRequest;
import org.springframework.boot.api.response.CustomerResponse;
import org.springframework.boot.domain.service.input.CustomerInput;
import org.springframework.boot.domain.service.output.CustomerOutput;
import org.springframework.boot.entity.CustomerEntity;
import org.springframework.web.bind.annotation.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerInput request2Input(CustomerRequest customerRequest);

  CustomerResponse output2Response(CustomerOutput customerOutput);

  CustomerOutput entity2Output(CustomerEntity customerEntity);

  List<CustomerOutput> entity2OutputList(List<CustomerEntity> customerEntityList);

  List<CustomerResponse> output2ResponseList(List<CustomerOutput> customerOutputList);

 // @Mapping(target = "id", ignore = true)
  CustomerEntity input2Entity(CustomerInput customerInput);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromInput(
      CustomerInput input, @MappingTarget CustomerEntity entity);

 // @Mapping(target = "id", ignore = true)
  void createEntityFromInput(CustomerInput input, @MappingTarget CustomerEntity entity);

  
}
