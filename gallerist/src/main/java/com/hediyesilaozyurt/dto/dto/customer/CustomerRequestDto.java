package com.hediyesilaozyurt.dto.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    @NotBlank(message = "firstname cannot be null, empty or blank")
    private String firstName;

    @NotBlank(message = "lastname cannot be null, empty or blank")
    private String lastName;

    @NotBlank(message = "tckn cannot be null, empty or blank")
    @Pattern(regexp = "^[1-9][0-9]{10}$",message = "enter a valid tckn")
    private String tckn;

    @NotNull(message = "cannot be null")
    @Past(message = "invalid birth of date")
    private LocalDate birthOfDate;

    @NotNull(message = "userId cannot be null")
    private String username;
}
