package io.yiduspace.community.mapper;

import io.yiduspace.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
   int incViewCount(Question question);
   int incCommentCount(Question question);
   List<Question> selectRelated(Question question);
}