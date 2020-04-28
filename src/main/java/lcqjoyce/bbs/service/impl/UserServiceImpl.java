package lcqjoyce.bbs.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lcqjoyce.bbs.mapper.UserMapper;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findByToken(String token) {
        System.out.println("查询该token：" + token + " 是否存在");
        return userMapper.findByToken(token);
    }

    @Override
    public void createOrUpdate(User user) {
        List<User> list=userMapper.findByAccountId(user.getAccountId());
        if(list.isEmpty()){
            this.insert(user);
        }else {
            list.get(0).setGmtModified(System.currentTimeMillis());
            list.get(0).setAvatarUrl(user.getAvatarUrl());
            list.get(0).setName(user.getName());
            list.get(0).setToken(user.getToken());
            userMapper.updateByPrimaryKey(list.get(0));
        }

    }


    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

}
