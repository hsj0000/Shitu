package cn.jsu.projectmanage.pojo.vo;

public class EachDaySignSituationVo {
    private Integer userId;
    private String userName;
    private String userAccount;
    private String absenceReason;
    private String totalSignTime;

    public EachDaySignSituationVo() {
    }

    public EachDaySignSituationVo(Integer userId, String userName, String userAccount, String absenceReason, String totalSignTime) {
        this.userId = userId;
        this.userName = userName;
        this.userAccount = userAccount;
        this.absenceReason = absenceReason;
        this.totalSignTime = totalSignTime;
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

    public String getAbsenceReason() {
        return absenceReason;
    }

    public void setAbsenceReason(String absenceReason) {
        this.absenceReason = absenceReason;
    }

    public String getTotalSignTime() {
        return totalSignTime;
    }

    public void setTotalSignTime(String totalSignTime) {
        this.totalSignTime = totalSignTime;
    }

    @Override
    public String toString() {
        return "EachDaySignSituationVo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", absenceReason='" + absenceReason + '\'' +
                ", totalSignTime='" + totalSignTime + '\'' +
                '}';
    }
}
