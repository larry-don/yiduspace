package io.yiduspace.community.controller;

import io.yiduspace.community.dto.CommentDTO;
import io.yiduspace.community.dto.QuestionDTO;
import io.yiduspace.community.enums.CommentTypeEnum;
import io.yiduspace.community.model.Question;
import io.yiduspace.community.service.CommentService;
import io.yiduspace.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("question/{questionId}")
    public String question(@PathVariable("questionId") Long questionId, Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(questionId);
        List<CommentDTO> list = commentService.getCommentById(questionId, CommentTypeEnum.QUESTION);
        List<Question> relatedQuestion = questionService.selectRelated(questionDTO);
        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("comments",list);
        model.addAttribute("relatedQuestion",relatedQuestion);
        return "question";
    }
}

