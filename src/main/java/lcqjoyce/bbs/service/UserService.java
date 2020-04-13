package lcqjoyce.bbs.service;

import lcqjoyce.bbs.entity.User;

public interface UserService {


    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User findByToken(String token);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}
