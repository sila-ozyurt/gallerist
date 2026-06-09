package com.hediyesilaozyurt.dto.dto.galleristCar;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCarRequestDto {

    @NotNull(message = "gallerist id cannot be null")
    private Long galleristId;

    @NotNull(message = "car id cannot be null")
    private Long carId;

    @PositiveOrZero
    @Digits(integer = 17,fraction = 2,message = "invalid price format")
    @NotNull(message = "price cannot be null")
    private BigDecimal price;
}
