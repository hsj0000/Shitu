package cn.jsu.projectmanage.pojo.vo;

public class QuantumVo {
    private String startTime;
    private String endTime;
    private long days;
    private Integer limit;
    private Integer pageOffset;

    public QuantumVo() {
    }

    public QuantumVo(String startTime, String endTime, long days, Integer limit, Integer pageOffset) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.limit = limit;
        this.pageOffset = pageOffset;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "QuantumVo{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", days=" + days +
                ", limit=" + limit +
                ", pageOffset=" + pageOffset +
                '}';
    }
}
