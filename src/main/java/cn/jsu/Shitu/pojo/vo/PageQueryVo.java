package cn.jsu.projectmanage.pojo.vo;


public class PageQueryVo {
    private Integer groupId;
    private Integer limit;
    private Integer pageOffset;

    public PageQueryVo() {
    }

    public PageQueryVo(Integer groupId, Integer limit, Integer pageOffset) {
        this.groupId = groupId;
        this.limit = limit;
        this.pageOffset = pageOffset;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    @Override
    public String toString() {
        return "PageQueryVo{" +
                "groupId=" + groupId +
                ", limit=" + limit +
                ", pageOffset=" + pageOffset +
                '}';
    }
}
