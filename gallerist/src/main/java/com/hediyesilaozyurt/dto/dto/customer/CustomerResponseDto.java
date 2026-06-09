package com.hediyesilaozyurt.dto.dto.customer;

import com.hediyesilaozyurt.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String tckn;

    private LocalDate birthOfDate;

    private int addressCount;
    private int accountCount;
    private int salesCount;
}
