package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.entity.DataManager;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DataManagerService extends IService<DataManager> {
   List<DataManager> getDatawithoutresult();
   PageInfo<DataManager> getDatawithoutresult(int page,int pageSize);
}