package io.yiduspace.community.controller;

import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.exception.CustomizeException;
import io.yiduspace.community.model.Notification;
import io.yiduspace.community.model.User;
import io.yiduspace.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String notify(@PathVariable("id") Long id, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            throw new CustomizeException(CustomizeErrorCode.No_login);
        }
        Notification notification = notificationService.destroyUnread(id);
        return "redirect:/question/" + notification.getQuestionId();
    }
}
