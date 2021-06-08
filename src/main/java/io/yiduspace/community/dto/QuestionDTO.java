package io.yiduspace.community.dto;

import io.yiduspace.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;//阅读数
    private int likeCount;//点赞数
    private String tag;
    private User user;
}
