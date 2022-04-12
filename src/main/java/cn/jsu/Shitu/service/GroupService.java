package cn.jsu.projectmanage.service;

import cn.jsu.projectmanage.pojo.dto.Project;
import cn.jsu.projectmanage.pojo.vo.*;

import java.util.List;

public interface GroupService {

    CommonResult createProject(ProjectVo project);

    CommonResult queryProjectAll(PageQueryVo pageQueryVo);

    CommonResult createGroup(GroupVo group);

    CommonResult addGroupUsers(int groupId, List<Integer> userIds);

    CommonResult updateProject(Project project);

    CommonResult deleteGroupUsers(Integer groupId, List<Integer> userIds);

    CommonResult updateGroupName(int groupId, String groupName);

    CommonResult queryProjectById(int projectId);

    CommonResult queryProjectTaskNumber(QueryProjectTaskVo queryProjectTask);

    CommonResult queryGroupById(int groupId);

    CommonResult queryGroupByGroup(int groupId);

    CommonResult deleteGroup1(Integer groupId);

    CommonResult deleteGroup2(Integer groupId);

    CommonResult deleteProject(Integer projectId);

}
