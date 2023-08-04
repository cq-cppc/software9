package com.cqupt.software_9.controller;

import com.cqupt.software_9.common.R;
import com.cqupt.software_9.service.Online_randforService;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest_online_RandFor;
import com.cqupt.software_9.service.Request.onlineUse;
import com.cqupt.software_9.service.Response.OnlineServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponse;
import com.cqupt.software_9.service.RuntimeBusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/Online_randfor")
@CrossOrigin
public class Online_randfor {
    @Resource
    private Online_randforService online_randforService;

    /**
     * 在线训练_随机森林
     * @RequestBody request
     * @return
     */

    @PostMapping("/submit")
    public OnlineServiceResponse busSubmit(@RequestBody RuntimeBusCreateRequest_online_RandFor request) throws Exception {
        try {
            OnlineServiceResponse result = online_randforService.online_Randfor(request);
            return result;
        } catch (Exception e) {
            OnlineServiceResponse res = new OnlineServiceResponse();
            res.setE(e);
            return res;
        }
    }


}
