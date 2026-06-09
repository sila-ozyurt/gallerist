package com.hediyesilaozyurt.dto.dto.saledCar;

import com.hediyesilaozyurt.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaledCarResponseDto extends BaseDto {

    // Gallerist
    private Long galleristId;
    private String galleristFullName;       // firstName + lastName

    // Car
    private Long carId;
    private String plate;
    private String brand;
    private String model;
    private int productionYear;

    // Customer
    private Long customerId;
    private String customerFullName;        // firstName + lastName
    private String customerTckn;

    private BigDecimal price;

}
