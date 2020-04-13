package lcqjoyce.bbs.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import lcqjoyce.bbs.entity.Notification;
import lcqjoyce.bbs.mapper.NotificationMapper;
import lcqjoyce.bbs.service.NotificationService;
@Service
public class NotificationServiceImpl implements NotificationService{

    @Resource
    private NotificationMapper notificationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return notificationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Notification record) {
        return notificationMapper.insert(record);
    }

    @Override
    public int insertSelective(Notification record) {
        return notificationMapper.insertSelective(record);
    }

    @Override
    public Notification selectByPrimaryKey(Long id) {
        return notificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Notification record) {
        return notificationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Notification record) {
        return notificationMapper.updateByPrimaryKey(record);
    }

}
