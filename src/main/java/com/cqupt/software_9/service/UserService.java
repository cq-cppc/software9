package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.entity.User;
import com.cqupt.software_9.service.Request.Query;

import java.util.List;

public interface UserService {

    IPage<User> selectUserPage(Query query);

    Integer addUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(Integer id);

    void batchDelete(List<Integer> ids);
}