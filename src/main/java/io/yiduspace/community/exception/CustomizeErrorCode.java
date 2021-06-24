package io.yiduspace.community.exception;

public enum CustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你找的问题走丢了，要不换个试试"),
    No_login(2002,"您当前尚未登录，请登录"),
    TARGET_PARAM_NOT_FOUND(2003, "未选中任何问题或评论进行回复"),
    SYS_ERROR(2004, "服务冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了，要不要换个试试？"),
    CONTENT_IS_EMPTY(2007,"回复内容不能为空"),
    NOTIFICATION_NOT_FOUND(2008,"消息走丢了");

    private Integer code;
    private String message;
    public Integer getCode(){
        return code;
    }
    public String getMessage() {
        return message;
    }
    CustomizeErrorCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }


}
