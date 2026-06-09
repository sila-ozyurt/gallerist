package com.hediyesilaozyurt.dto.dto.galleristCar;

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
public class GalleristCarUpdateDto {

    @PositiveOrZero
    @NotNull(message = "price cannot be null ")
    @Digits(integer = 17, fraction = 2, message ="invalid price format")
    private BigDecimal price;
}

