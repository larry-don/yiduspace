package io.yiduspace.community.mapper;

import io.yiduspace.community.model.Question;
import io.yiduspace.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    long countByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int deleteByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int insert(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int insertSelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    List<Question> selectByExampleWithBLOBsWithRowbounds(QuestionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    List<Question> selectByExampleWithBLOBs(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    List<Question> selectByExampleWithRowbounds(QuestionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    List<Question> selectByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    Question selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int updateByPrimaryKeyWithBLOBs(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Mon Jun 21 16:09:27 CST 2021
     */
    int updateByPrimaryKey(Question record);
}