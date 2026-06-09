package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.customer.CustomerDetailResponseDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerRequestDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerResponseDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerUpdateDto;
import com.hediyesilaozyurt.entities.entities.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring",uses = {AddressMapper.class,AccountMapper.class})
public interface CustomerMapper {
    //CREATE
    //RequestDto to Entity
    @Mapping(target = "id",ignore = true)
    @Mapping(target="createdAt",ignore = true)
    @Mapping(target="updatedAt",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "addresses",ignore = true)
    @Mapping(target = "accounts",ignore = true)
    @Mapping(target = "sales",ignore = true)
    Customer toEntity(CustomerRequestDto request);

    //RESPONSE
    //entity to CustomerResponseDto
    @Mapping(target = "addressCount",expression = "java(customer.getAddresses().size())")
    @Mapping(target = "accountCount",expression = "java(customer.getAccounts().size())")
    @Mapping(target = "salesCount",expression = "java(customer.getSales().size())")
    CustomerResponseDto toResponse(Customer customer);

    //DETAIL RESPONSE
    //entity to CustomerDetailResponse
    @Mapping(target="salesCount",expression = "java(customer.getSales().size())")
    CustomerDetailResponseDto toDetailResponse(Customer customer);


    //UPDATE
    //partial update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses",ignore = true)
    @Mapping(target = "accounts",ignore = true)
    @Mapping(target = "sales",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    void updateEntity(CustomerUpdateDto request, @MappingTarget Customer customer);

    //LIST
    List<CustomerResponseDto> toResponseList(List<Customer> customers);
}