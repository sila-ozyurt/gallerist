package com.hediyesilaozyurt.dto.dto.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hediyesilaozyurt.entities.enums.CarStatusType;
import com.hediyesilaozyurt.entities.enums.CurrencyType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestDto {

    @NotBlank(message = "plate cannot be null, empty or blank")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{1,3}[0-9]{2,4}",message = "enter a valid plate")
    private String plate;

    @NotBlank(message = "cannot be null, empty or blank")
    private String brand;

    @NotBlank(message = "cannot be null, empty or blank")
    private String model;

    @NotNull(message = "cannot be null")
    @Min(value = 1886,message = "invalid production year, cannot be below 1886")
    @Max(value = 2026,message = "invalid production year")
    private int productionYear;

    @PositiveOrZero
    @Digits(integer = 17,fraction = 2,message = "invalid price format")
    private BigDecimal price;

    @NotNull(message = "cannot be null")
    private CurrencyType currencyType;

    @PositiveOrZero
    @Digits(integer = 17,fraction = 2,message = "invalid damageprice format")
    private BigDecimal damagePrice;

    @NotNull(message = "cannot be null")
    private CarStatusType carStatusType;
}
