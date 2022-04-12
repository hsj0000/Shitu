package cn.jsu.projectmanage.pojo.dto;

/**
 * @author Mo
 * @createTime 2022/1/4 21:33
 * @descripiton
 */
public class GroupUser {
    private Integer groupUserId;
    private Integer groupId;
    private Integer userId;

    public GroupUser() {
    }

    public GroupUser(Integer groupUserId, Integer groupId, Integer userId) {
        this.groupUserId = groupUserId;
        this.groupId = groupId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GroupUser{" +
                "groupUserId=" + groupUserId +
                ", groupId=" + groupId +
                ", userId=" + userId +
                '}';
    }

    public Integer getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(Integer groupUserId) {
        this.groupUserId = groupUserId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
