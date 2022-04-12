package cn.jsu.projectmanage.dao.projectManage;

import cn.jsu.projectmanage.pojo.dto.JudgeInfo;
import cn.jsu.projectmanage.pojo.dto.TaskInfo;
import cn.jsu.projectmanage.pojo.vo.TaskInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskDao {

    //将任务信息插入到任务表
    void insertTaskInfo(TaskInfo taskInfo);

    /**
     * 插入任务池表
     * @param groupId 小组id
     * @param taskId 任务id
     */
    public void insertTaskPool(Integer groupId,Integer taskId);

    public TaskInfo queryTaskById(Integer taskId);

    /**
     * 查询任务表中executor_id为用户Id且status相同
     * @param userId
     * @return
     */
    public List<TaskInfoVO> queryTaskUser(Integer userId, Integer status);

    public void updateTask(TaskInfo taskInfo);

    public int deleteTaskPool(Integer taskId);

    public void updateTaskExecutor(Integer userId,Integer taskId);

    public  int deleteTask(Integer taskId);

    public void updateTaskStatus(Integer taskId,Integer status);

    /**
     * 根据任务ID和状态码查出评审信息
     * @param taskId 任务Id
     * @param status 状态码 0为未完成， 1为完成
     * @return
     */
    public List<JudgeInfo> queryJudgedInfoTaskByStatus(Integer taskId, Integer status);

    /**
     * 根据用户ID和状态码查出评审信息
     * @param userId 用户Id
     * @param status 状态码 0为未完成， 1为完成
     * @return
     */
    public List<JudgeInfo> queryJudgeInfoUserByStatus(Integer userId, Integer status);

    /**
     * 根据judgeId在评审表中插入评审意见、评审结果和评审结束时间
     * @param judgeInfo
     */
    public void Judge(JudgeInfo judgeInfo);


    /**
     * 根据judge_id修改评审表中的评审状态
     */
    public void updateJudgeStatus(Integer judgeId,Integer status);

    /**
     * 在任务表中查询符合状态和类型的项目任务
     * @param projectId 项目Id
     * @param status 状态码
     * @param taskType 任务类型
     * @return
     */
    public Integer queryProjectTaskNumber(Integer projectId,Integer status,Integer taskType);

    public void insertJudge(JudgeInfo judgeInfo);

    public List<TaskInfoVO> queryTaskByGroupId(@Param("groupId") Integer groupId);

    List<TaskInfo> queryTaskPoolTasks(@Param("projectId") Integer projectId);
}
