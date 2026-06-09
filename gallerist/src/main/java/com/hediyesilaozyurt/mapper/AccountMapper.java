package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.account.AccountRequestDto;
import com.hediyesilaozyurt.dto.dto.account.AccountResponseDto;
import com.hediyesilaozyurt.dto.dto.account.AccountUpdateDto;
import com.hediyesilaozyurt.entities.entities.Account;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel ="spring")
public interface AccountMapper {

    //CREATE
    //REQUEST TO ENTITY
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "customer",ignore = true)
    Account toEntity(AccountRequestDto request);

    //RESPONSE
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(target="customerFullName",
    expression = "java(account.getCustomer().getFirstName()+ ' ' +account.getCustomer().getLastName())")
    AccountResponseDto toResponse(Account account);

    //UPDATE
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "accountNo", ignore = true)
    @Mapping(target = "iban", ignore = true)
    @Mapping(target = "currencyType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(AccountUpdateDto request, @MappingTarget Account account);

    //LIST
    List<AccountResponseDto> toResponseList(List<Account> accounts);

}
