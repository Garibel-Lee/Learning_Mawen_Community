package lcqjoyce.bbs.service.impl;

import lcqjoyce.bbs.dto.CommentDTO;
import lcqjoyce.bbs.entity.Question;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.enums.CommentTypeEnum;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.exception.CustomizeException;
import lcqjoyce.bbs.mapper.QuestionMapper;
import lcqjoyce.bbs.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lcqjoyce.bbs.mapper.CommentMapper;
import lcqjoyce.bbs.entity.Comment;
import lcqjoyce.bbs.service.CommentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void insert(Comment record) {
        if (record.getParentId() == null || record.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
        }
        if (record.getType() == null || !CommentTypeEnum.isExist(record.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (record.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            //回复评论  db: type :2
            Comment dbComment = commentMapper.selectByPrimaryKey(record.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(record);
        } else {
            //回复问题 db: type :1
            Question question = questionMapper.selectByPrimaryKey(record.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(record);
            questionMapper.updateCommentCount(question.getId());
        }

    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CommentDTO> findAllByParentIdAndType(Long id, CommentTypeEnum commentType) {
        List<Comment> comments = commentMapper.findAllByParentIdAndType(id, commentType.getType());
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转为Map
        List<User> users = userMapper.findInId(userIds);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));


        //转化comment为commentDTO  维护comment下的user实体类
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());


        return commentDTOS;
    }

}
