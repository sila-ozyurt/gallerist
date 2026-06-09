package com.hediyesilaozyurt.controller.base;

import com.hediyesilaozyurt.entities.base.RootEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class RestBaseController {

    protected <T> ResponseEntity<RootEntity<T>> respond(HttpStatus status,T payload){
        RootEntity<T> body=RootEntity.of(status,payload);
        return ResponseEntity.status(status).body(body);
    }


}
