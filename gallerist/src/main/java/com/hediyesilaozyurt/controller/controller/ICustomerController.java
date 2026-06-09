package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.utils.PageRequest;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.dto.dto.customer.CustomerRequestDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerResponseDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerUpdateDto;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.base.RootEntity;
import org.springframework.http.ResponseEntity;

public interface  ICustomerController {

    //CRUD operations
    //admin below
    public ResponseEntity<RootEntity<CustomerResponseDto>> save(CustomerRequestDto request);
    public ResponseEntity<RootEntity<Void>> delete(Long id);
    public ResponseEntity<RootEntity<CustomerResponseDto>> findById(Long id);
    public ResponseEntity<RootEntity<CustomerResponseDto>> update(Long id, CustomerUpdateDto request);
    public ResponseEntity<RootEntity<PageResponse<CustomerResponseDto>>> findAll(PageRequest request);

    //customer below
    public ResponseEntity<RootEntity<CustomerResponseDto>> getMe(User currentUser);
    public ResponseEntity<RootEntity<CustomerResponseDto>> updateMe(User user, CustomerUpdateDto request);


}
