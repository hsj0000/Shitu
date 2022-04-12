package cn.jsu.projectmanage.controller;


import cn.jsu.projectmanage.pojo.vo.*;
import cn.jsu.projectmanage.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ApiOperation("注册")
    public CommonResult register(@RequestBody AccountInfo accountInfo) {
        return userService.register(accountInfo);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public CommonResult login(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        return userService.login(loginVO, request);
    }

    @PostMapping("/queryUserInfoAll")
    @ApiOperation("查询所有用户")
    public CommonResult queryUserInfoAll(@RequestBody PageQueryVo queryUserVo) {
        return userService.queryUserInfoAll(queryUserVo);
    }

    @PostMapping("/updateUserPassword")
    @ApiOperation("修改密码")
    public CommonResult updateUserPassword(@RequestBody UpdateUserPasswordVO updateUserPasswordVO) {
        return userService.updateUserPassword(updateUserPasswordVO);
    }

    @PostMapping("/queryUserInfoGroup")
    @ApiOperation("查询小组内用户")
    public CommonResult queryUserInfoGroup(@RequestBody PageQueryVo queryUserVo) {
        return userService.queryUserInfoGroup(queryUserVo);
    }

    @PostMapping("/queryUserInfoById")
    @ApiOperation("通过id查询用户")
    public CommonResult queryUserInfoById(@RequestParam("userId")Integer userId) {
        return userService.queryUserInfoById(userId);
    }
}
