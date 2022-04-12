package cn.jsu.projectmanage.pojo.vo;

public class AbsenceVo {
    private Integer userId;
    private String userName;
    private String userAccount;
    private Integer days;

    public AbsenceVo() {
    }

    public AbsenceVo(Integer userId, String userName, String userAccount, Integer days) {
        this.userId = userId;
        this.userName = userName;
        this.userAccount = userAccount;
        this.days = days;
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

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "AbsenceVo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", days=" + days +
                '}';
    }
}
