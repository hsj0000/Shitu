package cn.jsu.projectmanage.controller;

import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.pojo.vo.QuantumVo;
import cn.jsu.projectmanage.pojo.vo.UserSignInfoVo;
import cn.jsu.projectmanage.service.AttendanceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

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

    @PostMapping("/queryOneDayUserSignData")
    @ApiOperation("查询一天用户具体签到数据")
    public CommonResult queryOneDayUserSignData(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.queryOneDayUserSignData(userSignInfoVo);
    }

    @PostMapping("/updateAbsentCause")
    @ApiOperation("修改缺勤原因")
    public CommonResult updateAbsentCause(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.updateAbsentCause(userSignInfoVo);
    }

    @PostMapping("/queryEachDaySignSituation")
    @ApiOperation("查询一天所有人签到情况")
    public CommonResult queryEachDaySignSituation(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.queryEachDaySignSituation(userSignInfoVo);
    }

    @PostMapping("/queryTotalSignTimeByQuantum")
    @ApiOperation("查询不同时间段用户签到时长")
    public CommonResult queryTotalSignTimeByQuantum(@RequestBody QuantumVo quantumVo) {
        return attendanceService.queryTotalSignTimeByQuantum(quantumVo);
    }

    @PostMapping("/queryAbsenceTimeByQuantum")
    @ApiOperation("查询不同时间段用户缺勤天数")
    public CommonResult queryAbsenceTimeByQuantum(@RequestBody QuantumVo quantumVo) {
        return attendanceService.queryAbsenceTimeByQuantum(quantumVo);
    }

//    @PostMapping("/queryQuantumUser")
//    @ApiOperation("查询一段时间用户具体签到数据表")
//    public CommonResult queryQuantumUser(@RequestBody UserSignInfoVo userSignInfoVo) {
//        return attendanceService.queryQuantumUser(userSignInfoVo);
//    }


    //不用->
//-----------------------------------------------------------------------------------------------
    @PostMapping("/yearSignOutTable")
    @ApiOperation("年度签到表")
    public CommonResult yearSignOutTable(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.yearSignOutTable(userSignInfoVo);
    }

    @PostMapping("/queryOneDaySingleSignSituation")
    @ApiOperation("查询一天用户签到情况")
    public CommonResult queryOneDaySingleSignSituation(@RequestBody UserSignInfoVo userSignInfoVo) {
        return attendanceService.queryOneDaySingleSignSituation(userSignInfoVo);
    }
}
