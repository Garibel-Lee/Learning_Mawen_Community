package lcqjoyce.bbs.controller;

import lcqjoyce.bbs.dto.CommentCreateDTO;
import lcqjoyce.bbs.dto.ResultDTO;
import lcqjoyce.bbs.entity.Comment;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
}
