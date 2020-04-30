package lcqjoyce.bbs.service;

import lcqjoyce.bbs.dto.NotificationDTO;
import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.entity.Notification;
import lcqjoyce.bbs.entity.User;

public interface NotificationService{


    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notification record);

    PageinfoDTO list(Long id, Integer page, Integer size);

    Long selectUnreadCount(Long id);

    NotificationDTO read(Long id, User user);
}
