package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.account.AccountRequestDto;
import com.hediyesilaozyurt.dto.dto.account.AccountResponseDto;
import com.hediyesilaozyurt.dto.dto.account.AccountUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {

    AccountResponseDto save(AccountRequestDto dto);
    AccountResponseDto findById(Long id);
    Void delete(Long id);
    AccountResponseDto update(Long id, AccountUpdateDto dto);
    PageResponse<AccountResponseDto> findAll(Pageable pageable);

        //customer
    List<AccountResponseDto> getMyAccounts(User user);
    AccountResponseDto addMyAccount(User user, AccountRequestDto dto);
    AccountResponseDto updateMyAccount(User user,Long id, AccountUpdateDto dto);
    Void deleteMyAccount(User user, Long id);
}
