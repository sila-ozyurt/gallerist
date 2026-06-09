package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.galleristCar.GalleristCarRequestDto;
import com.hediyesilaozyurt.dto.dto.galleristCar.GalleristCarResponseDto;
import com.hediyesilaozyurt.dto.dto.galleristCar.GalleristCarUpdateDto;
import com.hediyesilaozyurt.entities.entities.GalleristCar;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GalleristCarMapper {

    //CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target="gallerist",ignore = true) //will be set in service
    @Mapping(target = "car",ignore = true) //will be set in service
    GalleristCar toEntity(GalleristCarRequestDto request);

    //RESPONSE
    @Mapping(source = "gallerist.id",target="galleristId")
    @Mapping(target = "galleristFullName",
            expression = "java(galleristCar.getGallerist().getFirstName() + \" \" + galleristCar.getGallerist().getLastName())")
    @Mapping(source = "car.id",target = "carId")
    @Mapping(source = "car.plate",target = "plate")
    @Mapping(source = "car.brand",target="brand")
    @Mapping(source = "car.model",target="model")
    @Mapping(source = "car.productionYear",target="productionYear")
    GalleristCarResponseDto toResponse(GalleristCar galleristCar);

    //UPDATE
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "gallerist",ignore = true)
    @Mapping(target = "car",ignore = true)
    void updateEntity(GalleristCarUpdateDto request,@MappingTarget GalleristCar galleristCar);

    //list
    List<GalleristCarResponseDto> toResponseList(List<GalleristCar> galleristCars);

}


