package cn.jsu.projectmanage.dao.projectManage;


import cn.jsu.projectmanage.pojo.dto.UserInfo;
import cn.jsu.projectmanage.pojo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    //查询学号
    Integer queryAccount(@Param("userAccount")String userAccount);

    //通过学号和密码查询用户信息
    LoginResponseVO queryAccountInfo(LoginVO loginVO);

    //注册
    Integer register(AccountInfo accountInfo);

    //查询用户总数
    Integer count1(PageQueryVo queryUserVo);

    //查询小组用户总数
    Integer count2(PageQueryVo queryUserVo);

    //查询所有用户信息
    List<UserInfo> queryUserInfoAll(PageQueryVo queryUserVo);

    //更新用户密码
    Integer updateUserPassword(UpdateUserPasswordVO updateUserPasswordVO);

    //查询用户密码
    String queryOldUserPassword(UpdateUserPasswordVO updateUserPasswordVO);

    //查询小组用户信息
    List<UserInfo> queryUserInfoGroup(PageQueryVo queryUserVo);

    //通过Id查询用户信息
    UserInfo queryUserInfoById(@Param("userId")Integer userId);

}
