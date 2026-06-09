package com.hediyesilaozyurt.dto.dto.gallerist;

import com.hediyesilaozyurt.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleristResponseDto extends BaseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String fullAddress;

    private int carCount;
    private int salesCount;
}
