package cn.jsu.projectmanage.service.imp;


import cn.jsu.projectmanage.dao.projectManage.UserDao;
import cn.jsu.projectmanage.pojo.dto.UserInfo;
import cn.jsu.projectmanage.pojo.vo.*;
import cn.jsu.projectmanage.service.UserService;
import cn.jsu.projectmanage.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public CommonResult register(AccountInfo accountInfo) {
        int i = userDao.queryAccount(accountInfo.getUserAccount());
        if (i > 0)//判断该学号是否已注册
            return new CommonResult(444, "该账号已存在，请重新输入!");
        if(userDao.register(accountInfo) > 0)
            return new CommonResult(200, "注册成功，请登录!");
        else
            return new CommonResult(444, "注册失败!");
    }

    @Override
    public CommonResult login(LoginVO loginVO, HttpServletRequest request) {
            int i = userDao.queryAccount(loginVO.getUserAccount());
            if (i <= 0)//判断该学号是否已注册
                return new CommonResult(444, "该账号未注册，请注册!");
            LoginResponseVO login = userDao.queryAccountInfo(loginVO);
            if (!login.equals("") || !"null".equals(login)) {
                HashMap<String, String> payload = new HashMap<>();
                payload.put("userId", login.getUserId().toString());
                String token = JWTUtil.getTOKEN(payload);
                login.setToken(token);
                System.out.println(token);
                return new CommonResult(200, "登陆成功!", login);
            }
            return new CommonResult(444, "密码错误!");
    }

    public CommonResult queryUserInfoAll(PageQueryVo queryUserVo) {
        HashMap<String, Object> map = new HashMap<>(2);
        Integer count = userDao.count1(queryUserVo);
        List<UserInfo> userInfoList = userDao.queryUserInfoAll(queryUserVo);
        map.put("count",count);
        map.put("userInfoList",userInfoList);
        return new CommonResult(200, "查询成功!", map);
    }

    public CommonResult updateUserPassword(UpdateUserPasswordVO updateUserPasswordVO) {
        String oldPassword = userDao.queryOldUserPassword(updateUserPasswordVO);
        if (!updateUserPasswordVO.getUserOldPassword().equals(oldPassword))
            return new CommonResult(444, "旧密码错误, 请确认密码!");
        int i = userDao.updateUserPassword(updateUserPasswordVO);
        if (i >= 0) {
            return new CommonResult(200, "修改成功!");
        } else {
            return new CommonResult(444, "修改失败!");
        }
    }

    @Override
    public CommonResult queryUserInfoGroup(PageQueryVo queryUserVo) {
        Integer count  = userDao.count2(queryUserVo);
        List<UserInfo> userInfoList = userDao.queryUserInfoGroup(queryUserVo);
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("count",count);
        map.put("userInfoList",userInfoList);
        return new CommonResult(200, "查询成功!", map);
    }

    @Override
    public CommonResult queryUserInfoById(Integer userId) {
        UserInfo userInfo = userDao.queryUserInfoById(userId);
        return new CommonResult(200, "查询成功", userInfo);
    }


}
