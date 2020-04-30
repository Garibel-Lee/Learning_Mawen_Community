package lcqjoyce.bbs.mapper;

import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.entity.Question;
import org.apache.ibatis.annotations.Param;

import lcqjoyce.bbs.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

    Integer countById(@Param("id") Long id);



    List<Notification> listMyNotification(@Param("receiverId")Long id, @Param("offset")Integer offset, @Param("size") Integer size);


    Long selectUnreadCount(Long id);
}