package com.hediyesilaozyurt.services.services.impl;

import com.hediyesilaozyurt.dto.dto.customer.CustomerRequestDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerResponseDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.entities.Customer;
import com.hediyesilaozyurt.entities.enums.UserStatus;
import com.hediyesilaozyurt.exception.BaseException;
import com.hediyesilaozyurt.exception.ErrorMessage;
import com.hediyesilaozyurt.exception.MessageType;
import com.hediyesilaozyurt.mapper.CustomerMapper;
import com.hediyesilaozyurt.repository.CustomerRepository;
import com.hediyesilaozyurt.repository.UserRepository;
import com.hediyesilaozyurt.services.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public CustomerResponseDto save(CustomerRequestDto request) {
        //if user exists
        User user=userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        "User not found with username: ",
                        MessageType.ENTITY_NOT_FOUND
                )));

        //whether or not user has already a customer
        if(customerRepository.existsByUsername(user.getUsername())){
            throw new BaseException(new ErrorMessage(
                    "This user already has a customer record",
                    MessageType.DUPLICATE_ENTRY
            ));
        }

        Customer customer=customerMapper.toEntity(request);
        customer.setUser(user);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public void delete(Long customerId) {
        Customer customer=customerRepository.findById(customerId).orElseThrow(()->new BaseException(new ErrorMessage(
                "Customer not found with id: ",
                MessageType.ENTITY_NOT_FOUND
        )));

        customerRepository.delete(customer);

    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto findById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponse)
                .orElseThrow(()->new BaseException(new ErrorMessage(
                        "Customer not found with id: ",
                        MessageType.ENTITY_NOT_FOUND
                )));
    }

    @Override
    @Transactional
    public CustomerResponseDto update(Long id, CustomerUpdateDto request) {
        Customer customer=customerRepository.findById(id).orElseThrow(()->new BaseException(new ErrorMessage(
                "customer not found with id:  ",
                MessageType.ENTITY_NOT_FOUND
        )));

        customerMapper.updateEntity(request,customer);
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CustomerResponseDto> findAll(Pageable pageable) {
        Page<Customer> page=customerRepository.findAll(pageable);
        List<CustomerResponseDto> content=customerMapper.toResponseList(page.getContent());

        return new PageResponse<>(content,page);
    }


    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto getMe(User user) {
        return customerRepository.findByUsername(user.getUsername())
                .map(customerMapper::toResponse)
                .orElseThrow(()->new BaseException(new ErrorMessage(
                        "Customer not found",
                        MessageType.ENTITY_NOT_FOUND)));
    }

    @Override
    @Transactional
    public CustomerResponseDto updateMe(User user, CustomerUpdateDto request) {
        Customer customer=customerRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        "Customer not found",
                        MessageType.ENTITY_NOT_FOUND)));

        customerMapper.updateEntity(request,customer);
        customerRepository.save(customer);

        return customerMapper.toResponse(customer);
    }
}


