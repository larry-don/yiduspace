package io.yiduspace.community.enums;

public enum NotificationStatusEnum {
    READ(1,"已读"),
    UNREAD(2,"未读");

    private Integer statusId;
    private String status;

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatus() {
        return status;
    }

    NotificationStatusEnum(Integer statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public static String statusOf(Integer statusId){
        for(NotificationStatusEnum notificationStatusEnum : NotificationStatusEnum.values()){
            if(notificationStatusEnum.getStatusId() == statusId){
                return notificationStatusEnum.getStatus();
            }
        }
        return "";
    }
}
