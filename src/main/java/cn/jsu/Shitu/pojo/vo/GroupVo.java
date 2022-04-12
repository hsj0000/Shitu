package cn.jsu.projectmanage.pojo.vo;

import cn.jsu.projectmanage.pojo.dto.Group;
import lombok.Data;

import java.util.List;

/**
 * @author: ahtonc
 * @date: 2022/1/5
 * @description:
 */
@Data
public class GroupVo {
    private Group group;
    private List<Integer> groupUsers;
}
