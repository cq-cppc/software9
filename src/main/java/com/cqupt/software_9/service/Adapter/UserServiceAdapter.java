package com.cqupt.software_9.service.Adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.entity.User;
import com.cqupt.software_9.service.Request.Query;
import com.cqupt.software_9.service.UserService;
import com.cqupt.software_9.service.imp.UserServiceImpl;

import java.util.List;

public class UserServiceAdapter implements UserService {
    private UserServiceImpl userServiceImpl;

    @Override
    public IPage<User> selectUserPage(Query query) {
        return userServiceImpl.selectUserPage(query);
    }

    @Override
    public Integer addUser(User user) {
        return null;
    }

    @Override
    public Integer updateUser(User user) {
        return null;
    }

    @Override
    public Integer deleteUser(Integer id) {
        return null;
    }

    @Override
    public void batchDelete(List<Integer> ids) {

    }
}
