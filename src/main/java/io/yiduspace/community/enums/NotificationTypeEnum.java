package io.yiduspace.community.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");
    private Integer type;
    private String typeName;

    NotificationTypeEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public static String typeOf(Integer type){
        for(NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()){
            if(notificationTypeEnum.getType() == type){
                return notificationTypeEnum.getTypeName();
            }
        }
        return "";
    }


}
