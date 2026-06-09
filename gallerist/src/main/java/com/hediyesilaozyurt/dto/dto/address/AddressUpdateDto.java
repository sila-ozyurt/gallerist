package com.hediyesilaozyurt.dto.dto.address;

import com.hediyesilaozyurt.entities.enums.AddressType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateDto {

    private String country;

    private String city;

    private String district;

    private String neighborhood;

    private String street;

    private String postalCode;

    private String buildingNo;

    private String apartmentNo;

    private AddressType addressType;
}
