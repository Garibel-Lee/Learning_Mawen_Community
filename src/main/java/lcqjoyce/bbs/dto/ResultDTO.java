package lcqjoyce.bbs.dto;

import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.exception.CustomizeException;
import lombok.Data;

/**
 * @author LCQJOYCE
 */
@Data
public class ResultDTO {
    private Integer  code;
    private String message;

    public static ResultDTO errof(Integer code ,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
    public static ResultDTO errof(CustomizeErrorCode errorCode){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(errorCode.getCode());
        resultDTO.setMessage(errorCode.getMessage());
        return resultDTO;
    }
    public static ResultDTO okof(){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }
    public static ResultDTO errof(CustomizeException e){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(e.getCode());
        resultDTO.setMessage(e.getMessage());
        return resultDTO;
    }
}
