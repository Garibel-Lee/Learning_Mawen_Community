package lcqjoyce.bbs.mapper;

import lcqjoyce.bbs.entity.Nav;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NavMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nav record);

    int insertSelective(Nav record);

    Nav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nav record);

    int updateByPrimaryKey(Nav record);
}