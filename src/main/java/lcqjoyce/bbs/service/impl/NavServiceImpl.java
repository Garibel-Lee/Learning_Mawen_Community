package lcqjoyce.bbs.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import lcqjoyce.bbs.entity.Nav;
import lcqjoyce.bbs.mapper.NavMapper;
import lcqjoyce.bbs.service.NavService;
@Service
public class NavServiceImpl implements NavService{

    @Resource
    private NavMapper navMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return navMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Nav record) {
        return navMapper.insert(record);
    }

    @Override
    public int insertSelective(Nav record) {
        return navMapper.insertSelective(record);
    }

    @Override
    public Nav selectByPrimaryKey(Integer id) {
        return navMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Nav record) {
        return navMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Nav record) {
        return navMapper.updateByPrimaryKey(record);
    }

}
