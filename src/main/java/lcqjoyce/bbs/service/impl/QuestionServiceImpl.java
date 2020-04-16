package lcqjoyce.bbs.service.impl;

import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.dto.QuestionDTO;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lcqjoyce.bbs.mapper.QuestionMapper;
import lcqjoyce.bbs.entity.Question;
import lcqjoyce.bbs.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return questionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Question record) {
        return questionMapper.insert(record);
    }

    @Override
    public int insertSelective(Question record) {
        return questionMapper.insertSelective(record);
    }

    @Override
    public Question selectByPrimaryKey(Long id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Question record) {
        return questionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Question record) {
        return questionMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageinfoDTO getAll(Integer page, Integer size) {

        PageinfoDTO pageinfoDTO = new PageinfoDTO();
        Integer totalCount = questionMapper.count();
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pageinfoDTO.setPageinfo(totalPage, page);

        Integer offset = size * (page - 1);
        //偏移量
        List<Question> questions = questionMapper.getAll(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageinfoDTO.setQuestionS(questionDTOS);


        return pageinfoDTO;
    }

}
