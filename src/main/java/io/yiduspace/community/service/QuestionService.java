package io.yiduspace.community.service;

import io.yiduspace.community.dto.NotificationDTO;
import io.yiduspace.community.dto.PaginationDTO;
import io.yiduspace.community.dto.QuestionDTO;
import io.yiduspace.community.enums.NotificationStatusEnum;
import io.yiduspace.community.enums.NotificationTypeEnum;
import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.exception.CustomizeException;
import io.yiduspace.community.mapper.NotificationMapper;
import io.yiduspace.community.mapper.QuestionExtMapper;
import io.yiduspace.community.mapper.QuestionMapper;
import io.yiduspace.community.mapper.UserMapper;
import io.yiduspace.community.model.*;
import io.yiduspace.community.util.BeanUtilsCopyIgnoreNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private NotificationMapper notificationMapper;
    //获取问题列表
    public List<QuestionDTO> getQuestionList(int offset, int limit, Long userId) {
        List<QuestionDTO> questionDTOLists = new ArrayList<>();
        List<Question> lists = null;
        if (userId == null) {
            QuestionExample example = new QuestionExample();
            example.setOrderByClause("gmt_create desc");
            lists = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, limit));
        } else {
            QuestionExample example = new QuestionExample();
            example.createCriteria().andCreatorEqualTo(userId);
            example.setOrderByClause("gmt_create desc");
            lists = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, limit));
        }
        if (lists != null) {
            for (Question list : lists) {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(list, questionDTO, BeanUtilsCopyIgnoreNull.getNullPropertyNames(list));
                User user = userMapper.selectByPrimaryKey(list.getCreator());
                questionDTO.setUser(user);
                questionDTOLists.add(questionDTO);
            }
        }
        return questionDTOLists;
    }

    //获取通知列表
    public List<NotificationDTO> getNotificationList(int offset, int limit, Long userId) {
        List<NotificationDTO> notificationDTOLists = new ArrayList<>();
        NotificationExample example1 = new NotificationExample();
        example1.createCriteria().andRecipientEqualTo(userId);
        example1.setOrderByClause("gmt_create desc");
        List<Notification> lists = notificationMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, limit));
        if (lists != null) {
            for (Notification list : lists) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setGmtCreate(list.getGmtCreate());
                notificationDTO.setNotificationStatus(NotificationStatusEnum.statusOf(list.getNotificationStatus()));
                notificationDTO.setNotificationType(NotificationTypeEnum.typeOf(list.getNotificationType()));
                notificationDTO.setQuestionId(list.getQuestionId());
                notificationDTO.setId(list.getId());
                Question question = questionMapper.selectByPrimaryKey(list.getQuestionId());
                if(question == null){
                    throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
                }
                notificationDTO.setQuestionTitle(question.getTitle());
                User user = userMapper.selectByPrimaryKey(list.getReplier());
                notificationDTO.setReplier(user.getName());
                notificationDTOLists.add(notificationDTO);
            }
        }
        return notificationDTOLists;
    }

    //获取我的提问页面或最新回复页面
    public PaginationDTO getPaginationDTO(int currentPage, int pageSize, Long userId,String action) {
        if("questions".equals(action)){
            PaginationDTO paginationDTO = getPaginationDTO(currentPage,pageSize,userId);
            return paginationDTO;
        }
        if("replies".equals(action)){
            PaginationDTO paginationDTO = new PaginationDTO();
            QuestionExample example = new QuestionExample();
            example.createCriteria().andCreatorEqualTo(userId);
            int totalCount = (int) questionMapper.countByExample(example);//问题总记录数

            int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;//总分页数
            boolean firstPage, endPage, prePage, nextPge;
            List<Integer> pageList = new ArrayList<>();//页码列表
            //请求页码的容错处理
            if (currentPage < 1) {
                currentPage = 1;
            }
            if (currentPage > totalPage) {
                currentPage = totalPage;
            }
            paginationDTO.setCurrentPage(currentPage);
            paginationDTO.setTotalPage(totalPage);
            //是否显示首页按钮
            if (currentPage > 1) {
                firstPage = true;
            } else {
                firstPage = false;
            }

            //是否显示尾页按钮
            if (currentPage < totalPage) {
                endPage = true;
            } else {
                endPage = false;
            }

            //是否显示上一页
            if (currentPage - 1 > 0) {
                prePage = true;
            } else {
                prePage = false;
            }

            //是否显示下一页
            if (totalPage - currentPage > 0) {
                nextPge = true;
            } else {
                nextPge = false;
            }

            //添加页码列表
            pageList.add(currentPage);
            for (int i = 1; i <= 3; i++) {
                if (currentPage - i > 0) {
                    pageList.add(0, currentPage - i);
                }
                if (currentPage + i <= totalPage) {
                    pageList.add(currentPage + i);
                }
            }

            paginationDTO.setFirstPage(firstPage);
            paginationDTO.setEndPage(endPage);
            paginationDTO.setPrePage(prePage);
            paginationDTO.setNextPage(nextPge);
            paginationDTO.setPageList(pageList);
            int offset = (currentPage - 1) * pageSize;
            List<NotificationDTO> notificationDTOS = getNotificationList(offset, pageSize, userId);
            paginationDTO.setData(notificationDTOS);

            return paginationDTO;
        }
        return null;
    }

    //获取分页对象
    public PaginationDTO getPaginationDTO(int currentPage, int pageSize, Long userId) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount;
        if (userId == null) {
            totalCount = (int) questionMapper.countByExample(new QuestionExample());//问题总记录数
        } else {
            QuestionExample example = new QuestionExample();
            example.createCriteria().andCreatorEqualTo(userId);
            totalCount = (int) questionMapper.countByExample(example);//问题总记录数
        }

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;//总分页数
        boolean firstPage, endPage, prePage, nextPge;
        List<Integer> pageList = new ArrayList<>();//页码列表
        //请求页码的容错处理
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        paginationDTO.setCurrentPage(currentPage);
        paginationDTO.setTotalPage(totalPage);
        //是否显示首页按钮
        if (currentPage > 1) {
            firstPage = true;
        } else {
            firstPage = false;
        }

        //是否显示尾页按钮
        if (currentPage < totalPage) {
            endPage = true;
        } else {
            endPage = false;
        }

        //是否显示上一页
        if (currentPage - 1 > 0) {
            prePage = true;
        } else {
            prePage = false;
        }

        //是否显示下一页
        if (totalPage - currentPage > 0) {
            nextPge = true;
        } else {
            nextPge = false;
        }

        //添加页码列表
        pageList.add(currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pageList.add(0, currentPage - i);
            }
            if (currentPage + i <= totalPage) {
                pageList.add(currentPage + i);
            }
        }

        paginationDTO.setFirstPage(firstPage);
        paginationDTO.setEndPage(endPage);
        paginationDTO.setPrePage(prePage);
        paginationDTO.setNextPage(nextPge);
        paginationDTO.setPageList(pageList);
        int offset = (currentPage - 1) * pageSize;
        List<QuestionDTO> questionDTOS = getQuestionList(offset, pageSize, userId);
        paginationDTO.setData(questionDTOS);

        return paginationDTO;
    }


    public QuestionDTO getQuestionById(long questionId) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        question.setViewCount(1);
        questionExtMapper.incViewCount(question);
        BeanUtils.copyProperties(question, questionDTO, BeanUtilsCopyIgnoreNull.getNullPropertyNames(question));
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdateQuestion(Question question) {
        if (question.getId() == null) {
            //插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
            if (dbQuestion == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //更新
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExample(question, example);

        }
    }


    public List<Question> selectRelated(QuestionDTO questionDTO) {
        if(StringUtils.isBlank(questionDTO.getTag())){
            return new ArrayList<>();
        }
        //将tag用，分隔并用|拼接，用于正则查询
        String[] strings = StringUtils.split(questionDTO.getTag(), ',');
        String regexpTag = Arrays.stream(strings).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(regexpTag);
        List<Question> list = questionExtMapper.selectRelated(question);
        return list;
    }
}
