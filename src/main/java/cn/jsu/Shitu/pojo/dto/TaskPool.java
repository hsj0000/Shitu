package cn.jsu.projectmanage.pojo.dto;

/**
 * @author Mo
 * @createTime 2022/1/4 21:46
 * @descripiton
 */
public class TaskPool {
    private Integer groupTaskId;
    private Integer groupId;
    private Integer taskId;

    public TaskPool() {
    }

    public TaskPool(Integer groupTaskId, Integer groupId, Integer taskId) {
        this.groupTaskId = groupTaskId;
        this.groupId = groupId;
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "TaskPool{" +
                "groupTaskId=" + groupTaskId +
                ", groupId=" + groupId +
                ", taskId=" + taskId +
                '}';
    }

    public Integer getGroupTaskId() {
        return groupTaskId;
    }

    public void setGroupTaskId(Integer groupTaskId) {
        this.groupTaskId = groupTaskId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
