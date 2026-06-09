package com.hediyesilaozyurt.dto.dto.customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateDto {

    private String firstName;

    private String lastName;

    private String tckn;

    @Past(message = "invalid birth of date")
    private LocalDate birthOfDate;
}
