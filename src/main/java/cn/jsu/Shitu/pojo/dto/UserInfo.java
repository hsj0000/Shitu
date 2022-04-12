package cn.jsu.projectmanage.pojo.dto;

/**
 * @author Mo
 * @createTime 2022/1/4 21:47
 * @descripiton
 */
public class UserInfo {
    private Integer userId;
    private String userName;
    private Integer userType;//1为管理员 2为普通成员
    private String userAccount;
    private String userPassword;
    private String userPhone;

    public UserInfo() {
    }

    public UserInfo(Integer userId, String userName, Integer userType, String userAccount, String userPassword, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
