package com.hediyesilaozyurt.dto.dto.customer;

import com.hediyesilaozyurt.dto.base.BaseDto;
import com.hediyesilaozyurt.dto.dto.account.AccountResponseDto;
import com.hediyesilaozyurt.dto.dto.address.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDetailResponseDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String tckn;

    private LocalDate birthOfDate;

    private List<AddressResponseDto> addresses;
    private List<AccountResponseDto> accounts;
    private int salesCount;
}
