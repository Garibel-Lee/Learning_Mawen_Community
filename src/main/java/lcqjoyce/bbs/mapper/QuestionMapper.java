package lcqjoyce.bbs.mapper;

import lcqjoyce.bbs.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> getAll(@Param("offset")Integer offset,@Param("size") Integer size);

    Integer count();



}