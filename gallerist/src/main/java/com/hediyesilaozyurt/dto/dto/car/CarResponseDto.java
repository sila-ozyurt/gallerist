package com.hediyesilaozyurt.dto.dto.car;

import com.hediyesilaozyurt.dto.base.BaseDto;
import com.hediyesilaozyurt.entities.enums.CarStatusType;
import com.hediyesilaozyurt.entities.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDto extends BaseDto {

    private String plate;
    private String brand;
    private String model;
    private int productionYear;
    private BigDecimal price;
    private CurrencyType currencyType;
    private BigDecimal damagePrice;
    private CarStatusType carStatusType;
    //the number of gallerists
    private int galleristCount;
}
