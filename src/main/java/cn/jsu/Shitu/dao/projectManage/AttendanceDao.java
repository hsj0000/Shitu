package cn.jsu.projectmanage.dao.projectManage;

import cn.jsu.projectmanage.pojo.dto.SignInfo;
import cn.jsu.projectmanage.pojo.vo.AbsenceVo;
import cn.jsu.projectmanage.pojo.vo.EachDaySignSituationVo;
import cn.jsu.projectmanage.pojo.vo.QuantumVo;
import cn.jsu.projectmanage.pojo.vo.UserSignInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AttendanceDao {

    //查询用户签到信息
    SignInfo queryUserSignInfo(UserSignInfoVo userSignInfoVo);

    //插入用户签到信息
    void insertUserSignInfo(UserSignInfoVo userSignInfoVo);

    //插入用户签到日期信息
    void insertUserSignDateRecord(UserSignInfoVo userSignInfoVo);

    //插入用户签到时间信息
    void insertUserSignTimeRecord(UserSignInfoVo userSignInfoVo);

    //查询当前有多少个未签退的签到记录
    Integer querySignOutRecord(SignInfo signInfo);

    //修改用户签到信息
    void updateUserSignInfo(UserSignInfoVo userSignInfoVo);

    //签退
    Integer updateUserSignOutTimeRecord(UserSignInfoVo userSignInfoVo);

    //查询签到时间Id
    Integer querySignTimeId(UserSignInfoVo userSignInfoVo);

    //查询签到记录Id
    Integer querySignRecordId(UserSignInfoVo userSignInfoVo);
    Integer querySignRecordId1(UserSignInfoVo userSignInfoVo);

    //修改签到总时长
    Integer updateTotalSignTime(@Param("signTimeId") Integer signTimeId);

    //查询时间间隔内签到次数
    Integer querySignInTime(UserSignInfoVo userSignInfoVo);

    //签退时间截止
    void signOutStop();

    //获取签到表
    List<Map<String, Object>> yearSignOutTable(UserSignInfoVo userSignInfoVo);

    //查询每日签到情况
    List<EachDaySignSituationVo> queryOneDaySignInSituation(UserSignInfoVo userSignInfoVo);

    //查询用户数  除去管理员
    Integer queryCount();

    //修改缺勤原因
    Integer updateAbsentCause(UserSignInfoVo userSignInfoVo);

    //查询具体签到数据
    List<String> queryOneDayUserSignData(UserSignInfoVo userSignInfoVo);

    //查询不同时间段用户签到时长
    List<EachDaySignSituationVo> queryTotalSignTimeByQuantum(@Param("quantumVo")QuantumVo quantumVo);

    //查询不同时间段用户缺勤天数
    List<AbsenceVo> queryAbsenceTimeByQuantum(@Param("quantumVo") QuantumVo quantumVo);
}
