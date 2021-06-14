package io.yiduspace.community.exception;

public enum CustomizeErrorCode{
    QUESTION_NOT_FOUND("你找的问题走丢了，要不换个试试");

    private String message;

    public String getMessage() {
        return message;
    }
    private CustomizeErrorCode(String message){
        this.message = message;
    }

}
