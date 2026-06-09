package com.hediyesilaozyurt.dto.dto.galleristCar;

import com.hediyesilaozyurt.dto.base.BaseDto;
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
public class GalleristCarResponseDto extends BaseDto {

    //gallerist
    private Long galleristId;
    private String galleristFullName;

    // Car
    private Long carId;
    private String plate;
    private String brand;
    private String model;
    private int productionYear;

    private BigDecimal price;
}
