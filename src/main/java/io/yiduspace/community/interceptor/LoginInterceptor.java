package io.yiduspace.community.interceptor;

import io.yiduspace.community.enums.NotificationStatusEnum;
import io.yiduspace.community.mapper.NotificationMapper;
import io.yiduspace.community.mapper.UserMapper;
import io.yiduspace.community.model.NotificationExample;
import io.yiduspace.community.model.User;
import io.yiduspace.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample example = new UserExample();
                    example.createCriteria().andTokenEqualTo(token);
                    List<User> list = userMapper.selectByExample(example);
                    if (list.size() != 0) {
                        request.getSession().setAttribute("user", list.get(0));
                        NotificationExample example1 = new NotificationExample();
                        example1.createCriteria().andRecipientEqualTo(list.get(0).getId())
                                .andNotificationStatusEqualTo(NotificationStatusEnum.UNREAD
                                        .getStatusId());
                        long notificationNumber = notificationMapper.countByExample(example1);
                        request.getSession().setAttribute("notificationNumber", notificationNumber);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
