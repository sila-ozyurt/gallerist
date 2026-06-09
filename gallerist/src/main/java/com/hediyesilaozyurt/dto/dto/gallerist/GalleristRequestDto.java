package com.hediyesilaozyurt.dto.dto.gallerist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GalleristRequestDto {

    @NotBlank(message = "firstname cannot be null, empty or blank")
    private String firstName;

    @NotBlank(message = "lastname cannot be null, empty or blank")
    private String lastName;

    @NotNull(message = "address cannot be null")
    private Long addressId;
}
