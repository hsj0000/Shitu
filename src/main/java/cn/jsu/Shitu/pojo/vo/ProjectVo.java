package cn.jsu.projectmanage.pojo.vo;

import cn.jsu.projectmanage.pojo.dto.Project;
import lombok.Data;

import java.util.List;

/**
 * @author: ahtonc
 * @date: 2022/1/5
 */
@Data
public class ProjectVo {
    private Project project;
    private Integer projectLeadId;
    private List<Integer> userIds;
}
