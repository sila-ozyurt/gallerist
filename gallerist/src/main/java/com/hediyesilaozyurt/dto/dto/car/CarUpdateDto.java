package com.hediyesilaozyurt.dto.dto.car;

import com.hediyesilaozyurt.entities.enums.CarStatusType;
import com.hediyesilaozyurt.entities.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarUpdateDto {

    private String plate;

    private String brand;

    private String model;

    private int productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
