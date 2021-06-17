package io.yiduspace.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String content;
    private Long parentId;
    private Integer type;
}
