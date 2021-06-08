package io.yiduspace.community.controller;

import io.yiduspace.community.dto.PaginationDTO;
import io.yiduspace.community.dto.QuestionDTO;
import io.yiduspace.community.mapper.UserMapper;
import io.yiduspace.community.model.User;
import io.yiduspace.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                        @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.getUserByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                }
                break;
            }
        }
        PaginationDTO paginationDTO = questionService.getPaginationDTO(currentPage,pageSize);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }
}
