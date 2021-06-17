package io.yiduspace.community.enums;


public enum CommentTypeEnum {
    // 1代表问题，2代表评论
    QUESTION(1),
    COMMENT(2);
    private Integer type;
    public Integer getType(){
        return type;
    }
    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public static boolean isExistType(Integer type){
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }
}
