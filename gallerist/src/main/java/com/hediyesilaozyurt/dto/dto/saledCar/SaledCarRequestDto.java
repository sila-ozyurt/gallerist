package com.hediyesilaozyurt.dto.dto.saledCar;

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
public class SaledCarRequestDto {

    private Long galleristId;

    private Long carId;

    private Long customerId;

    @NotNull(message = "price cannot be null")
    @PositiveOrZero
    @Digits(integer = 17,fraction = 2,message = "invalid price format")
    private BigDecimal price;

}
