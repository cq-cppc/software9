package com.cqupt.software_9.dao.mysql;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelMapper extends BaseMapper<Model> {
    List<Model> getallmodel();

}
