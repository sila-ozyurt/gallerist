package com.hediyesilaozyurt.dto.dto.gallerist;

import com.hediyesilaozyurt.dto.dto.address.AddressUpdateDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleristUpdateDto {

    private String firstName;

    private String lastName;

    @Valid
    private AddressUpdateDto address;
}
