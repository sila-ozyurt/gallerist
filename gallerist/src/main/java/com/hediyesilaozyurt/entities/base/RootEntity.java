package com.hediyesilaozyurt.entities.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class RootEntity<T> {
    // RootEntity<UserResponse>, RootEntity<List<CarResponseDto>>

    private int status;
    // HTTP status — 200, 404, 500 etc.

    private T payload;
    //the actual returned data- DTO-list-String

    public static <T> RootEntity<T> of(HttpStatus status,T payload){
        RootEntity<T> rootEntity=new RootEntity<>();
        rootEntity.setStatus(status.value());
        rootEntity.setPayload(payload);

        return rootEntity;
    }

}
