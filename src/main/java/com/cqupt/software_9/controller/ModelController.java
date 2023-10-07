package com.cqupt.software_9.controller;

import com.cqupt.software_9.dao.mysql.ModelMapper;
import com.cqupt.software_9.entity.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/Model")
@RestController
public class ModelController {
    @Resource
    private ModelMapper modelMapper;

    @GetMapping("/getall")
    public List<Model> getallmodel(){
        return modelMapper.getallmodel();
    }

}