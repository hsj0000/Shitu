package cn.jsu.projectmanage.service;

import cn.jsu.projectmanage.pojo.vo.*;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    public CommonResult register(AccountInfo accountInfo);

    public CommonResult login(LoginVO loginVO, HttpServletRequest request);

    public CommonResult queryUserInfoAll(PageQueryVo queryUserVo);

    public CommonResult updateUserPassword(UpdateUserPasswordVO updateUserPasswordVO);

    public CommonResult queryUserInfoGroup(PageQueryVo queryUserVo);

    public CommonResult queryUserInfoById(Integer userId);
}
