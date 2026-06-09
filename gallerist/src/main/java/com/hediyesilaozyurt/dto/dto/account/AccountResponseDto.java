package com.hediyesilaozyurt.dto.dto.account;

import com.hediyesilaozyurt.dto.base.BaseDto;
import com.hediyesilaozyurt.entities.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto extends BaseDto {

    private String accountNo;
    private String iban;
    private BigDecimal balance;
    private CurrencyType currencyType;
    private Long customerId;
    private String customerFullName;


}
