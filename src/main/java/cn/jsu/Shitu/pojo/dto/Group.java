package cn.jsu.projectmanage.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mo
 * @createTime 2022/1/4 21:32
 * @descripiton
 */
@Data
@AllArgsConstructor
public class Group {
    private Integer groupId;
    private Integer groupParentId;
    private String groupName;
    private Integer groupLead;
}
