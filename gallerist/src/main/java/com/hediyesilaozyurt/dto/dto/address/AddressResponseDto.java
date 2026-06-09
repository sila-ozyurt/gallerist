package com.hediyesilaozyurt.dto.dto.address;

import com.hediyesilaozyurt.dto.base.BaseDto;
import com.hediyesilaozyurt.entities.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto extends BaseDto {

    private String country;

    private String city;

    private String district;

    private String neighborhood;

    private String street;

    private String postalCode;

    private String buildingNo;

    private String apartmentNo;

    private AddressType addressType;

    private String fullAddress;

    private Long customerId;

}
