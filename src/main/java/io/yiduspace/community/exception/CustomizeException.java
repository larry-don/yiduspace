package io.yiduspace.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(String message){
        this.message = message;
    }

    public CustomizeException(CustomizeErrorCode customizeErrorCode){
        this.message = customizeErrorCode.getMessage();
    }
}
