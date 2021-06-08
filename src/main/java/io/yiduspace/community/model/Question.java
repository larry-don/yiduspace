package io.yiduspace.community.model;

import lombok.Data;

@Data
public class Question {
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;//阅读数
    private int likeCount;//点赞数
    private String tag;

}