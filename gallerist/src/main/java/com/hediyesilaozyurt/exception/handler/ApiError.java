package com.hediyesilaozyurt.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)   //do not show null values
public class ApiError {

    private LocalDateTime timestamp;

    private String code;  //1001 etc.

    private String message;        // "Record not found"

   /* private String detail;  */       // "Customer not found with id: 123"

    private String path;           // "/api/customers/123"

    private String hostName;       //for microservice

    private List<ValidationError> validationErrors; // Validation error if exists


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ValidationError{

        private String field;

        private String message;
    }
}
