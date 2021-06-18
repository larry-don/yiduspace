package io.yiduspace.community.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private String content;
    private Long parentId;
    private Integer type;
}
