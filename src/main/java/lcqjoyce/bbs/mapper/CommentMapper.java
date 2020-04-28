package lcqjoyce.bbs.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import lcqjoyce.bbs.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findAllByParentIdAndType(@Param("parentId")Long parentId,@Param("type")Integer type);


}