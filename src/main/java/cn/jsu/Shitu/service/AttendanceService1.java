package cn.jsu.projectmanage.service;

import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.pojo.vo.UserSignInfoVo;

public interface AttendanceService1 {

    CommonResult signIn(UserSignInfoVo userSignInfoVo);

    CommonResult userSignOut(UserSignInfoVo userSignInfoVo);

    CommonResult yearSignOutTable(UserSignInfoVo userSignInfoVo);

    CommonResult queryEachDaySignSituation(UserSignInfoVo userSignInfoVo);

    CommonResult queryOneDaySingleSignSituation(UserSignInfoVo userSignInfoVo);

    CommonResult updateAbsentCause(UserSignInfoVo userSignInfoVo);
}
