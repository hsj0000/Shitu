package cn.jsu.projectmanage.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Mo
 * @createTime 2022/1/4 21:38
 * @descripiton
 */
@Data
public class Project {
    private Integer projectId;
    private String projectName;
    private String projectDescribe;
    private Integer projectGroup;
    private Integer projectStatus;
    private Date projectCreateTime;
    private Date projectEndTime;

    public enum ProjectStatus {
        START(0),
        END(1);

        private final int value;
        ProjectStatus(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    public Project() {
    }

    public Project(Integer projectId, String projectName, String projectDescribe, Integer projectGroup, Integer projectStatus, Date projectCreateTime, Date projectEndTime) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescribe = projectDescribe;
        this.projectGroup = projectGroup;
        this.projectStatus = projectStatus;
        this.projectCreateTime = projectCreateTime;
        this.projectEndTime = projectEndTime;
    }
}
