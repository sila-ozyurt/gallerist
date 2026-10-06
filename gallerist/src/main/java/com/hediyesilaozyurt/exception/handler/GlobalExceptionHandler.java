package com.hediyesilaozyurt.exception.handler;

import com.hediyesilaozyurt.exception.BaseException;
import com.hediyesilaozyurt.exception.MessageType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //@Value("${spring.application.name:gallery-service}")
    private String applicationName="gallery-management";

    //handle custom exceptions
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException exception, HttpServletRequest request){

        log.error("Base exception occured: {}",exception.getMessage(),exception);

        MessageType messageType=exception.getMessageType();
        HttpStatus status=determineHtppStatus(messageType);

        ApiError response= ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(messageType.getCode())
                .message(messageType.getMessage())
                //.detail(exception.getMessage())
                .path(request.getRequestURI())
                .hostName(applicationName)
                .build();

        return ResponseEntity.status(status).body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException exception,
                                                              HttpServletRequest request){
        log.error("Validation Error Occured : {}",exception.getMessage());

        List<ApiError.ValidationError> validationErrorList=exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->ApiError.ValidationError.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ApiError apiError= ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(MessageType.VALIDATION_FAILED.getCode())
                .message(MessageType.VALIDATION_FAILED.getMessage())
                .path(request.getRequestURI())
                .hostName(applicationName)
                .validationErrors(validationErrorList)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException exception,
                                                                   HttpServletRequest request){
        log.error("Illegal argument: {}",exception.getMessage());

        ApiError response= ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(MessageType.INVALID_ARGUMENT_TYPE.getCode())
                .message(MessageType.INVALID_ARGUMENT_TYPE.getMessage())
                .path(request.getRequestURI())
                .hostName(applicationName)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException exception,
                                                                 HttpServletRequest request){
        log.error("Database constraint violation: {}",exception.getMessage(),exception);

        ApiError apiError= ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(MessageType.DATABASE_CONSTRAINT.getCode())
                .message(MessageType.DATABASE_CONSTRAINT.getMessage())
                .path(request.getRequestURI())
                .hostName(applicationName)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralException(Exception exception,
                                                           HttpServletRequest request){
        log.error("Unexpected error occured: {}",exception.getMessage());

        ApiError response=ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(MessageType.INTERNAL_SERVER_ERROR.getCode())
                .message(MessageType.INTERNAL_SERVER_ERROR.getMessage())
                .path(request.getRequestURI())
                .hostName(applicationName)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    //determine http status according to message type for baseexception
    private HttpStatus determineHtppStatus(MessageType messageType){
        return switch (messageType){
            case ENTITY_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case DUPLICATE_ENTRY -> HttpStatus.CONFLICT;
            case VALIDATION_FAILED, INVALID_ARGUMENT_TYPE -> HttpStatus.BAD_REQUEST;
            case UNAUTHORIZED_ACCESS -> HttpStatus.UNAUTHORIZED;
            case DATABASE_CONSTRAINT -> HttpStatus.CONFLICT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
