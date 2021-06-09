package io.yiduspace.community.mapper;

import io.yiduspace.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified" +
            ",creator,comment_count,view_count,like_count,tag) values(#{title}," +
            "#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount}," +
            "#{viewCount},#{likeCount},#{tag})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> getQuestionList(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("select * from question where creator = #{userId} limit #{offset},#{pageSize}")
    List<Question> getQuestionListByUserId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("userId") int userId);


    @Select("select count(1) from question")
    int getTotalCount();

    @Select("select count(1) from question where creator = #{userId}")
    int getTotalCountByUserId(@Param("userId") int userId);

}
