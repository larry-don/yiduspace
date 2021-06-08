package io.yiduspace.community.service;

import io.yiduspace.community.dto.PaginationDTO;
import io.yiduspace.community.dto.QuestionDTO;
import io.yiduspace.community.mapper.QuestionMapper;
import io.yiduspace.community.mapper.UserMapper;
import io.yiduspace.community.model.Question;
import io.yiduspace.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    //获取问题列表
    public List<QuestionDTO> getQuestionList(int currentPage, int pageSize) {
        int offset = (currentPage - 1)*pageSize;
        List<QuestionDTO> questionDTOLists = new ArrayList<>();
        List<Question> lists = questionMapper.getQuestionList(offset,pageSize);
        for (Question list : lists) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(list,questionDTO);
            User user = userMapper.getUserById(list.getCreator());
            questionDTO.setUser(user);
            questionDTOLists.add(questionDTO);
        }
        return questionDTOLists;
    }

    //获取分页对象
    public PaginationDTO getPaginationDTO(int currentPage, int pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount = questionMapper.getTotalCount();//问题总记录数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize:totalCount/pageSize+1;//总分页数
        boolean firstPage,endPage,prePage,nextPge;
        List<Integer> pageList = new ArrayList<>();//页码列表
        //请求页码的容错处理
        if(currentPage < 1){
            currentPage = 1;
        }
        if(currentPage > totalPage){
            currentPage = totalPage;
        }
        paginationDTO.setCurrentPage(currentPage);
        paginationDTO.setTotalPage(totalPage);
        //是否显示首页按钮
        if(currentPage > 1){
            firstPage = true;
        }else{
            firstPage = false;
        }

        //是否显示尾页按钮
        if(currentPage < totalPage){
            endPage = true;
        }else{
            endPage = false;
        }

        //是否显示上一页
        if(currentPage - 1 > 0){
            prePage = true;
        }else{
            prePage = false;
        }

        //是否显示下一页
        if(totalPage - currentPage > 0){
            nextPge = true;
        }else{
            nextPge = false;
        }

        //添加页码列表
        pageList.add(currentPage);
        for (int i = 1; i <= 3 ; i++) {
            if(currentPage - i > 0){
                pageList.add(0,currentPage - i);
            }
            if(currentPage + i <= totalPage){
                pageList.add(currentPage+i);
            }
        }

        paginationDTO.setFirstPage(firstPage);
        paginationDTO.setEndPage(endPage);
        paginationDTO.setPrePage(prePage);
        paginationDTO.setNextPage(nextPge);
        paginationDTO.setPageList(pageList);
        List<QuestionDTO> questionDTOS = getQuestionList(currentPage,pageSize);
        paginationDTO.setQuestionDTOS(questionDTOS);

        return paginationDTO;
    }
}
