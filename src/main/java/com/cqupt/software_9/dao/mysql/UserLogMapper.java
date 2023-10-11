package com.cqupt.software_9.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.UserLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLogMapper extends BaseMapper<UserLog> {
}
