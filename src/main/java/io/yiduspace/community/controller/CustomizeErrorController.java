package io.yiduspace.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomizeErrorController implements ErrorController {

    @RequestMapping(produces = {"text/html"})
    private ModelAndView errorHandling(Model model, HttpServletRequest httpServletRequest){
        HttpStatus httpStatus = getStatus(httpServletRequest);
        if(httpStatus.is4xxClientError()){
            model.addAttribute("message","你这个请求错了吧，要不然换个姿势？");
        }
        if(httpStatus.is5xxServerError()){
            model.addAttribute("message", "服务走丢了，要不然你稍后再试试！！！");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(code);
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
