package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.gallerist.GalleristDetailResponseDto;
import com.hediyesilaozyurt.dto.dto.gallerist.GalleristRequestDto;
import com.hediyesilaozyurt.dto.dto.gallerist.GalleristResponseDto;
import com.hediyesilaozyurt.dto.dto.gallerist.GalleristUpdateDto;
import com.hediyesilaozyurt.entities.entities.Gallerist;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",uses = {AddressMapper.class,GalleristCarMapper.class})
public interface GalleristMapper {

    //CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Gallerist toEntity(GalleristRequestDto request);

    //Response
    @Mapping(source = "address.fullAddress", target = "fullAddress")
    @Mapping(target = "carCount",expression = "java(gallerist.getCars().size())")
    @Mapping(target="salesCount",expression = "java(gallerist.getSales().size())")
    GalleristResponseDto toResponse(Gallerist gallerist);

    //DETAIL RESPONSE
    @Mapping(target = "salesCount",
            expression = "java(gallerist.getSales().size())")
    GalleristDetailResponseDto toDetailedResponse(Gallerist gallerist);

    //UPDATE
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "sales", ignore = true)
    void updateEntity(GalleristUpdateDto request, @MappingTarget Gallerist gallerist);

    //LIST
    List<GalleristResponseDto> toResponseList(List<Gallerist> gallerists);
}
