package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.dao.mysql.DiseasesMapper;
import com.cqupt.software_9.dao.mysql.HeartManagerMapper;
import com.cqupt.software_9.dao.mysql.HeartMapper;
import com.cqupt.software_9.entity.HeartManager;
import com.cqupt.software_9.service.Adapter.HeartManagerServiceAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class HeartManagerServiceImpl extends HeartManagerServiceAdapter {

    @Resource
    private HeartMapper heartMapper;
    @Resource
    private HeartManagerMapper heartManagerMapper;

     public List<String> getinforbytablename(String tablename){

        return  heartMapper.getinforbytablename(tablename);
    }

    public List<HeartManager> getall(){
         return heartManagerMapper.getall();
    }
}
