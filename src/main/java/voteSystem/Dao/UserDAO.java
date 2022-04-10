package voteSystem.Dao;

import voteSystem.pojo.Admin_UserPojo;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/20 0020 16:06
 */
public interface UserDAO {
    //输入用户密码  check是否有效
    boolean check(Admin_UserPojo user);

    //使用该用户的邮箱查询所有信息
    Admin_UserPojo queryUserByEmail(String email);

    //新用户保存进User表里
    boolean save(Admin_UserPojo user);

    //传入一个admin_UserPojo 判断是否在表中  若存在则可更新
    boolean updatePassword(Admin_UserPojo user, String newPassword);

    //添加一个活动的名称到相应的用户信息栏中
    boolean addActivity(Admin_UserPojo user, String activityName);
}
