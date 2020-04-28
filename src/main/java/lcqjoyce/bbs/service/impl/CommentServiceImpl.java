package lcqjoyce.bbs.service.impl;

import lcqjoyce.bbs.entity.Question;
import lcqjoyce.bbs.enums.CommentTypeEnum;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.exception.CustomizeException;
import lcqjoyce.bbs.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lcqjoyce.bbs.mapper.CommentMapper;
import lcqjoyce.bbs.entity.Comment;
import lcqjoyce.bbs.service.CommentService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

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

}
