package lcqjoyce.bbs.service.impl;

import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.dto.QuestionDTO;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.exception.CustomizeException;
import lcqjoyce.bbs.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lcqjoyce.bbs.mapper.QuestionMapper;
import lcqjoyce.bbs.entity.Question;
import lcqjoyce.bbs.service.QuestionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
    public QuestionDTO selectByPrimaryKey(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null || question.equals(null)) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);

        return questionDTO;
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

        PageinfoDTO<QuestionDTO> pageinfoDTO = new PageinfoDTO();
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
        List<Question> questions = questionMapper.getAll(null, offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageinfoDTO.setData(questionDTOS);


        return pageinfoDTO;
    }

    @Override
    public PageinfoDTO listMyQuestion(Long userId, Integer page, Integer size) {

        PageinfoDTO<QuestionDTO> pageinfoDTO = new PageinfoDTO();
        Integer totalCount = questionMapper.countByCreator(userId);
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
        List<Question> questions = questionMapper.getAll(userId, offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageinfoDTO.setData(questionDTOS);


        return pageinfoDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            questionMapper.insert(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateByPrimaryKey(question);
        }
    }

    @Override
    public void inView(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        questionMapper.updateViewByPrimaryKey(question);
    }

    @Override
    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question=new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexTag);

        List<Question> questions = questionMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS=questions.stream().map(q->{
           QuestionDTO questionDTO=new QuestionDTO();
           BeanUtils.copyProperties(q,questionDTO);
           return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

}
