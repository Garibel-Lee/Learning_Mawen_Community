package lcqjoyce.bbs.service;

import lcqjoyce.bbs.dto.CommentDTO;
import lcqjoyce.bbs.entity.Comment;

import java.util.List;

public interface CommentService{


    int deleteByPrimaryKey(Long id);

    void insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);


    List<CommentDTO> findAllByParentIdAndType(Long id);
}
