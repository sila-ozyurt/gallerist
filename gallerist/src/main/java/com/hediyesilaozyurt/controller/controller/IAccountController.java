package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.account.AccountRequestDto;
import com.hediyesilaozyurt.dto.dto.account.AccountResponseDto;
import com.hediyesilaozyurt.dto.dto.account.AccountUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.base.RootEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccountController {

    ResponseEntity<RootEntity<AccountResponseDto>> save(AccountRequestDto dto);
    ResponseEntity<RootEntity<AccountResponseDto>>findById(Long id);
    ResponseEntity<RootEntity<Void>> delete(Long id);
    ResponseEntity<RootEntity<AccountResponseDto>> update(Long id, AccountUpdateDto dto);
    ResponseEntity<RootEntity<PageResponse<AccountResponseDto>>> findAll(Pageable pageable);


    ResponseEntity<RootEntity<List<AccountResponseDto>>> getMyAccounts(User user);
    ResponseEntity<RootEntity<AccountResponseDto>> addMyAccount(User user, AccountRequestDto dto);
    ResponseEntity<RootEntity<AccountResponseDto>> updateMyAccount(User user,Long id, AccountUpdateDto dto);
    ResponseEntity<RootEntity<Void>> deleteMyAccount(User user, Long id);
}
