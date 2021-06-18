package io.yiduspace.community.mapper;

import io.yiduspace.community.model.Question;

public interface QuestionExtMapper {
   int incViewCount(Question question);
   int incCommentCount(Question question);
}