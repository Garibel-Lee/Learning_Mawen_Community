package lcqjoyce.bbs.service;

import lcqjoyce.bbs.entity.Notification;
public interface NotificationService{


    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

}
