package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.address.AddressRequestDto;
import com.hediyesilaozyurt.dto.dto.address.AddressResponseDto;
import com.hediyesilaozyurt.dto.dto.address.AddressUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAddressService {

    //CRUD
    AddressResponseDto save(AddressRequestDto address);
    void delete(Long Id);
    AddressResponseDto findById(Long id);
    AddressResponseDto update(Long id,AddressUpdateDto dto);
    PageResponse<AddressResponseDto> findAll(Pageable pageable);

    List<AddressResponseDto> getMyAddresses(User user);
    AddressResponseDto addMyAddress(User user,AddressRequestDto request);
    AddressResponseDto updateMyAddress(User user,Long id,AddressUpdateDto request);
    void deleteMyAddress(User user,Long id);

}
