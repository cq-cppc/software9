package com.cqupt.software_9.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.software_9.dao.mysql.UserMapper;
import com.cqupt.software_9.entity.User;
import com.cqupt.software_9.service.Adapter.UserServiceAdapter;
import com.cqupt.software_9.service.Request.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends UserServiceAdapter {
    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<User> selectUserPage(Query queryDTO) {
        Page<User> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return userMapper.selectUserPage(page,queryDTO.getKeyword());
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateById(user);
    }



    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteById(id);
    }



    @Override
    public void batchDelete(List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
    }

}
