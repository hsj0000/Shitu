package cn.jsu.projectmanage.pojo.vo;

/**
 * @author Mo
 * @createTime 2022/1/4 22:11
 * @descripiton
 */
public class LoginVO {
    private String userAccount;
    private String userPassword;
    private String code;
    private String data;

    public LoginVO() {
    }

    public LoginVO(String userAccount, String userPassword, String code, String data) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", code='" + code + '\'' +
                ", data='" + data + '\'' +
                '}';
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
