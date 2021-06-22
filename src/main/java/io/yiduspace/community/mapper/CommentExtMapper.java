package io.yiduspace.community.mapper;

import io.yiduspace.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}
