package io.yiduspace.community.mapper;

import io.yiduspace.community.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface QuestionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    long countByExample(io.QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int deleteByExample(io.QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int insert(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int insertSelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    List<Question> selectByExampleWithBLOBs(io.QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    List<Question> selectByExample(io.QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    Question selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int updateByExampleSelective(@Param("record") Question record, @Param("example") io.QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") io.QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int updateByExample(@Param("record") Question record, @Param("example") io.QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int updateByPrimaryKeyWithBLOBs(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Fri Jun 11 10:10:18 CST 2021
     */
    int updateByPrimaryKey(Question record);
}