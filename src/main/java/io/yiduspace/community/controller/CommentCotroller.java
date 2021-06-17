package io.yiduspace.community.controller;

import io.yiduspace.community.dto.CommentDTO;
import io.yiduspace.community.dto.ResultDTO;
import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.model.Comment;
import io.yiduspace.community.model.User;
import io.yiduspace.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentCotroller {

    @Autowired
    private CommentService commentService;
    //@RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    @PostMapping(value = "/comment")
    public Object comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        //登录状态校验
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.No_login);
        }
        Comment comment = new Comment();
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
