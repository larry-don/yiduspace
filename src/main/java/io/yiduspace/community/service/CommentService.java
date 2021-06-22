package io.yiduspace.community.service;

import io.yiduspace.community.dto.CommentDTO;
import io.yiduspace.community.enums.CommentTypeEnum;
import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.exception.CustomizeException;
import io.yiduspace.community.mapper.*;
import io.yiduspace.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;

    @Transactional
    public void insert(Comment comment) {
        //parentId校验
        if(comment.getParentId() == null || comment.getParentId() <= 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //type校验
        if(comment.getType() == null || !CommentTypeEnum.isExistType(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if(comment.getType() == CommentTypeEnum.QUESTION.getType()){
            //回复问题
            //进一步校验回复的问题是否存在
            Question dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(dbQuestion == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            dbQuestion.setCommentCount(1);
            questionExtMapper.incCommentCount(dbQuestion);
        }else{
            //回复评论
            //进一步校验评论是否存在
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            dbComment.setCommentCount(1);
            commentExtMapper.incCommentCount(dbComment);
        }
    }

    public List<CommentDTO> getCommentById(Long id, CommentTypeEnum commentTypeEnum) {
        //获取评论列表
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(commentTypeEnum.getType());
        //评论倒序取出
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);

        if(comments.size() == 0){
            return new ArrayList<>();
        }

        //获取去重的评论人
        List<Long> commnetators = comments.stream().map(comment -> comment.getCommentator()).distinct().collect(Collectors.toList());

        //将评论人封装成map
        UserExample example1 = new UserExample();
        example1.createCriteria().andIdIn(commnetators);
        List<User> users = userMapper.selectByExample(example1);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //将Comment封装成CommentDTO
        List<CommentDTO> commentDTOList = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOList;
    }
}
