package io.yiduspace.community.advice;

import io.yiduspace.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class CustomizeExceptionHandler {

    @RequestMapping("/error")
    @ExceptionHandler(CustomizeException.class)
    public ModelAndView handleControllerException(Model model, Throwable ex) {
        model.addAttribute("message",ex.getMessage());
        return new ModelAndView("error");
    }

}



