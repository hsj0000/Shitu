package cn.jsu.projectmanage.controller;

import cn.jsu.projectmanage.pojo.dto.JudgeInfo;
import cn.jsu.projectmanage.pojo.dto.TaskInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

//  zpy觉得这个接口写的有问题
    @PostMapping("/createTask")
    @ApiOperation("创建任务")
    public CommonResult createTask(@RequestBody TaskInfo taskInfo){
        return taskService.createTask(taskInfo);
    }

    /**
     * 只能删除未开始的任务
     * @return
     */
    @PostMapping("/deleteTask")
    @ApiOperation("删除任务")
    public CommonResult deleteTask (@RequestParam("taskId") Integer taskId){
        return taskService.deleteTask(taskId);
    }

    /**
     * 任务名 任务描述 任务优先级 任务结束时间 任务状态 任务类型
     * @param taskInfo
     * @return
     */
    @PostMapping("/updateTask")
    @ApiOperation("修改任务信息")
    public CommonResult updateTask (@RequestBody TaskInfo taskInfo){
        return taskService.updateTask(taskInfo);
    }


    @PostMapping("/queryTaskById")
    @ApiOperation("查询任务")
    public CommonResult queryTaskById (@RequestParam("taskId") Integer taskId){
        return taskService.queryTaskById(taskId);
    }

    /**
     *
     * @param projectId
     *        id为0： 查询所有项目的任务池
     *        id不为0：查询对应项目的任务池
     * @return
     */
    @PostMapping("/queryTaskPoolTasks")
    @ApiOperation("查询任务池的所有任务")
    public CommonResult queryTaskPoolTasks(@RequestParam("projectId") Integer projectId){
        return taskService.queryTaskPoolTasks(projectId);
    }

    @PostMapping("/getTask")
    @ApiOperation("领取任务")
    public CommonResult getTask (@RequestParam("userId") Integer userId,
                                 @RequestParam("taskId") Integer taskId){
        return taskService.getTask(userId, taskId);
    }

    @PostMapping("/updateTaskStatus")
    @ApiOperation("修改任务状态 0为未完成 1为已完成 2为提前终止")
    public CommonResult updateTaskStatus (@RequestParam("taskId") Integer taskId,
                                          @RequestParam("status") Integer status){
        return taskService.updateTaskStatus(taskId, status);
    }

    /**
     * @Author spl
     * @param groupId
     * @return
     */
    @PostMapping("/queryTaskByGroupId")
    @ApiOperation("查询小组任务")
    public CommonResult queryTaskByGroupId(@RequestParam("groupId") Integer groupId){
        return taskService.queryTaskByGroupId(groupId);
    }

    // done
    @PostMapping("/queryTaskUserByStatus")
    @ApiOperation("根据任务状态查询用户的任务，0为未完成 1为已完成 2为提前终止 3为全部")
    public CommonResult queryTaskUserByStatus (@RequestParam("userId") Integer userId,
                                               @RequestParam("status") Integer status){
        return taskService.queryTaskUser(userId, status);
    }

    //-----------------------------------------------------------------------------------------------------

    @PostMapping("/queryJudgedInfoTaskByStatus")
    @ApiOperation("根据评审状态查询任务的评审")
    public CommonResult queryJudgedInfoTaskByStatus (@RequestParam("taskId") Integer taskId,
                                                     @RequestParam("status") Integer status){
        return taskService.queryJudgedInfoTaskByStatus(taskId, status);
    }

    /**
     * 查询用户需要的评审
     */
    @PostMapping("/queryJudgeUserByStatus")
    @ApiOperation("根据评审状态查询用户的评审,0为未完成,1为完成")
    public CommonResult queryJudgeInfoUserByStatus (@RequestParam("userId") Integer userId,
                                                    @RequestParam("status") Integer status){
        return taskService.queryJudgeInfoUserByStatus(userId, status);
    }

    @PostMapping("/Judge")
    @ApiOperation("评审")
    public CommonResult Judge (@RequestBody JudgeInfo judgeInfo){
        return taskService.Judge(judgeInfo);
    }

    /**
     * 创建一条评审
     * @return
     */
    @PostMapping("/submitTaskReport")
    @ApiOperation("提交任务报告")
    public CommonResult submitTaskReport (@RequestBody JudgeInfo judgeInfo, HttpServletRequest request){
        return taskService.submitTaskReport(judgeInfo,request);
    }

    @PostMapping("/queryJudgeInfo")
    @ApiOperation("查看评审信息")
    public CommonResult queryJudgeInfo () {
        return null;
    }

}
