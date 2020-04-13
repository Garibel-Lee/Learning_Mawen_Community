package lcqjoyce.bbs.service;

import lcqjoyce.bbs.entity.Question;
public interface QuestionService{


    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

}
