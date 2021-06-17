package io.yiduspace.community.exception;

import lombok.Data;

@Data
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(CustomizeErrorCode customizeErrorCode){
        this.code = customizeErrorCode.getCode();
        this.message = customizeErrorCode.getMessage();
    }
}
