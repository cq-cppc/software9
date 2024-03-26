package com.cqupt.software_9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.Model;
import com.cqupt.software_9.entity.modelResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface modelResultMapper extends BaseMapper<modelResult> {

    List<modelResult> getModelResultByUidAndModelName(Integer uid, String modelname);
    void removeModelResult(Integer uid, String modelname);

}
