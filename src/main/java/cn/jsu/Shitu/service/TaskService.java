package cn.jsu.projectmanage.service;

import cn.jsu.projectmanage.pojo.dto.JudgeInfo;
import cn.jsu.projectmanage.pojo.dto.TaskInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;

import javax.servlet.http.HttpServletRequest;

public interface TaskService {

    CommonResult createTask(TaskInfo taskInfo);

    CommonResult updateTask(TaskInfo taskInfo);

    CommonResult getTask(Integer userId,Integer taskId);

    CommonResult deleteTask(Integer taskId);

    CommonResult queryTaskById(Integer taskId);

    CommonResult queryTaskUser(Integer userId, Integer status);

    CommonResult queryTaskedUser();

    CommonResult updateTaskStatus(Integer taskId, Integer status);

    CommonResult submitTaskReport(JudgeInfo judgeInfo, HttpServletRequest request);

    CommonResult queryJudgeInfo();

    CommonResult queryJudgeInfoUser();

    CommonResult Judge(JudgeInfo judgeInfo);

    CommonResult queryJudgeInfoUserByStatus(Integer userId,Integer status);

    CommonResult queryJudgedInfoTaskByStatus(Integer taskId, Integer status);

    CommonResult queryTaskByGroupId(Integer groupId);

    CommonResult queryTaskPoolTasks(Integer projectId);

}
