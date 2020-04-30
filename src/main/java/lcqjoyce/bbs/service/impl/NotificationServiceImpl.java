package lcqjoyce.bbs.service.impl;

import lcqjoyce.bbs.dto.NotificationDTO;
import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.dto.QuestionDTO;
import lcqjoyce.bbs.entity.Question;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.enums.NotificationStatusEnum;
import lcqjoyce.bbs.enums.NotificationTypeEnum;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.exception.CustomizeException;
import lcqjoyce.bbs.mapper.UserMapper;
import lcqjoyce.bbs.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import lcqjoyce.bbs.entity.Notification;
import lcqjoyce.bbs.mapper.NotificationMapper;
import lcqjoyce.bbs.service.NotificationService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Resource
    private NotificationMapper notificationMapper;
    @Resource
    private UserMapper userMapper;
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
    public PageinfoDTO<NotificationDTO> list(Long id, Integer page, Integer size) {
        PageinfoDTO<NotificationDTO> pageinfoDTO = new PageinfoDTO();
        Integer totalCount = notificationMapper.countById(id);
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
        if(offset<1){
            offset=0;
        }
        List<Notification> notifications = notificationMapper.listMyNotification(id, offset, size);

        if(notifications.size()==0){
            return pageinfoDTO;
        }
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        pageinfoDTO.setData(notificationDTOS);

        return pageinfoDTO;
    }

    @Override
    public Long selectUnreadCount(Long id) {

        return   notificationMapper.selectUnreadCount(id);
    }

    @Override
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());


        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }

}
