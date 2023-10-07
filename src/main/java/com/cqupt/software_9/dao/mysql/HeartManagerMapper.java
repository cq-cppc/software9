package com.cqupt.software_9.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.HeartManager;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface HeartManagerMapper extends BaseMapper<HeartManager> {

     String findnamebysymp(String symp);

     List<HeartManager> getall();

}
