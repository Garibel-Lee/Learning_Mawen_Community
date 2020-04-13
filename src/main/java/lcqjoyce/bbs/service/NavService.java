package lcqjoyce.bbs.service;

import lcqjoyce.bbs.entity.Nav;
public interface NavService{


    int deleteByPrimaryKey(Integer id);

    int insert(Nav record);

    int insertSelective(Nav record);

    Nav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nav record);

    int updateByPrimaryKey(Nav record);

}
