package cn.jsu.projectmanage.pojo.vo;

/**
 * @author Mo
 * @createTime 2022/1/4 23:10
 * @descripiton userType: 1 管理员
 *                        2 普通用户
 */
public class LoginResponseVO {
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userType;
    private String token;

    public LoginResponseVO() {
    }

    public LoginResponseVO(Integer userId, String userName, String userAccount, String userType, String token) {
        this.userId = userId;
        this.userName = userName;
        this.userAccount = userAccount;
        this.userType = userType;
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponseVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userType='" + userType + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
