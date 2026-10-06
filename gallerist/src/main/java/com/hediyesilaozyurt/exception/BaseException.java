package com.hediyesilaozyurt.exception;

public class BaseException extends RuntimeException{

    private final MessageType messageType;

    public BaseException(ErrorMessage errorMessage){
        super(errorMessage.prepareErrorMessage());
        this.messageType=errorMessage.getMessageType();
    }

    public MessageType getMessageType(){
        return messageType;
    }

}
