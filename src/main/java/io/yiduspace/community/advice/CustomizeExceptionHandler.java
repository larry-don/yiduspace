package io.yiduspace.community.advice;

import com.alibaba.fastjson.JSON;
import io.yiduspace.community.dto.ResultDTO;
import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@ControllerAdvice
public class CustomizeExceptionHandler {

    @RequestMapping("/error")
    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(Model model, Throwable ex, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO ;
            if(ex instanceof CustomizeException){
                //如果是自定义异常
                resultDTO= ResultDTO.errorOf((CustomizeException) ex);
            }else{
                //其他异常
                ex.printStackTrace();
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            if(ex instanceof CustomizeException){
                //如果是自定义异常
                model.addAttribute("message",ex.getMessage());
            }else{
                //其他异常
                ex.printStackTrace();
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

}



