package lcqjoyce.bbs.controller;

import lcqjoyce.bbs.dto.CommentCreateDTO;
import lcqjoyce.bbs.dto.CommentDTO;
import lcqjoyce.bbs.dto.ResultDTO;
import lcqjoyce.bbs.entity.Comment;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.enums.CommentTypeEnum;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {



    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    //把传过来的json自动映射到类里面
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errof(CustomizeErrorCode.NO_LOGIN);
        }
        if(StringUtils.isEmpty(commentCreateDTO.getContent()) || commentCreateDTO==null){
            return ResultDTO.errof(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setContent(commentCreateDTO.getContent());
        comment.setCommentator(1L);
        commentService.insert(comment);
        return ResultDTO.okof();
    }

//泛型运行
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name="id") Long id ){
        List<CommentDTO> commentDTOs= commentService.findAllByParentIdAndType(id, CommentTypeEnum.COMMENT);
        System.out.println(commentDTOs);
        return ResultDTO.okof(commentDTOs);
    }
}
