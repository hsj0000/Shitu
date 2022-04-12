package cn.jsu.projectmanage.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author Mo
 * @createTime 2022/1/5 20:15
 * @descripiton
 */
public class TaskInfoVO {
    private Integer taskId;
    private Integer parentId;
    private String taskName;
    private String taskDescribe;
    private Integer executorId;
    private Integer groupId;
    private Integer projectId;
    private Integer weight;
    private Integer priority;
    //任务状态  0为未完成 1为已完成 2为提前终止
    private Integer status;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskCreateTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskStartime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskEndTime;
    //任务类型 ：0为非叶节点  1为叶节点任务 2为任务池任务

    private String formatCreateTime;
    private String formatStartime;
    private String formatEndTime;

    private Integer taskType;

    public TaskInfoVO() {
    }

    public TaskInfoVO(Integer taskId, Integer parentId, String taskName, String taskDescribe, Integer executorId, Integer groupId, Integer projectId, Integer weight, Integer priority, Integer status, Date taskCreateTime, Date taskStartime, Date taskEndTime, String formatCreateTime, String formatStartime, String formatEndTime, Integer taskType) {
        this.taskId = taskId;
        this.parentId = parentId;
        this.taskName = taskName;
        this.taskDescribe = taskDescribe;
        this.executorId = executorId;
        this.groupId = groupId;
        this.projectId = projectId;
        this.weight = weight;
        this.priority = priority;
        this.status = status;
        this.taskCreateTime = taskCreateTime;
        this.taskStartime = taskStartime;
        this.taskEndTime = taskEndTime;
        this.formatCreateTime = formatCreateTime;
        this.formatStartime = formatStartime;
        this.formatEndTime = formatEndTime;
        this.taskType = taskType;
    }

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public String getFormatStartime() {
        return formatStartime;
    }

    public void setFormatStartime(String formatStartime) {
        this.formatStartime = formatStartime;
    }

    public String getFormatEndTime() {
        return formatEndTime;
    }

    public void setFormatEndTime(String formatEndTime) {
        this.formatEndTime = formatEndTime;
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

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "TaskInfoVO{" +
                "taskId=" + taskId +
                ", parentId=" + parentId +
                ", taskName='" + taskName + '\'' +
                ", taskDescribe='" + taskDescribe + '\'' +
                ", executorId=" + executorId +
                ", groupId=" + groupId +
                ", projectId=" + projectId +
                ", weight=" + weight +
                ", priority=" + priority +
                ", status=" + status +
                ", taskCreateTime=" + taskCreateTime +
                ", taskStartime=" + taskStartime +
                ", taskEndTime=" + taskEndTime +
                ", formatCreateTime='" + formatCreateTime + '\'' +
                ", formatStartime='" + formatStartime + '\'' +
                ", formatEndTime='" + formatEndTime + '\'' +
                ", taskType=" + taskType +
                '}';
    }
}
