package io.yiduspace.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;//问题列表
    private boolean firstPage;//首页
    private boolean prePage;//上一页
    private boolean endPage;//尾页
    private boolean nextPage;//下一页
    private List<Integer> pageList;//页码列表
    private int currentPage;//当前页
    private int totalPage;//总页码数

}
