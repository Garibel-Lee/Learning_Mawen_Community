package lcqjoyce.bbs.service;

import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.dto.QuestionDTO;
import lcqjoyce.bbs.entity.Question;

public interface QuestionService{


    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    QuestionDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    PageinfoDTO getAll(Integer page, Integer size);

    PageinfoDTO listMyQuestion(Long id, Integer page, Integer size);

    void createOrUpdate(Question question);

    void inView(Long id);
}
