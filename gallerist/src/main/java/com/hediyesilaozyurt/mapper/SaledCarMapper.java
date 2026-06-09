package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.saledCar.SaledCarRequestDto;
import com.hediyesilaozyurt.dto.dto.saledCar.SaledCarResponseDto;
import com.hediyesilaozyurt.entities.entities.SaledCar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaledCarMapper {

    //CREATE
    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "gallerist",ignore = true) //will be set in service
    @Mapping(target = "car",ignore = true)// same as above
    @Mapping(target = "customer",ignore = true) //same as above
    SaledCar toEntity(SaledCarRequestDto request);

    //RESPONSE
    @Mapping(target="galleristId",source = "gallerist.id")
    @Mapping(target = "galleristFullName",
            expression = "java(saledCar.getGallerist().getFirstName()+ \" \" +saledCar.getGallerist().getLastName())")
    @Mapping(target = "carId",source = "car.id")
    @Mapping(target = "plate",source = "car.plate")
    @Mapping(target = "brand",source = "car.brand")
    @Mapping(target = "model",source = "car.model")
    @Mapping(target = "productionYear",source = "car.productionYear")
    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "customerFullName",
            expression = "java(saledCar.getCustomer().getFirstName()+\" \"+saledCar.getCustomer().getLastName())")
    @Mapping(target = "customerTckn", source = "customer.tckn")
    SaledCarResponseDto toResponse(SaledCar saledCar);

    //list
    List<SaledCarResponseDto> toResponseList(List<SaledCar> saledCars);
}
