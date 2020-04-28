package lcqjoyce.bbs.controller;

import lcqjoyce.bbs.dto.CommentDTO;
import lcqjoyce.bbs.dto.ResultDTO;
import lcqjoyce.bbs.entity.Comment;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.mapper.CommentMapper;
import lcqjoyce.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {



    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    //把传过来的json自动映射到类里面
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errof(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(1L);
        commentService.insert(comment);
        return ResultDTO.okof();
    }
}
