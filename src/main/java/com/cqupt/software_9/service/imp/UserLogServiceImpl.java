package com.cqupt.software_9.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.software_9.dao.mysql.UserLogMapper;
import com.cqupt.software_9.entity.UserLog;
import com.cqupt.software_9.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog>
        implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public int insertUserLog(UserLog userLog) {

        int insert = userLogMapper.insert(userLog);
        return insert;
    }
}
