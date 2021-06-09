package io.yiduspace.community.controller;

import io.yiduspace.community.dto.PaginationDTO;
import io.yiduspace.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                        @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize) {
        PaginationDTO paginationDTO = questionService.getPaginationDTO(currentPage,pageSize);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }
}
