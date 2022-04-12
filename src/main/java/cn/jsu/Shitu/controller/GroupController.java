package cn.jsu.projectmanage.controller;

import cn.jsu.projectmanage.pojo.dto.Project;
import cn.jsu.projectmanage.pojo.vo.*;
import cn.jsu.projectmanage.service.GroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    /**
     * done
     * @param project
     * @return
     */
    @PostMapping("/createProject")
    @ApiOperation("创建项目")
    public CommonResult createProject(@RequestBody ProjectVo project) {
        return groupService.createProject(project);
    }

    /**
     * @Author Aurora
     * @param projectId
     * @return
     */
    @PostMapping("/deleteProject")
    @ApiOperation("删除项目")
    public CommonResult deleteProject(@RequestParam Integer projectId) {
        return groupService.deleteProject(projectId);
    }


    /**
     * @Author Aurora
     * @param project
     * @return
     */
    @PostMapping("/updateProject")
    @ApiOperation("修改项目信息")
    public CommonResult updateProject(@RequestBody Project project) {
        return groupService.updateProject(project);
    }

    /**
     * done
     * @param pageQueryVo
     * @return
     */
    @PostMapping("/queryProjectAll")
    @ApiOperation("查询所有项目")
    public CommonResult queryProjectAll(PageQueryVo pageQueryVo) {
        return groupService.queryProjectAll(pageQueryVo);
    }

    /**
     * done
     * @param projectId
     * @return
     */
    @PostMapping("/queryProjectById")
    @ApiOperation("通过ID查询项目信息")
    public CommonResult queryProjectById(@RequestParam("projectId") Integer projectId) {
        return groupService.queryProjectById(projectId);
    }

    /**
     * done
     * @param queryProjectTask
     * @return
     */
    @PostMapping("/queryProjectTaskNumber")
    @ApiOperation("查询项目任务的数量")
    public CommonResult queryProjectTaskNumber(@RequestBody QueryProjectTaskVo queryProjectTask) {
        return groupService.queryProjectTaskNumber(queryProjectTask);
    }

    //------------------------------------------------------------------------------------

    /**
     * done
     * @param group
     * @return
     */
    @PostMapping("/createGroup")
    @ApiOperation("创建小组")
    public CommonResult createGroup(@RequestBody GroupVo group) {
        return groupService.createGroup(group);
    }

    /**
     * @Author Aurora
     * @param groupId
     * @describtion 删除小组方案一：将小组及其附属的小组全部删除
     */
    @PostMapping("/deleteGroup1")
    @ApiOperation("删除小组")
    public CommonResult deleteGroup1(@RequestParam Integer groupId) {
        return groupService.deleteGroup1(groupId);
    }

    /**
     * @Author Aurora
     * @param groupId
     * @describtion 删除小组方案二：只能删除没有附属小组的小组删除
     */
    @PostMapping("/deleteGroup2")
    @ApiOperation("删除小组")
    public CommonResult deleteGroup2(@RequestParam Integer groupId) {
        return groupService.deleteGroup2(groupId);
    }

    /**
     * done
     * @param groupId
     * @param groupName
     * @return
     */
    @PostMapping("/updateGroupName")
    @ApiOperation("修改小组名称")
    public CommonResult updateGroupName(@RequestParam("groupId") Integer groupId, @RequestParam("groupName") String groupName) {
        return groupService.updateGroupName(groupId, groupName);
    }

    /**
     * done
     * @param groupId
     * @param userIds
     * @return
     */
    @PostMapping("/addGroupUser")
    @ApiOperation("添加小组成员")
    public CommonResult addGroupUsers(@RequestParam("groupId") Integer groupId, @RequestParam("userIds") List<Integer> userIds) {
        return groupService.addGroupUsers(groupId, userIds);
    }

    /**
     * 删除小组成员，已经有向下关联的组员不能删除
     * @param groupId
     * @param userIds
     * @return
     */
    @PostMapping("/deleteGroupUser")
    @ApiOperation("删除小组成员")
    public CommonResult deleteGroupUsers(@RequestParam("groupId") Integer groupId, @RequestParam("userIds") List<Integer> userIds) {
        return groupService.deleteGroupUsers(groupId, userIds);
    }

    /**
     * done
     * @param groupId
     * @return
     */
    @PostMapping("/queryGroupById")
    @ApiOperation("通过Id查询小组信息")
    public CommonResult queryGroupById(@RequestParam("groupId") Integer groupId) {
        return groupService.queryGroupById(groupId);
    }

    /**
     * @param groupId
     * @return
     */
    @PostMapping("/queryGroupByGroup")
    @ApiOperation("查询小组内的所有组")
    public CommonResult queryGroupByGroup(@RequestParam("groupId") Integer groupId) {
        return groupService.queryGroupByGroup(groupId);
    }
}
