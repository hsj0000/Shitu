package cn.jsu.projectmanage.service.imp;

import cn.jsu.projectmanage.dao.projectManage.GroupDao;
import cn.jsu.projectmanage.dao.projectManage.TaskDao;
import cn.jsu.projectmanage.pojo.dto.Group;
import cn.jsu.projectmanage.pojo.dto.GroupUser;
import cn.jsu.projectmanage.pojo.dto.Project;
import cn.jsu.projectmanage.pojo.vo.*;
import cn.jsu.projectmanage.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    GroupDao groupDao;

    @Autowired
    TaskDao taskDao;

    /**
     * 先创建小组并将组员加入关联表，再在项目表中插入项目信息
     * @param project
     * @return
     */
    @Override
    @Transactional
    public CommonResult createProject(ProjectVo project) {
        //1为管理员Id
        Group group = new Group(0, null, project.getProject().getProjectName(), project.getProjectLeadId());
        groupDao.insertGroup(group);
        int groupId = group.getGroupId();
        //添加小组成员
        List<GroupUser> groupUsers = new ArrayList<>();
        for (Integer userId: project.getUserIds()) {
            groupUsers.add(new GroupUser(0, groupId, userId));
        }
        groupDao.insertGroupUsers(groupUsers);

        project.getProject().setProjectStatus(Project.ProjectStatus.START.getValue());
        project.getProject().setProjectGroup(groupId);
        groupDao.insertProject(project.getProject());
        return new CommonResult<>(200, "项目创建成功!", project.getProject().getProjectId());
    }

    @Override
    public CommonResult queryProjectAll(PageQueryVo pageQueryVo) {
        Integer count = groupDao.count3(pageQueryVo);
        List<Project> projects = groupDao.queryProjectAll(pageQueryVo);
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("count", count);
        map.put("projects",projects);
        return new CommonResult<>(200, "查询成功!", map);
    }


    @Override
    public CommonResult queryProjectById(int projectId) {
        Project project = groupDao.queryProjectById(projectId);
        return new CommonResult<>(200, "查询成功", project);
    }

    /**
     * 查询项目中符合查询类型和状态的任务
     * @param queryProjectTask
     * @return
     *
     */
    @Override
    public CommonResult queryProjectTaskNumber(QueryProjectTaskVo queryProjectTask) {
        //查询项目中叶任务的总数
        Integer taskNumber = taskDao.queryProjectTaskNumber(queryProjectTask.getProjectId()
        , queryProjectTask.getStatus(), queryProjectTask.getTaskType());
        return new CommonResult(200,"查询数量成功!",taskNumber);
    }

    @Override
    @Transactional
    public CommonResult createGroup(GroupVo group) {
        groupDao.insertGroup(group.getGroup());
        Integer groupId = group.getGroup().getGroupId();
        List<GroupUser> groupUsers = new ArrayList<>();
        for (Integer userId: group.getGroupUsers()) {
            groupUsers.add(new GroupUser(0, groupId, userId));
        }
        groupDao.insertGroupUsers(groupUsers);
        return new CommonResult<>(200, "小组创建成功!", groupId);
    }

    @Override
    public CommonResult addGroupUsers(int groupId, List<Integer> userIds) {
        Integer count = groupDao.queryCount(groupId, userIds);
        if(count > 0)//判断是否重复添加了用户
            return new CommonResult<>(200, "用户已存在，添加失败!");
        List<GroupUser> groupUsers = new ArrayList<>();
        for (Integer userId: userIds) {
            groupUsers.add(new GroupUser(0, groupId, userId));
        }
        groupDao.insertGroupUsers(groupUsers);
        return new CommonResult<>(200, "添加用户成功!");
    }

    @Override
    public CommonResult deleteGroupUsers(Integer groupId, List<Integer> userIds) {
        groupDao.deleteGroupUsers(groupId, userIds);
        return new CommonResult(200, "删除成功!");
    }

    @Override
    public CommonResult updateGroupName(int groupId, String groupName) {
        if(groupDao.updateGroupName(groupId,groupName) > 0)
        return new CommonResult<>(200, "修改成功!");
        else return new CommonResult<>(200, "修改失败!");
    }

    @Override
    public CommonResult queryGroupById(int groupId) {
        Group group = groupDao.queryGroupById(groupId);
        return new CommonResult<>(200, "查询成功!", group);
    }

    @Override
    public CommonResult queryGroupByGroup(int groupId) {
        List<Group> groups = groupDao.queryGroupByGroup(groupId);
        return new CommonResult<>(200, "查询成功!", groups);
    }

    @Override
    public CommonResult deleteGroup1(Integer groupId) {
        if(groupDao.deleteGroupByGroupId(groupId) > 0){
            groupDao.deleteGroupUserByGroupId(groupId);
            //删除子组
            Set<Integer> groupIds = groupDao.queryChildIdList(groupId);
            do{ groupDao.deleteGroupUserByGroupIds(groupIds);
                Set<Integer> a = new HashSet<>();
                for (Integer integer : groupIds) {
                    a.addAll(groupDao.queryChildIdList(integer));
                }groupIds.addAll(a);
            }while(groupDao.deleteGroupByGroupIds(groupIds) > 0);
            return new CommonResult<>(200, "删除成功!");
        }
        else return new CommonResult<>(444, "删除失败,小组不存在!");
    }

    @Override
    public CommonResult deleteGroup2(Integer groupId) {
        Integer account = groupDao.queryChildGroupAccount(groupId);
        if(account > 0)//判断该小组是否有子组
            return new CommonResult<>(444, "存在子组，删除失败!");
        if(groupDao.deleteGroupByGroupId(groupId) > 0){
            groupDao.deleteGroupUserByGroupId(groupId);
            return new CommonResult<>(200, "删除成功!");
        }
        else return new CommonResult<>(444, "删除失败!");
    }

    /**
     * @Author Aurora
     * @param projectId
     * @Describtion 删除项目
     *              1：在project表中删除项目信息
     *              2：删除项目的所有附属小组
     *              3：删除项目的所有附属任务
     * @return
     */
    @Override
    public CommonResult deleteProject(Integer projectId) {
        Integer groupId = groupDao.equryGroupIdByProjectId(projectId);
        if(groupDao.deleteProject(projectId) > 0){
            deleteGroup1(groupId);
            List<Integer> taskIds = groupDao.equryTasksByProjectId(projectId);
            System.out.println(taskIds);
            groupDao.deleteTaskByProjectId(projectId);
            groupDao.deleteTaskPoolTaskByGroupId(taskIds);
            return new CommonResult<>(200, "删除成功!");
        }
        return new CommonResult<>(444, "删除失败，项目不存在!");
    }

    @Override
    public  CommonResult updateProject(Project project){
        if(project.getProjectName() == null
        && project.getProjectDescribe() == null
        && project.getProjectEndTime() == null
        ){
            return new CommonResult<>(444, "修改失败,至少填写一项内容");
        }
        if(groupDao.updateProject(project) > 0){
            return new CommonResult<>(200, "修改成功!");
        }
        else{
            return new CommonResult<>(444, "修改失败!");
        }
    }

}
