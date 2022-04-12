package cn.jsu.projectmanage.pojo.vo;


public class UserSignInfoVo {
    private Integer signId;
    private Integer userId;
    private Integer continueSign;
    private Integer signRecordId;
    private Integer signTimeId;
    private String year;//年份
    private String date;//日期
    private String absenceReason;//缺勤原因

    /**
     * 分页查询
     */
    private Integer limit;
    private Integer pageOffset;

    public UserSignInfoVo() {
    }

    public UserSignInfoVo(Integer signId, Integer userId, Integer continueSign, Integer signRecordId, Integer signTimeId, String year, String date, String absenceReason, Integer limit, Integer pageOffset) {
        this.signId = signId;
        this.userId = userId;
        this.continueSign = continueSign;
        this.signRecordId = signRecordId;
        this.signTimeId = signTimeId;
        this.year = year;
        this.date = date;
        this.absenceReason = absenceReason;
        this.limit = limit;
        this.pageOffset = pageOffset;
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

    public Integer getSignRecordId() {
        return signRecordId;
    }

    public void setSignRecordId(Integer signRecordId) {
        this.signRecordId = signRecordId;
    }

    public Integer getSignTimeId() {
        return signTimeId;
    }

    public void setSignTimeId(Integer signTimeId) {
        this.signTimeId = signTimeId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public String getAbsenceReason() {
        return absenceReason;
    }

    public void setAbsenceReason(String absenceReason) {
        this.absenceReason = absenceReason;
    }

    @Override
    public String toString() {
        return "UserSignInfoVo{" +
                "signId=" + signId +
                ", userId=" + userId +
                ", continueSign=" + continueSign +
                ", signRecordId=" + signRecordId +
                ", signTimeId=" + signTimeId +
                ", year='" + year + '\'' +
                ", date='" + date + '\'' +
                ", absenceReason='" + absenceReason + '\'' +
                ", limit=" + limit +
                ", pageOffset=" + pageOffset +
                '}';
    }
}
