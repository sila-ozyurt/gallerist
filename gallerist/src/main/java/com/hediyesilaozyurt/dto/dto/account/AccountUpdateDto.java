package com.hediyesilaozyurt.dto.dto.account;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateDto {

    @NotNull(message = "balance cannot be null")
    @PositiveOrZero
    @Digits(integer = 17,fraction = 2,message = "invalid balance format")
    private BigDecimal balance;
}
