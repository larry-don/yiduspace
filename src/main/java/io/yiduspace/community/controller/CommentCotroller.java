package io.yiduspace.community.controller;

import io.yiduspace.community.dto.CommentCreateDTO;
import io.yiduspace.community.dto.CommentDTO;
import io.yiduspace.community.dto.ResultDTO;
import io.yiduspace.community.enums.CommentTypeEnum;
import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.model.Comment;
import io.yiduspace.community.model.User;
import io.yiduspace.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentCotroller {

    @Autowired
    private CommentService commentService;

    //@RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    @PostMapping(value = "/comment")
    public Object comment(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        //登录状态校验
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.No_login);
        }
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setType(commentCreateDTO.getType());
        comment.setContent(commentCreateDTO.getContent());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @GetMapping("/comment/{commentId}")
    public ResultDTO<List<CommentDTO>> comment(@PathVariable("commentId") Long commentId){
        List<CommentDTO> list = commentService.getCommentById(commentId, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(list);
    }
}
