package io.yiduspace.community.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    //回复人name
    private String replier;
    //回复类型
    private String notificationType;
    //通知的问题title
    private String questionTitle;
    //通知的问题Id
    private Long questionId;
    //通知状态
    private String notificationStatus;
    //创建时间
    private Long gmtCreate;
    //通知Id
    private Long id;


}
