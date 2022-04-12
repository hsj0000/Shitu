package cn.jsu.projectmanage.service.imp;

import cn.jsu.projectmanage.dao.projectManage.TaskDao;
import cn.jsu.projectmanage.pojo.dto.JudgeInfo;
import cn.jsu.projectmanage.pojo.dto.TaskInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    TaskDao taskDao;

    /**
     * 系统生成创建时间放入任务信息
     * 判断任务类型，任务类型为0或1放入任务表，任务类型为2放入任务表再放入任务池表
     * @param taskInfo
     * @return
     */
    @Override
    public CommonResult createTask(TaskInfo taskInfo) {
        try {
            taskDao.insertTaskInfo(taskInfo);
            if (taskInfo.getTaskType() ==  0) {
                taskDao.insertTaskPool(taskInfo.getGroupId(),taskInfo.getTaskId());
            } else if (taskInfo.getParentId() != null)
            {
                taskDao.updateTaskStatus(taskInfo.getParentId(),0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  new CommonResult(444,"创建失败!");
        }
        return new CommonResult(200,"创建成功!");
    }

    /**
     * 修改任务名，任务描述，任务结束时间，任务权重，任务优先级
     * @param taskInfo
     * @return
     */
    @Override
    public CommonResult updateTask(TaskInfo taskInfo) {
        try {
            taskDao.updateTask(taskInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(444,"更新失败");
        }
        return new CommonResult(200,"更新成功");
    }

    /**
     * 先删除任务池表中任务，在将任务表中的任务执行人改为用户Id
     * 用任务结束时间减开始时间得到时间跨度并将开始时间重设为当前时间
     * 结束时间设为当前时间加时间跨度
     * @param userId
     * @param taskId
     * @return
     */
    @Override
    public CommonResult getTask(Integer userId, Integer taskId) {
        if(taskDao.deleteTaskPool(taskId) <= 0)
        {
            return new CommonResult(200,"任务被别人抢了");
        }
        taskDao.updateTaskExecutor(userId,taskId);
        TaskInfo taskInfo = taskDao.queryTaskById(taskId);

        long span = taskInfo.getTaskEndTime().getTime() - taskInfo.getTaskStartime().getTime();
        Date endTime = new Date(new Date().getTime() + span);
        taskInfo.setTaskStartime(new Date());
        taskInfo.setTaskEndTime(endTime);
        taskDao.updateTask(taskInfo);


        return new CommonResult(200,"领取成功");
    }

    @Override
    public CommonResult deleteTask(Integer taskId) {
        if(taskDao.deleteTask(taskId) > 0) {
            taskDao.deleteTaskPool(taskId);
            return new CommonResult(200,"删除成功");
        }
        else
            return new CommonResult(444, "删除失败，任务已进行");
    }

    @Override
    public CommonResult queryTaskById(Integer taskId) {
        try {
            return new CommonResult(200,"查询成功",taskDao.queryTaskById(taskId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult(444,"查询失败");
    }

    @Override
    public CommonResult queryTaskUser(Integer userId,Integer status) {
        return new CommonResult(200,"查询成功",taskDao.queryTaskUser(userId,status));
    }

    @Override
    public CommonResult queryTaskedUser() {
        return null;
    }

    @Override
    public CommonResult updateTaskStatus(Integer taskId, Integer status) {
        taskDao.updateTaskStatus(taskId, status);
        return new CommonResult(200,"更新成功");
    }

    /**
     * 先将任务报告存好并保存Url,再将评审插入评审表
     * @param judgeInfo
     * @return
     */
    @Override
    public CommonResult submitTaskReport(JudgeInfo judgeInfo, HttpServletRequest request) {
//        MultipartFile taskReport = ((MultipartHttpServletRequest) request).getFile("multipartFile");
//
//        String uuid32 = FileServiceImp.getUUID32();
//        String filePath = FileServiceImp.getFilePath(uuid32);
//        //获取存储文件路径
//        File path = new File(filePath);
//
//        try {
//            taskReport.transferTo(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new CommonResult(444,"存储文件错误");
//        }

        judgeInfo.setCreateTime(new Date());
        // 0为评审未完成的状态码
        judgeInfo.setStatus(0);
//        judgeInfo.setReportPosition(filePath);
        taskDao.insertJudge(judgeInfo);
        return new CommonResult(200, "提交报告成功");
    }

    @Override
    public CommonResult queryJudgeInfo() {
        return null;
    }

    @Override
    public CommonResult queryJudgeInfoUser() {
        return null;
    }

    /**
     * 将评审意见、评审结果和评审结束时间插入评审表，再改变评审状态
     * @param judgeInfo
     * @return
     */
    @Override
    public CommonResult Judge(JudgeInfo judgeInfo) {
        judgeInfo.setEndTime(new Date());
        taskDao.Judge(judgeInfo);
        taskDao.updateJudgeStatus(judgeInfo.getJudgeId(),1);
        return new CommonResult(200,"评审成功");
    }

    @Override
    public CommonResult queryJudgeInfoUserByStatus(Integer userId, Integer status) {
        return new CommonResult(200,"查询成功",
                taskDao.queryJudgeInfoUserByStatus(userId,status));
    }


    @Override
    public CommonResult queryJudgedInfoTaskByStatus(Integer taskId, Integer status) {
        return new CommonResult(200,"查询成功",
                taskDao.queryJudgedInfoTaskByStatus(taskId,status));
    }


    @Override
    public  CommonResult queryTaskByGroupId(Integer groupId){
        try {
            return new CommonResult(200,"查询成功",taskDao.queryTaskByGroupId(groupId));
        } catch (Exception e){
            e.printStackTrace();
        }
        return new CommonResult(444,"查询失败");
    }

    @Override
    public CommonResult queryTaskPoolTasks(Integer projectId) {
        return new CommonResult(200,"查询成功",taskDao.queryTaskPoolTasks(projectId));
    }
}
