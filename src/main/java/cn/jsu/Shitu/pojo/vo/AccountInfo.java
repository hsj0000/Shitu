package cn.jsu.projectmanage.pojo.vo;

/**
 * @author Mo
 * @createTime 2022/1/4 23:43
 * @descripiton
 */
public class AccountInfo {
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userPhone;

    public AccountInfo() {
    }

    public AccountInfo(Integer userId, String userName, String userAccount, String userPassword, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
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
