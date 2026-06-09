package com.hediyesilaozyurt.dto.utils;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    @Min(value = 0, message = "Page number cannot be negative")
    private int pageNumber;

    @Min(value = 1, message = "Page size must be at least 1")
    private int pageSize;

    @NotBlank(message = "Column name cannot be null, empty or blank")
    private String columnName="id";

    private boolean asc;
}
