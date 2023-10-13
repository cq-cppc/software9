package com.cqupt.software_9.service.imp;


import com.cqupt.software_9.mapper.SympMapper;
import com.cqupt.software_9.entity.Symp;
import com.cqupt.software_9.service.Adapter.SympServiceAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SympServiceImpl extends SympServiceAdapter {
    @Resource
    private SympMapper sympMapper;
    @Override
    public List<Symp> getAllsymp() {
        return sympMapper.findAll();
    }


}
