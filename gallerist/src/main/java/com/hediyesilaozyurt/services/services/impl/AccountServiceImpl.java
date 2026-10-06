package com.hediyesilaozyurt.services.services.impl;

import com.hediyesilaozyurt.dto.dto.account.AccountRequestDto;
import com.hediyesilaozyurt.dto.dto.account.AccountResponseDto;
import com.hediyesilaozyurt.dto.dto.account.AccountUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.entities.Account;
import com.hediyesilaozyurt.entities.entities.Customer;
import com.hediyesilaozyurt.exception.BaseException;
import com.hediyesilaozyurt.exception.ErrorMessage;
import com.hediyesilaozyurt.exception.MessageType;
import com.hediyesilaozyurt.mapper.AccountMapper;
import com.hediyesilaozyurt.repository.AccountRepository;
import com.hediyesilaozyurt.services.services.IAccountService;
import com.hediyesilaozyurt.services.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ICustomerService customerService;



    @Override
    @Transactional
    public AccountResponseDto save(AccountRequestDto dto) {

        Customer customer=customerService.findEntityById(dto.getCustomerId());
        Account account=accountMapper.toEntity(dto);
        customer.addAccount(account);
        customerService.saveEntity(customer);

        return accountMapper.toResponse(account);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponseDto findById(Long id) {

        Account account=accountRepository.findById(id)
                .orElseThrow(()->new BaseException(new ErrorMessage(
                                "Account not found with id: " +id,
                        MessageType.ENTITY_NOT_FOUND
                )));
        return accountMapper.toResponse(account);
    }

    @Override
    @Transactional
    public Void delete(Long id) {
        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                new ErrorMessage(
                        "Account not found with id: " + id,
                        MessageType.ENTITY_NOT_FOUND
                )));

        Customer customer=account.getCustomer();
        customer.removeAccount(account);
        customerService.saveEntity(customer);
        return null;
    }

    @Override
    @Transactional
    public AccountResponseDto update(Long id, AccountUpdateDto dto) {
        Account account=accountRepository.findById(id).orElseThrow(() -> new BaseException(
                new ErrorMessage(
                        "Account not found with id: " + id,
                        MessageType.ENTITY_NOT_FOUND
                )));

        accountMapper.updateEntity(dto,account);

        return accountMapper.toResponse(account);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<AccountResponseDto> findAll(Pageable pageable) {
        Page<Account> page=accountRepository.findAll(pageable);
        List<AccountResponseDto> content=accountMapper.toResponseList(page.getContent());

        return new PageResponse<>(content,page);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDto> getMyAccounts(User user) {
        Customer customer=customerService.findEntityByUsername(user.getUsername());
        List<Account> accounts=customer.getAccounts();
        return accountMapper.toResponseList(accounts);
    }

    @Override
    @Transactional
    public AccountResponseDto addMyAccount(User user, AccountRequestDto dto) {
        Customer customer=customerService.findEntityByUsername(user.getUsername());
        Account account=accountMapper.toEntity(dto);
        customer.addAccount(account);
        customerService.saveEntity(customer);

        return accountMapper.toResponse(account);
    }

    @Override
    @Transactional
    public AccountResponseDto updateMyAccount(User user, Long id, AccountUpdateDto dto) {
        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(
                                "Account not found with id: " + id,
                                MessageType.ENTITY_NOT_FOUND
                        )));

        if(!account.getCustomer().getUser().getUsername().equals(user.getUsername())){
            throw new BaseException(
                    new ErrorMessage(
                            "You are not authorized",
                            MessageType.UNAUTHORIZED_ACCESS
                    )
            );
        }

        accountMapper.updateEntity(dto,account);
        return accountMapper.toResponse(account);
    }

    @Override
    @Transactional
    public Void deleteMyAccount(User user, Long id) {
        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(
                                "Account not found with id: " + id,
                                MessageType.ENTITY_NOT_FOUND
                        )
                ));

        if(!account.getCustomer().getUser().getUsername().equals(user.getUsername())){
            throw new BaseException(
                    new ErrorMessage(
                            "You are not authorized",
                            MessageType.UNAUTHORIZED_ACCESS
                    )
            );

        }
        Customer customer=account.getCustomer();
        customer.removeAccount(account);
        customerService.saveEntity(customer);
    }
}
