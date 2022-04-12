package cn.jsu.projectmanage.service.imp;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.jsu.projectmanage.dao.projectManage.AttendanceDao;
import cn.jsu.projectmanage.pojo.dto.SignInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.pojo.vo.EachDaySignSituationVo;
import cn.jsu.projectmanage.pojo.vo.QuantumVo;
import cn.jsu.projectmanage.pojo.vo.UserSignInfoVo;
import cn.jsu.projectmanage.service.AttendanceService;
import cn.jsu.projectmanage.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttendanceServiceImp implements AttendanceService {

    @Autowired
    AttendanceDao attendanceDao;

    @Override
    public CommonResult signIn(UserSignInfoVo userSignInfoVo) {

        SignInfo signInfo = attendanceDao.queryUserSignInfo(userSignInfoVo);
        if(signInfo == null) {//该用户未进行过签到
            userSignInfoVo.setContinueSign(1);
            attendanceDao.insertUserSignInfo(userSignInfoVo);
            attendanceDao.insertUserSignDateRecord(userSignInfoVo);
            attendanceDao.insertUserSignTimeRecord(userSignInfoVo);
            return new CommonResult(200, "首次签到成功");
        } else {
            int signOutRecord = attendanceDao.querySignOutRecord(signInfo);
            if(signOutRecord > 0) {//判断是否有未签退的记录
                return new CommonResult(444, "您有未签退的签到记录，请及时签退");
            }

            Date date1 = DateUtil.parse(signInfo.getLastModifyTime());//上一次修改时间
            Date beginOfDay1 = DateUtil.beginOfDay(date1); //一天的开始时间
            Date date2 = new Date();
            Date beginOfDay2 = DateUtil.beginOfDay(date2);

                userSignInfoVo.setSignId(signInfo.getSignId());
                //设置打卡间隔
                if(attendanceDao.querySignInTime(userSignInfoVo) == 0)
                    return new CommonResult(444, "打卡频繁，请一分钟后再试");

                long betweenDay = DateUtil.between(beginOfDay1,beginOfDay2,DateUnit.DAY);
                if (betweenDay == 0) {//同一天签到
                    userSignInfoVo.setContinueSign(signInfo.getContinueSign());  //拿到连续签到时间
                    userSignInfoVo.setSignRecordId(attendanceDao.querySignRecordId1(userSignInfoVo));
                    attendanceDao.updateUserSignInfo(userSignInfoVo);
                    attendanceDao.insertUserSignTimeRecord(userSignInfoVo);
                    return new CommonResult(200, "今日多次签到成功");
                } else if (betweenDay == 1) {//第二天第一次签到
                    userSignInfoVo.setContinueSign(signInfo.getContinueSign() + 1);  // 连续签到时间加1
                    attendanceDao.updateUserSignInfo(userSignInfoVo);
                    attendanceDao.insertUserSignDateRecord(userSignInfoVo);
                    attendanceDao.insertUserSignTimeRecord(userSignInfoVo);
                    return new CommonResult(200, "第一次签到成功");
                } else {
                    userSignInfoVo.setContinueSign(1);  //断签设置连续签到为1
                    attendanceDao.updateUserSignInfo(userSignInfoVo);
                    attendanceDao.insertUserSignDateRecord(userSignInfoVo);
                    attendanceDao.insertUserSignTimeRecord(userSignInfoVo);
                    return new CommonResult(200, "签到成功, 你断签了哟");
                }
        }
    }

    @Override
    public CommonResult userSignOut(UserSignInfoVo userSignInfoVo) {

        SignInfo signInfo = attendanceDao.queryUserSignInfo(userSignInfoVo);
        if(signInfo == null) {
            return new CommonResult(444, "您的用户签到信息为空，请签到");
        }
        userSignInfoVo.setSignId(signInfo.getSignId());  // 拿到个人签到信息数据
        userSignInfoVo.setContinueSign(signInfo.getContinueSign());  //  同上
        int signOutRecord = attendanceDao.querySignOutRecord(signInfo);
        if(signOutRecord == 0) {
            return new CommonResult(444, "签退失败，你还没有签到哦");
        } else if (signOutRecord == 1) {
                userSignInfoVo.setSignTimeId(attendanceDao.querySignTimeId(userSignInfoVo));
                //userSignInfoVo.setSignRecordId(attendanceDao.querySignRecordId(userSignInfoVo));
                attendanceDao.updateUserSignInfo(userSignInfoVo);
                attendanceDao.updateUserSignOutTimeRecord(userSignInfoVo);
                //进行签退计算时长
                if(attendanceDao.updateTotalSignTime(userSignInfoVo.getSignTimeId()) > 0)
                return new CommonResult(200, "签退成功");
                else return new CommonResult(444, "签退失败");
        } else {
            // 出现这个错误说明当天有两个及两个以上未签退数据，业务流程上不应存在这样的数据
            return new CommonResult(444, "未签退数据异常，请联系管理员");
        }
    }

    @Override
    public CommonResult yearSignOutTable(UserSignInfoVo userSignInfoVo) {

        String startTime = userSignInfoVo.getYear().concat("-1-1 0:0:0");//一年开始时间
        String endTime = userSignInfoVo.getYear().concat("-12-31 0:0:0");//一年结束时间

        List<Map<String, Object>> mapList = attendanceDao.yearSignOutTable(userSignInfoVo);
        List<String> dateList = DateUtils.getDayBetweenDates(startTime, endTime);
        Map<String, Object> dateMap = new HashMap<>();
        for (Map<String, Object> itemMap : mapList) {
            dateMap.put(itemMap.get("DATE").toString(), itemMap.get("COUNT"));
        }
        List<List<Object>> resultList = new ArrayList<>();
        for (String item : dateList) {
            double count = 0;
            if (dateMap.get(item) != null) {
                count = Double.valueOf(dateMap.get(item).toString());
            }
            List<Object> objectList = new ArrayList<>();
            objectList.add(item);
            objectList.add(count);
            resultList.add(objectList);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("contributeDate", userSignInfoVo.getYear());
        resultMap.put("eachDailySubmitTopicSum", resultList);

        return new CommonResult(200, "获取成功",resultMap);
    }

    @Override
    public CommonResult queryEachDaySignSituation(UserSignInfoVo userSignInfoVo) {
        userSignInfoVo.setUserId(null);
        List<EachDaySignSituationVo> eachDaySignSituationVoList = attendanceDao.queryOneDaySignInSituation(userSignInfoVo);
        Map<String, Object> map = new HashMap<>(2);
        map.put("count", attendanceDao.queryCount());
        map.put("eachDaySignSituationVoList", eachDaySignSituationVoList);
        return new CommonResult(200, "查询成功",map);
    }

    @Override
    public CommonResult queryOneDaySingleSignSituation(UserSignInfoVo userSignInfoVo) {
        return new CommonResult(200, "查询成功",attendanceDao.queryOneDaySignInSituation(userSignInfoVo));
    }

    @Override
    public CommonResult queryOneDayUserSignData(UserSignInfoVo userSignInfoVo) {
        List<EachDaySignSituationVo> eachDaySignSituationVo = attendanceDao.queryOneDaySignInSituation(userSignInfoVo);
        List<String> data = attendanceDao.queryOneDayUserSignData(userSignInfoVo);
        Map<String,Object> map = new HashMap<>(2);
        map.put("eachDaySignSituationVo",eachDaySignSituationVo);
        map.put("data", data);
        return new CommonResult(200, "查询成功",map);
    }

    @Override
    public CommonResult updateAbsentCause(UserSignInfoVo userSignInfoVo) {
        attendanceDao.updateAbsentCause(userSignInfoVo);
        return new CommonResult(200, "修改成功");
    }

    @Override
    public CommonResult queryTotalSignTimeByQuantum(QuantumVo quantumVo) {
        List<EachDaySignSituationVo> eachDaySignSituationVoList = attendanceDao.queryTotalSignTimeByQuantum(quantumVo);
        Map<String, Object> map = new HashMap<>(2);
        map.put("count", attendanceDao.queryCount());
        map.put("eachDaySignSituationVoList",eachDaySignSituationVoList);
        return new CommonResult(200, "查询成功",map);
    }

    @Override
    public CommonResult queryAbsenceTimeByQuantum(QuantumVo quantumVo) {
        Date startTime = DateUtil.parse(quantumVo.getStartTime(), "yyyy-MM-dd");
        Date endTime = DateUtil.parse(quantumVo.getEndTime(), "yyyy-MM-dd");
        quantumVo.setDays(DateUtil.between(startTime,endTime,DateUnit.DAY)+1);
        Map<String, Object> map = new HashMap<>(2);
        map.put("count", attendanceDao.queryCount());
        map.put("AbsenceTime",attendanceDao.queryAbsenceTimeByQuantum(quantumVo));
        return new CommonResult(200, "查询成功",map);
    }

    @Scheduled(cron = "0 0 0 * * ?")//自动签退
    public void autoSetSignOutTimeStop() {
        System.out.println(DateUtil.date() + "签到时间截止");
        attendanceDao.signOutStop();
    }

}
