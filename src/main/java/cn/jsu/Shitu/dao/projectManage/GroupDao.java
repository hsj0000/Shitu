package cn.jsu.projectmanage.dao.projectManage;

import cn.jsu.projectmanage.pojo.dto.Group;
import cn.jsu.projectmanage.pojo.dto.GroupUser;
import cn.jsu.projectmanage.pojo.dto.Project;
import cn.jsu.projectmanage.pojo.vo.PageQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface GroupDao {

    //添加小组
    Integer insertGroup(Group group);

    //添加项目
    Integer insertProject(Project project);

    //小组关联小组成员
    Integer insertGroupUsers(@Param("groupUsers") List<GroupUser> groupUsers);

    //删除小组成员
    void deleteGroupUsers(Integer groupId, List<Integer> userIds);

    //查询指定用户在小组的数目
    Integer queryCount(Integer groupId, List<Integer> userIds);

    //通过项目Id查询项目信息
    Project queryProjectById(Integer projectId);

    //查询所有项目
    List<Project> queryProjectAll(PageQueryVo pageQueryVo);

    //查询项目个数
    Integer count3(PageQueryVo pageQueryVo);

    //通过groupId查询小组信息
    Group queryGroupById(Integer groupId);

    //修改小组名称
    Integer updateGroupName(Integer groupId,String groupName);

    //查询小组内的所有组
    List<Group> queryGroupByGroup(Integer groupId);

    //修改项目信息
    Integer updateProject(@Param("project") Project project);

    //查询子组个数
    Integer queryChildGroupAccount(Integer groupId);

    //通过小组Id删除小组
    Integer deleteGroupByGroupId(Integer groupId);

    //通过一堆小组Id删除小组
    Integer deleteGroupByGroupIds(@Param("groupIds")Set<Integer> groupIds);

    //通过小组Id删除小组成员
    Integer deleteGroupUserByGroupId(Integer groupId);

    //通过一堆小组Id删除小组成员
    Integer deleteGroupUserByGroupIds(@Param("groupIds")Set<Integer> groupIds);

    //查询子组Id集合
    Set<Integer> queryChildIdList(Integer groupId);

    //通过项目Id查询小组Id
    Integer equryGroupIdByProjectId(Integer projectId);

    //删除项目
    Integer deleteProject(Integer projectId);

    //通过项目Id查询任务池的任务
    List<Integer> equryTasksByProjectId(Integer projectId);

    //通过项目Id删除任务
    void deleteTaskByProjectId(Integer projectId);

    //通过项目Id删除任务池任务
    void deleteTaskPoolTaskByGroupId(@Param("taskIds")List<Integer> taskIds);

}
