package cn.jsu.projectmanage.pojo.dto;


public class SignInfo {
    private Integer signId;
    private Integer userId;
    private Integer continueSign;
    private String lastModifyTime;

    public SignInfo() {
    }

    public SignInfo(Integer signId, Integer userId, Integer continueSign, String lastModifyTime) {
        this.signId = signId;
        this.userId = userId;
        this.continueSign = continueSign;
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContinueSign() {
        return continueSign;
    }

    public void setContinueSign(Integer continueSign) {
        this.continueSign = continueSign;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return "SignInfo{" +
                "signId=" + signId +
                ", userId=" + userId +
                ", continueSign=" + continueSign +
                ", lastModifyTime='" + lastModifyTime + '\'' +
                '}';
    }
}
