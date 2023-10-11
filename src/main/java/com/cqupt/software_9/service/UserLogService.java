package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.entity.UserLog;

public interface UserLogService extends IService<UserLog> {
    int insertUserLog(UserLog userLog);
}
