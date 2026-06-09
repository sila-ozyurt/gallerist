package com.hediyesilaozyurt.dto.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hediyesilaozyurt.entities.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {

    @NotBlank(message = "country cannot be null, empty or blank")
    private String country;

    @NotBlank(message = " city cannot be null, empty or blank")
    private String city;

    @NotBlank(message = "district cannot be null, empty or blank")
    private String district;

    @NotBlank(message = "neighborhood cannot be null, empty or blank")
    private String neighborhood;

    @NotBlank(message = "street cannot be null, empty or blank")
    private String street;

    @NotBlank(message = "postal code cannot be null, empty or blank")
    private String postalCode;

    @NotBlank(message = "building no cannot be null, empty or blank")
    private String buildingNo;

    @NotBlank(message = "apartment no cannot be null, empty or blank")
    private String apartmentNo;

    @NotNull(message = "address type cannot be null")
    private AddressType addressType;

    @NotNull(message = "customer id cannot be null")
    private Long customerId;
}
