package cn.jsu.projectmanage.service.imp;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.jsu.projectmanage.dao.projectManage.AttendanceDao1;
import cn.jsu.projectmanage.pojo.dto.SignInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.pojo.vo.EachDaySignSituationVo;
import cn.jsu.projectmanage.pojo.vo.UserSignInfoVo;
import cn.jsu.projectmanage.service.AttendanceService1;
import cn.jsu.projectmanage.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttendanceServiceImp1 implements AttendanceService1 {

    @Autowired
    AttendanceDao1 attendanceDao;

    @Override
    public CommonResult signIn(UserSignInfoVo userSignInfoVo) {

        SignInfo signInfo = attendanceDao.queryUserSignInfo(userSignInfoVo);
        if(signInfo == null) {//该用户未进行过签到
            userSignInfoVo.setContinueSign(1);
            attendanceDao.insertUserSignInfo(userSignInfoVo);
            attendanceDao.insertUserSignDateRecord(userSignInfoVo);
            attendanceDao.insertUserSignTimeRecord(userSignInfoVo);
            return new CommonResult(200, "第一次签到成功");
        } else {
            int signOutRecord = attendanceDao.querySignOutRecord(signInfo);
            if(signOutRecord > 0) {//判断是否有未签退的记录
                return new CommonResult(444, "您有未签退的签到记录，请及时签退");
            }

            Date date1 = DateUtil.parse(signInfo.getLastModifyTime());// 上一次修改时间
            Date beginOfDay1 = DateUtil.beginOfDay(date1); //一天的开始时间
            Date date2 = new Date();
            Date beginOfDay2 = DateUtil.beginOfDay(date2);

            if(DateUtil.between(beginOfDay2,date2,DateUnit.HOUR) >= 8) {   //早上八点之前不是签到时间
                userSignInfoVo.setSignId(signInfo.getSignId());

                //设置同一时间段最多打卡次数
                if(attendanceDao.querySignInTimes(userSignInfoVo) >= 1)
                    return new CommonResult(444, "同一时间段不可重复打卡");

                long betweenDay = DateUtil.between(beginOfDay1,beginOfDay2,DateUnit.DAY);
                if (betweenDay == 0) {//同一天签到
                    userSignInfoVo.setContinueSign(signInfo.getContinueSign());  // 拿到连续签到时间
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
            } else {
                return new CommonResult(444, "该时段不是上班时间");
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
            return new CommonResult(444, "签退失败(你还没有签到哦)");
        } else if (signOutRecord == 1) {
            userSignInfoVo.setSignTimeId(attendanceDao.querySignTimeId(userSignInfoVo));
            //userSignInfoVo.setSignRecordId(attendanceDao.querySignRecordId(userSignInfoVo));
            attendanceDao.updateUserSignInfo(userSignInfoVo);
            attendanceDao.updateUserSignOutTimeRecord(userSignInfoVo);

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
            Integer count = 0;
            if (dateMap.get(item) != null) {
                count = Integer.valueOf(dateMap.get(item).toString());
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
    public CommonResult updateAbsentCause(UserSignInfoVo userSignInfoVo) {
        attendanceDao.updateAbsentCause(userSignInfoVo);
        return new CommonResult(200, "修改成功");
    }

//    @Scheduled(cron = "0 0 12 * * ?")//上午签退时间截止
//    @Scheduled(cron = "0 0 14 * * ?")//中午签退时间截止
//    @Scheduled(cron = "0 0 18 * * ?")//下午签退时间截止
//    @Scheduled(cron = "0 0 23 * * ?")//晚上签退时间截止
//    public void autoSetSignOutTimeStop() {
//        System.out.println(DateUtil.date() + "签到时间截止");
//        attendanceDao.signOutStop();
//    }


}
