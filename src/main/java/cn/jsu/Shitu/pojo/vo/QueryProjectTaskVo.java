package cn.jsu.projectmanage.pojo.vo;

import lombok.Data;

@Data
public class QueryProjectTaskVo {
    private Integer projectId;
    private Integer status;
    private Integer taskType;

    public QueryProjectTaskVo(Integer projectId, Integer status, Integer taskType) {
        this.projectId = projectId;
        this.status = status;
        this.taskType = taskType;
    }

    public QueryProjectTaskVo() {

    }
}
