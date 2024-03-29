package io.yiduspace.community.dto;

import io.yiduspace.community.exception.CustomizeErrorCode;
import io.yiduspace.community.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;
    public  static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(customizeErrorCode.getCode());
        resultDTO.setMessage(customizeErrorCode.getMessage());
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException customizeException){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(customizeException.getCode());
        resultDTO.setMessage(customizeException.getMessage());
        return resultDTO;
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(data);
        return resultDTO;
    }
}
