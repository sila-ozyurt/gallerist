package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.account.AccountRequestDto;
import com.hediyesilaozyurt.dto.dto.account.AccountResponseDto;
import com.hediyesilaozyurt.dto.dto.account.AccountUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.base.RootEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IAccountController {

    ResponseEntity<RootEntity<AccountResponseDto> save(AccountRequestDto dto);
    ResponseEntity<RootEntity<Void> delete(Long id);
    ResponseEntity<RootEntity<AccountResponseDto> update(Long id, AccountUpdateDto dto);
    ResponseEntity<RootEntity<AccountResponseDto> findById(Long id);
    PageResponse<AccountResponseDto> findAll(Pageable pageable);


}
