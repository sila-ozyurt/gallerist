package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.base.RestBaseController;
import com.hediyesilaozyurt.controller.controller.ICustomerController;
import com.hediyesilaozyurt.dto.dto.customer.CustomerRequestDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerResponseDto;
import com.hediyesilaozyurt.dto.dto.customer.CustomerUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageRequest;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.base.RootEntity;
import com.hediyesilaozyurt.services.services.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/customer")
@RequiredArgsConstructor
public class CustomerControllerImpl extends RestBaseController implements ICustomerController {

    private final ICustomerService customerService;

    //-----------admin---------------------

    @PostMapping(path="/save")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<CustomerResponseDto>> save(@RequestBody @Valid CustomerRequestDto request) {
        return respond(HttpStatus.CREATED,customerService.save(request));
    }

    @DeleteMapping(path="/delete/{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<Void>> delete(@PathVariable(name="id") Long id) {
        customerService.delete(id);
        return respond(HttpStatus.NO_CONTENT,null);
    }

    @GetMapping(path = "/list/{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<CustomerResponseDto>> findById(@PathVariable(name="id") Long id) {
        return respond(HttpStatus.OK,customerService.findById(id));
    }

    @PatchMapping(path ="/update/{id}")
    @Override
    public ResponseEntity<RootEntity<CustomerResponseDto>> update(@PathVariable(name="id") Long id, CustomerUpdateDto request) {
        return respond(HttpStatus.OK,customerService.update(id,request));
    }

    @GetMapping(path = "/list")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<PageResponse<CustomerResponseDto>>> findAll(PageRequest request) {
        return null;
    }


    //---------------customer----------------


    @GetMapping(path="/me")
    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<RootEntity<CustomerResponseDto>> getMe(@AuthenticationPrincipal User currentUser) {
        return respond(HttpStatus.OK,customerService.getMe(currentUser));
    }

    @PatchMapping(path="/update-me")
    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<RootEntity<CustomerResponseDto>> updateMe(@AuthenticationPrincipal User user,
                                                                    @RequestBody @Valid CustomerUpdateDto request) {
        return respond(HttpStatus.OK,customerService.updateMe(user, request));
    }
}
