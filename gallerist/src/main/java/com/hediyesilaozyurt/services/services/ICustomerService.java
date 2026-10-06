package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.dto.dto.customer.CustomerRequestDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerResponseDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerUpdateDto;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.entities.Customer;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerService {

    //CRUD OPERATIONS
    public CustomerResponseDto save(CustomerRequestDto customer);
    public void delete(Long customerId);
    public CustomerResponseDto findById(Long id);
    public CustomerResponseDto update(Long id, CustomerUpdateDto customer);
    public PageResponse<CustomerResponseDto> findAll(Pageable pageable);

    //Customer Self Operations
    public CustomerResponseDto getMe(User user);
    public CustomerResponseDto updateMe(User user,CustomerUpdateDto request);

    Customer findEntityById(Long id);
    Customer findEntityByUsername(String username);
    void saveEntity(Customer customer);

}
