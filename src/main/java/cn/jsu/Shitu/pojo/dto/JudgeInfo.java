package cn.jsu.projectmanage.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Mo
 * @createTime 2022/1/4 21:35
 * @descripiton
 */

@Data
public class JudgeInfo {
    private Integer judgeId;
    private Integer judgeResult;
    private String judgeOpinion;
    private Integer taskId;
    private String reportDescribe;
    private String reportPosition;
    private Integer judgeUserId;
    //0为未完成 1为完成
    private Integer status;
    private Date createTime;
    private Date endTime;


    public JudgeInfo() {
    }

    public JudgeInfo(Integer judgeId, Integer judgeResult, String judgeOpinion, Integer taskId, String reportDescribe, String reportPosition, Integer judgeUserId, Integer status, Date createTime, Date endTime) {
        this.judgeId = judgeId;
        this.judgeResult = judgeResult;
        this.judgeOpinion = judgeOpinion;
        this.taskId = taskId;
        this.reportDescribe = reportDescribe;
        this.reportPosition = reportPosition;
        this.judgeUserId = judgeUserId;
        this.status = status;
        this.createTime = createTime;
        this.endTime = endTime;
    }

}
