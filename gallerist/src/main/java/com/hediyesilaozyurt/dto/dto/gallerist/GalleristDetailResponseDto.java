package com.hediyesilaozyurt.dto.dto.gallerist;

import com.hediyesilaozyurt.dto.base.BaseDto;
import com.hediyesilaozyurt.dto.dto.address.AddressResponseDto;
import com.hediyesilaozyurt.dto.dto.galleristCar.GalleristCarResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleristDetailResponseDto extends BaseDto {

    private String firstName;

    private String lastName;

    private AddressResponseDto address;

    private List<GalleristCarResponseDto> cars;

    private int salesCount;

}
