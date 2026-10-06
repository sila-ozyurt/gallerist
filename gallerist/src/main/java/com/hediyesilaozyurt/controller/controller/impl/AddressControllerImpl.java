package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.base.RestBaseController;
import com.hediyesilaozyurt.controller.controller.IAddressController;
import com.hediyesilaozyurt.dto.dto.address.AddressRequestDto;
import com.hediyesilaozyurt.dto.dto.address.AddressResponseDto;
import com.hediyesilaozyurt.dto.dto.address.AddressUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.base.RootEntity;
import com.hediyesilaozyurt.services.services.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/address")
public class AddressControllerImpl extends RestBaseController implements IAddressController {

    @Autowired
    private IAddressService addressService;

    @Override
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<AddressResponseDto>>  save(@RequestBody @Valid AddressRequestDto address) {
        return respond(HttpStatus.CREATED,addressService.save(address));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RootEntity<Void>> delete(@PathVariable Long id) {
        addressService.delete(id);
        return respond(HttpStatus.NO_CONTENT,null);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public ResponseEntity<RootEntity<AddressResponseDto>>  findById(@PathVariable Long id) {
        return respond(HttpStatus.OK,addressService.findById(id));
    }

    @Override
    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<AddressResponseDto>>  update(@PathVariable Long id, @RequestBody @Valid AddressUpdateDto dto) {
        return respond(HttpStatus.OK,addressService.update(id,dto));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<RootEntity<PageResponse<AddressResponseDto>>> findAll(Pageable pageable) {
        return respond(HttpStatus.OK,addressService.findAll(pageable));
    }




    @GetMapping("/my-addresses")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Override
    public ResponseEntity<RootEntity<List<AddressResponseDto>>> getMyAddresses(@AuthenticationPrincipal User user) {
        return respond(HttpStatus.OK,addressService.getMyAddresses(user));
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("my-addresses/add")
    public ResponseEntity<RootEntity<AddressResponseDto>>  addMyAddress(@AuthenticationPrincipal User user, @RequestBody @Valid AddressRequestDto request) {
        return respond(HttpStatus.CREATED,addressService.addMyAddress(user,request));
    }

    @Override
    @PreAuthorize("hasRole(!CUSTOMER')")
    @PatchMapping("/my-addresses/update/{id}")
    public ResponseEntity<RootEntity<AddressResponseDto>>  updateMyAddress(@AuthenticationPrincipal User user, @PathVariable Long id,@RequestBody @Valid  AddressUpdateDto request) {
        return respond(HttpStatus.OK,addressService.updateMyAddress(user,id,request));
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/my-addresses/delete/{id}")
    public ResponseEntity<RootEntity<Void>> deleteMyAddress(@AuthenticationPrincipal User user, @PathVariable Long id) {
        addressService.deleteMyAddress(user,id);
        return respond(HttpStatus.NO_CONTENT,null);
    }



}
