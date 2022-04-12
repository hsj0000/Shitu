package cn.jsu.projectmanage.service;

import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.pojo.vo.QuantumVo;
import cn.jsu.projectmanage.pojo.vo.UserSignInfoVo;

public interface AttendanceService {

    CommonResult signIn(UserSignInfoVo userSignInfoVo);

    CommonResult userSignOut(UserSignInfoVo userSignInfoVo);

    CommonResult yearSignOutTable(UserSignInfoVo userSignInfoVo);

    CommonResult queryEachDaySignSituation(UserSignInfoVo userSignInfoVo);

    CommonResult queryOneDaySingleSignSituation(UserSignInfoVo userSignInfoVo);

    CommonResult queryOneDayUserSignData(UserSignInfoVo userSignInfoVo);

    CommonResult updateAbsentCause(UserSignInfoVo userSignInfoVo);

    CommonResult queryTotalSignTimeByQuantum(QuantumVo quantumVo);

    CommonResult queryAbsenceTimeByQuantum(QuantumVo quantumVo);

}
