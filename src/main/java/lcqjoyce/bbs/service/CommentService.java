package lcqjoyce.bbs.service;

import lcqjoyce.bbs.entity.Comment;
public interface CommentService{


    int deleteByPrimaryKey(Long id);

    void insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

}
