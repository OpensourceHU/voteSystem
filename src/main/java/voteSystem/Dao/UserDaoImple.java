package voteSystem.Dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import voteSystem.Util.DigestUtil;
import voteSystem.pojo.Admin_UserPojo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/20 0020 16:43
 */
@ComponentScan
@Repository
public class UserDaoImple implements UserDAO {
    @Autowired
    UserBaseDao userBaseDao;

    /**
     * 传入一个管理员信息  判断信息是否匹配
     *
     * @param user
     * @return true匹配 false不匹配
     */
    public boolean check(Admin_UserPojo user) {
        String email = user.getEmail();
        String password = user.getPassword();

        String sql = "select `password` from admin_user where email=?";
        Admin_UserPojo admin_userPojo = userBaseDao.queryForOne(sql, Admin_UserPojo.class, email);
        return (admin_userPojo != null && admin_userPojo.getPassword().equals(password));
    }

    /**
     * 依照email查看该用户的全部信息
     *
     * @param email
     * @return 管理员类实例
     */
    public Admin_UserPojo queryUserByEmail(String email) {
        String sql = "select email,`password`,activity01,activity02,activity03,activity04,activity05 from admin_user where email=?";
        return userBaseDao.queryForOne(sql, Admin_UserPojo.class, email);
    }

    /**
     * 传入一个用户  保存其邮箱密码
     *
     * @param user
     * @return true更新成功false失败
     */
    public boolean save(Admin_UserPojo user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = "insert into admin_user (email,`password`) values (?,?)";
        return userBaseDao.update(sql, email, password) != -1;
    }

    public boolean updatePassword(String email, String newPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String sql = "update admin_user set password = ? where email = ?";
        String byMd5 = DigestUtil.EncoderByMd5(newPassword);
        return userBaseDao.update(sql, byMd5, email) != -1;
    }

    /**
     * 首先检查该用户信息是否匹配  若匹配则可更新
     *
     * @param user
     * @param newPassword
     * @return true更新成功false失败
     */
    public boolean updatePassword(Admin_UserPojo user, String newPassword) {
        if (check(user) == false) return false;
        String email = user.getEmail();
        String sql = "update admin_user set password = ? where email=?";
        return userBaseDao.update(sql, newPassword, email) != -1;
    }

    /**
     * @param user
     * @param activityName
     * @return 将活动名添加到指定用户的MySQL表下  返回添加是否成功
     */
    public boolean addActivity(Admin_UserPojo user, String activityName) {
        if (check(user) == false) return false;
        String email = user.getEmail();
        String sql = "";
        Admin_UserPojo userInfo = queryUserByEmail(email);
        if (userInfo.getActivity01() == null) {
            sql = "update admin_user set activity01 = ? where email=?";
            userBaseDao.update(sql, activityName, email);
        } else if (userInfo.getActivity02() == null) {
            sql = "update admin_user set activity02 = ? where email=?";
            userBaseDao.update(sql, activityName, email);
        } else if (userInfo.getActivity03() == null) {
            sql = "update admin_user set activity03 = ? where email=?";
            userBaseDao.update(sql, activityName, email);
        } else if (userInfo.getActivity04() == null) {
            sql = "update admin_user set activity04 = ? where email=?";
            userBaseDao.update(sql, activityName, email);
        } else if (userInfo.getActivity05() == null) {
            sql = "update admin_user set activity05 = ? where email=?";
            userBaseDao.update(sql, activityName, email);
        } else
            return false;
        return true;
    }


    /**
     * 输入用户信息和活动名  删除用户表中该活动
     *
     * @param user
     * @param activityName
     * @return 删除是否成功
     */
    public boolean deleteActivity(Admin_UserPojo user, String activityName) {
        if (check(user) == false) return false;
        String email = user.getEmail();
        String sql = "";
        Admin_UserPojo userInfo = queryUserByEmail(email);
        System.err.println("即将删除活动的活动名---" + activityName);
        System.err.println("该账户下目前的信息:");
        System.err.println(userInfo);
        if (userInfo.getActivity01() != null && userInfo.getActivity01().equals(activityName)) {
            sql = "update admin_user set activity01 = NULL where email=?";
            userBaseDao.update(sql, email);
        } else if (userInfo.getActivity02() != null && userInfo.getActivity02().equals(activityName)) {
            sql = "update admin_user set activity02 = NULL where email=?";
            userBaseDao.update(sql, email);
        } else if (userInfo.getActivity03() != null && userInfo.getActivity03().equals(activityName)) {
            sql = "update admin_user set activity03 = NULL where email=?";
            userBaseDao.update(sql, email);
        } else if (userInfo.getActivity04() != null && userInfo.getActivity04().equals(activityName)) {
            sql = "update admin_user set activity04 = NULL where email=?";
            userBaseDao.update(sql, email);
        } else if (userInfo.getActivity05() != null && userInfo.getActivity05().equals(activityName)) {
            sql = "update admin_user set activity05 = NULL where email=?";
            userBaseDao.update(sql, email);
        } else
            return false;
        return true;
    }


}
