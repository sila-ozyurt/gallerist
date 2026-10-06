package com.hediyesilaozyurt.services.services.impl;


import com.hediyesilaozyurt.dto.dto.address.AddressRequestDto;
import com.hediyesilaozyurt.dto.dto.address.AddressResponseDto;
import com.hediyesilaozyurt.dto.dto.address.AddressUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.entities.Address;
import com.hediyesilaozyurt.entities.entities.Customer;
import com.hediyesilaozyurt.exception.BaseException;
import com.hediyesilaozyurt.exception.ErrorMessage;
import com.hediyesilaozyurt.exception.MessageType;
import com.hediyesilaozyurt.mapper.AddressMapper;
import com.hediyesilaozyurt.repository.AddressRepository;
import com.hediyesilaozyurt.services.services.IAddressService;
import com.hediyesilaozyurt.services.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private ICustomerService customerService;

    //ADMIN
    @Override
    @Transactional
    public AddressResponseDto save(AddressRequestDto dto ) {
        Customer customer=customerService.findEntityById(dto.getCustomerId());

        Address address=addressMapper.toEntity(dto);
        customer.addAddress(address);
        customerService.saveEntity(customer);

        return addressMapper.toResponse(address);
    }

    //ADMIN
    @Override
    @Transactional
    public void delete(Long id) {

        Address address=addressRepository.findById(id)
                .orElseThrow(()->new BaseException(new ErrorMessage(
                        "address not found with id: ",
                        MessageType.ENTITY_NOT_FOUND
                )));

        Customer customer=address.getCustomer();
        customer.removeAddress(address);
        customerService.saveEntity(customer);
    }

    //Admin
    @Override
    @Transactional(readOnly = true)
    public AddressResponseDto findById(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::toResponse)
                .orElseThrow(()->new BaseException(new ErrorMessage(
                        "address not found with id: ",
                        MessageType.ENTITY_NOT_FOUND
                )));
    }

    //ADMIN
    @Override
    public AddressResponseDto update(Long id,AddressUpdateDto dto) {
        Address address=addressRepository.findById(id).orElseThrow(()->new BaseException(new ErrorMessage(
                "Address not found with id: ",
                MessageType.ENTITY_NOT_FOUND
        )));

        addressMapper.updateEntity(dto,address);
        return addressMapper.toResponse(address);
    }

    //Admin
    @Override
    @Transactional(readOnly = true)
    public PageResponse<AddressResponseDto> findAll(Pageable pageable) {
        Page<Address> page=addressRepository.findAll(pageable);
        List<AddressResponseDto> content=addressMapper.toResponseList(page.getContent());
        return new PageResponse<>(content,page);
    }

    //Customer
    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDto> getMyAddresses(User user) {
        Customer customer=customerService.findEntityByUsername(user.getUsername());
        return addressMapper.toResponseList(customer.getAddresses());
    }

    //Customer
    @Override
    public AddressResponseDto addMyAddress(User user, AddressRequestDto request) {
        Customer customer=customerService.findEntityByUsername(user.getUsername());
        Address address=addressMapper.toEntity(request);

        customer.addAddress(address);
        customerService.saveEntity(customer);

        return addressMapper.toResponse(address);
    }

    //Customer
    @Override
    @Transactional
    public AddressResponseDto updateMyAddress(User user,Long id, AddressUpdateDto request) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        "Address not found with id: ",
                        MessageType.ENTITY_NOT_FOUND
                )));

        if (!address.getCustomer().getUser().equals(user)){
            throw new BaseException(new ErrorMessage(
               "You are not authorized",
               MessageType.UNAUTHORIZED_ACCESS
            ));
        }
        addressMapper.updateEntity(request,address);
        return addressMapper.toResponse(address);
    }

    //Customer
    @Override
    public void deleteMyAddress(User user, Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        "Address not found with id: ",
                        MessageType.ENTITY_NOT_FOUND
                )));

        if(!address.getCustomer().getUser().equals(user)){
            throw new BaseException(new ErrorMessage(
                    "You are not authorized",
                    MessageType.UNAUTHORIZED_ACCESS
            ));
        }

        Customer customer=address.getCustomer();
        customer.removeAddress(address);
        customerService.saveEntity(customer);

    }
}
