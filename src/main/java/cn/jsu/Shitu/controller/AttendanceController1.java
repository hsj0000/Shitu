package cn.jsu.projectmanage.controller;

import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.pojo.vo.UserSignInfoVo;
import cn.jsu.projectmanage.service.AttendanceService1;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author hsj
 * 历史版本
 * 每日分时期签到
 */

@CrossOrigin
@RestController
@RequestMapping("/attendance1")
public class AttendanceController1 {

    @Autowired
    AttendanceService1 attendanceService;

    @PostMapping("/signIn")
    @ApiOperation("用户签到")
    public CommonResult userSignIn(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.signIn(userSignInfoVo);
    }

    @PostMapping("/signOut")
    @ApiOperation("用户签退")
    public CommonResult userSignOut(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.userSignOut(userSignInfoVo);
    }

    @PostMapping("/yearSignOutTable")
    @ApiOperation("年度签到表")
    public CommonResult yearSignOutTable(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.yearSignOutTable(userSignInfoVo);
    }

    @PostMapping("/queryEachDaySignSituation")
    @ApiOperation("查询一天所有人签到情况")
    public CommonResult queryEachDaySignSituation(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.queryEachDaySignSituation(userSignInfoVo);
    }

    @PostMapping("/queryOneDaySingleSignSituation")
    @ApiOperation("查询一天用户签到情况")
    public CommonResult queryOneDaySingleSignSituation(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.queryOneDaySingleSignSituation(userSignInfoVo);
    }

    @PostMapping("/updateAbsentCause")
    @ApiOperation("修改缺勤原因")
    public CommonResult updateAbsentCause(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.updateAbsentCause(userSignInfoVo);
    }

}
