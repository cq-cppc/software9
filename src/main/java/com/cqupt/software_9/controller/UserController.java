package com.cqupt.software_9.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.entity.User;
import com.cqupt.software_9.service.Request.Query;
import com.cqupt.software_9.service.Response.Result;
import com.cqupt.software_9.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/User")

@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
    @PostMapping("/api/user/list")
    public Result userList(@RequestBody Query queryDTO){
        //System.out.println(queryDTO.toString());
        // 调用userService.selectUserPage(queryDTO)方法获取返回结果
        // 调用userService.selectUserPage(queryDTO)方法获取返回结果
//        IPage<User> userPage = userService.selectUserPage(queryDTO);
//        System.out.println("userPage: " + userPage);
//
//        if (userPage != null && userPage.getRecords() != null) {
//            // 返回结果和记录列表均不为空，可以创建Result对象并返回
//            return new Result(200, "", userPage.getRecords());
//        } else {
//            // 返回结果或记录列表为空，可以创建适当的错误信息或处理方式
//            return new Result(500, "User list is empty.", null);
//        }


       return new Result(200,"",userService.selectUserPage(queryDTO));
    }

    /**
     * 添加
     * @param user
     * @return
     */
    @PostMapping("/api/user/add")
    public Result addUser(@RequestBody User user){
        return new Result(200,"",userService.addUser(user));
    }

    /**
     * 更新
     * @param user
     * @return
     */
    @PostMapping("/api/user/update")
    public Result updateUser(@RequestBody User user){
        return new Result(200,"",userService.updateUser(user));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/api/user/delete")
    public Result deleteUser(Integer id){
        return new Result(200,"",userService.deleteUser(id));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/api/user/delete/batch")
    public Result batchDeleteUser(@RequestBody List<Integer> ids){
        userService.batchDelete(ids);
        return new Result(200,"","");
    }
}