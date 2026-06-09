package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.address.AddressRequestDto;
import com.hediyesilaozyurt.dto.dto.address.AddressResponseDto;
import com.hediyesilaozyurt.dto.dto.address.AddressUpdateDto;
import com.hediyesilaozyurt.entities.entities.Address;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    //CREATE
    //AddressRequestDto to Entity
    @Mapping(target="id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "fullAddress",ignore = true)
    @Mapping(target = "customer",ignore = true) //will be set in service layer
    Address toEntity(AddressRequestDto request);

    //RESPONSE
    //ENTITIY TO RESPONSEDTO
    @Mapping(source = "customer.id", target = "customerId") // nested object->flat field
    AddressResponseDto toResponse(Address address);

    //UPDATE
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="id",ignore = true)
    @Mapping(target = "fullAddress",ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(AddressUpdateDto request, @MappingTarget Address address);

    //LIST
    List<AddressResponseDto> toResponseList(List<Address> addresses);
}
