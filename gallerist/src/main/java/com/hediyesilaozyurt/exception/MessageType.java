package com.hediyesilaozyurt.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
public enum MessageType {
    VALIDATION_FAILED("1000","Invalid data submitted"),
    DATABASE_CONSTRAINT("1001","Database constraint violation"),
    ENTITY_NOT_FOUND("1002","Record not found"),
    INVALID_ARGUMENT_TYPE("1003","Invalid argument type"),
    INTERNAL_SERVER_ERROR("1004","Internal server error occured"),
    UNAUTHORIZED_ACCESS("1005", "Unauthorized access"),
    DUPLICATE_ENTRY("1006", "Record already exists"),

    private final String code;

    private final String message;

    MessageType(String code,String message){
        this.code=code;
        this.message=message;
    }

}


