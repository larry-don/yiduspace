package io.yiduspace.community.dto;

import io.yiduspace.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private String content;
    private Long parentId;
    private Integer type;
    private Long id;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private User user;
}
