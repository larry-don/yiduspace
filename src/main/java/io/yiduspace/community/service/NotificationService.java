package io.yiduspace.community.service;

import io.yiduspace.community.enums.NotificationStatusEnum;
import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.exception.CustomizeException;
import io.yiduspace.community.mapper.NotificationMapper;
import io.yiduspace.community.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public Notification destroyUnread(Long id) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        notification.setNotificationStatus(NotificationStatusEnum.READ.getStatusId());
        notificationMapper.updateByPrimaryKey(notification);
        return notification;
    }
}
