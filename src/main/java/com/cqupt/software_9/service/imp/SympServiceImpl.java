package com.cqupt.software_9.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.software_9.dao.mysql.DiseasesMapper;
import com.cqupt.software_9.dao.mysql.SympMapper;
import com.cqupt.software_9.entity.Diseases;
import com.cqupt.software_9.entity.Symp;
import com.cqupt.software_9.service.Adapter.DiseasesServiceAdapter;
import com.cqupt.software_9.service.Adapter.SympServiceAdapter;
import com.cqupt.software_9.service.Request.Query;
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
