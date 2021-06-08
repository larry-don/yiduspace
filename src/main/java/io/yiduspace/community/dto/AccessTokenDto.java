package io.yiduspace.community.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;

}
