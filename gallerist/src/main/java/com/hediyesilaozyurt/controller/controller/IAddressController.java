package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.address.AddressRequestDto;
import com.hediyesilaozyurt.dto.dto.address.AddressResponseDto;
import com.hediyesilaozyurt.dto.dto.address.AddressUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.base.RootEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAddressController {




    ResponseEntity<RootEntity<AddressResponseDto>>  save(AddressRequestDto address);
    ResponseEntity<RootEntity<Void>> delete(Long Id);
    ResponseEntity<RootEntity<AddressResponseDto>> findById(Long id);
    ResponseEntity<RootEntity<AddressResponseDto>> update(Long id, AddressUpdateDto dto);
    ResponseEntity<RootEntity<PageResponse<AddressResponseDto> >> findAll(Pageable pageable);

    ResponseEntity<RootEntity<List<AddressResponseDto>>> getMyAddresses(User user);
    ResponseEntity<RootEntity<AddressResponseDto>> addMyAddress(User user,AddressRequestDto request);
    ResponseEntity<RootEntity<AddressResponseDto>> updateMyAddress(User user,Long id,AddressUpdateDto request);
    ResponseEntity<RootEntity<Void>> deleteMyAddress(User user,Long id);


}
