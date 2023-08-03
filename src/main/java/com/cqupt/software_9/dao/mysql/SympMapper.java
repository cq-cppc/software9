package com.cqupt.software_9.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.software_9.entity.Diseases;
import com.cqupt.software_9.entity.Symp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SympMapper extends BaseMapper<Symp> {

    List<Symp> findAll();
}
