package com.hediyesilaozyurt.dto.dto.account;

import com.hediyesilaozyurt.entities.enums.CurrencyType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

    @NotBlank(message = "account no cannot be null, empty or blank")
    @Size(min = 10, max=26,message = "account length must be between 10-26 chars")
    private String accountNo;

    @NotBlank(message = "iban cannot be null, empty or blank")
    @Pattern(regexp = "^TR[0-9]{24}$",message = "enter a valid iban format")
    private String iban;

    @PositiveOrZero
    @NotNull(message = "balance cannot be null")
    @Digits(integer=17,fraction = 2,message = "invalid balance format")
    private BigDecimal balance;

    @NotNull(message = "currency type cannot be null")
    private CurrencyType currencyType;

    private Long customerId;

}
