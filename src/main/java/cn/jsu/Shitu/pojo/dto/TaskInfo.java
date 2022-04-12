package cn.jsu.projectmanage.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Mo
 * @createTime 2022/1/4 21:40
 * @descripiton
 */
@Data
public class TaskInfo {
    private Integer taskId;
    private Integer parentId;
    private String taskName;
    private String taskDescribe;
    private Integer executorId;
    private Integer groupId;
    private Integer projectId;
    private Integer priority;
    private Integer weight;
    //任务状态  0为未完成 1为已完成 2为提前终止
    private Integer status;
    private Date taskCreateTime;
    private Date taskStartime;
    private Date taskEndTime;
    private Date taskCompleteTime;
    //任务类型 ：0为非叶节点  1为叶节点任务 2为任务池任务
    private Integer taskType;

    public TaskInfo() {
    }

    public TaskInfo(Integer taskId, Integer parentId, String taskName, String taskDescribe, Integer executorId, Integer groupId, Integer projectId, Integer priority, Integer weight, Integer status, Date taskCreateTime, Date taskStartime, Date taskEndTime, Date taskCompleteTime, Integer taskType) {
        this.taskId = taskId;
        this.parentId = parentId;
        this.taskName = taskName;
        this.taskDescribe = taskDescribe;
        this.executorId = executorId;
        this.groupId = groupId;
        this.projectId = projectId;
        this.priority = priority;
        this.weight = weight;
        this.status = status;
        this.taskCreateTime = taskCreateTime;
        this.taskStartime = taskStartime;
        this.taskEndTime = taskEndTime;
        this.taskCompleteTime = taskCompleteTime;
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskId=" + taskId +
                ", parentId=" + parentId +
                ", taskName='" + taskName + '\'' +
                ", taskDescribe='" + taskDescribe + '\'' +
                ", executorId=" + executorId +
                ", groupId=" + groupId +
                ", projectId=" + projectId +
                ", priority=" + priority +
                ", weight=" + weight +
                ", status=" + status +
                ", taskCreateTime=" + taskCreateTime +
                ", taskStartime=" + taskStartime +
                ", taskEndTime=" + taskEndTime +
                ", taskCompleteTime=" + taskCompleteTime +
                ", taskType=" + taskType +
                '}';
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public Integer getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Integer executorId) {
        this.executorId = executorId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public Date getTaskStartime() {
        return taskStartime;
    }

    public void setTaskStartime(Date taskStartime) {
        this.taskStartime = taskStartime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskCompleteTime() {
        return taskCompleteTime;
    }

    public void setTaskCompleteTime(Date taskCompleteTime) {
        this.taskCompleteTime = taskCompleteTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }
}
