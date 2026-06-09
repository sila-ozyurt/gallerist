package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.car.CarRequestDto;
import com.hediyesilaozyurt.dto.dto.car.CarResponseDto;
import com.hediyesilaozyurt.dto.dto.car.CarUpdateDto;
import com.hediyesilaozyurt.entities.entities.Car;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    //CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "gallerists",ignore = true)  //weill be set in service
    Car toEntity(CarRequestDto request);

    //RESPONSE
    @Mapping(target = "galleristCount",expression ="java(car.getGallerists().size())" )
    CarResponseDto toResponse(Car car);

    //UPDATE
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gallerists", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CarUpdateDto request,@MappingTarget Car car);

    //LIST
    List<CarResponseDto> toResponseList(List<Car> cars);

}
