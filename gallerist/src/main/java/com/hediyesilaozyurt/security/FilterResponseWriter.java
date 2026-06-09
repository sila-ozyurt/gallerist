package com.hediyesilaozyurt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hediyesilaozyurt.entities.base.RootEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterResponseWriter {

    private final ObjectMapper objectMapper;

    public void write(HttpServletResponse response,
                      HttpStatus status,
                      String mesage) throws IOException{

        RootEntity<String> body=RootEntity.of(status,mesage);

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(),body);
    }
}
