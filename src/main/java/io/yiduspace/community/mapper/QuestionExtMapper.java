package io.yiduspace.community.mapper;

public interface QuestionExtMapper {
   int incViewCount(Long id);
   int incCommentCount(Long id);
}