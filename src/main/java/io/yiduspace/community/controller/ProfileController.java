package io.yiduspace.community.controller;

import io.yiduspace.community.dto.PaginationDTO;
import io.yiduspace.community.mapper.UserMapper;
import io.yiduspace.community.model.User;
import io.yiduspace.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("profile/{action}")
    public String profile(HttpServletRequest request,
                          Model model,
                          @PathVariable("action") String action,
                          @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }
        if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        Long id = user.getId();
        PaginationDTO paginationDTO = questionService.getPaginationDTO(currentPage,pageSize, id);
        model.addAttribute("paginationDTO",paginationDTO);
        return "profile";
    }
}
