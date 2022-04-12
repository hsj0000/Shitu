package cn.jsu.projectmanage.pojo.vo;

/**
 * @author Mo
 * @createTime 2022/1/5 15:39
 * @descripiton 修改用户密码数据传输对象
 */
public class UpdateUserPasswordVO {
    private Integer userId;
    private String userOldPassword;
    private String userNewPassword;

    public UpdateUserPasswordVO() {
    }

    public UpdateUserPasswordVO(Integer userId, String userOldPassword, String userNewPassword) {
        this.userId = userId;
        this.userOldPassword = userOldPassword;
        this.userNewPassword = userNewPassword;
    }

    @Override
    public String toString() {
        return "UpdateUserPasswordVO{" +
                "userId=" + userId +
                ", userOldPassword='" + userOldPassword + '\'' +
                ", userNewPassword='" + userNewPassword + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserOldPassword() {
        return userOldPassword;
    }

    public void setUserOldPassword(String userOldPassword) {
        this.userOldPassword = userOldPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }
}
