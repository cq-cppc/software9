package com.cqupt.software_9.controller;

import com.cqupt.software_9.dao.mysql.ModelMapper;
import com.cqupt.software_9.entity.Diseases;
import com.cqupt.software_9.entity.Model;
import com.cqupt.software_9.service.DiseasesService;
import com.cqupt.software_9.service.Request.Query;
import com.cqupt.software_9.service.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Model")
@RestController
public class ModelController {
    @Autowired
    private ModelMapper modelMapper;
    public ModelController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @GetMapping("/getall")
    public List<Model> getallmodel(){
        return modelMapper.getallmodel();
    }

}