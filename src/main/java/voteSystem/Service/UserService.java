package voteSystem.Service;

import org.springframework.stereotype.Service;
import voteSystem.Dao.ActivityDAOImple;
import voteSystem.Dao.UserDaoImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import voteSystem.Util.DigestUtil;
import voteSystem.pojo.ActivityPojo;
import voteSystem.pojo.Admin_UserPojo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/21 0021 11:04
 */
@ComponentScan
@Service("UserService")
public class UserService {
    @Autowired
    private ActivityDAOImple activityDAOImple;
    @Autowired
    private UserDaoImple userDaoImple;

    public boolean checkAdmin(Admin_UserPojo user) {
        return userDaoImple.check(user);
    }

    //创建活动
    public void addActivity(Admin_UserPojo user, ActivityPojo activityPojo) {
        String activityName = activityPojo.getActivityName();
        userDaoImple.addActivity(user, activityName);
        activityDAOImple.createActivity(activityPojo);
    }

    //更改用户的密码
    public void resetPassword(Admin_UserPojo user, String newPassword) {
        String byMd5="";
        try {
            byMd5 = DigestUtil.EncoderByMd5(newPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userDaoImple.updatePassword(user,byMd5);
    }

    //取消某活动
    public void deleteActivity(Admin_UserPojo user, String activityName) {
        userDaoImple.deleteActivity(user, activityName);
        activityDAOImple.delteActivity(activityName);
    }

    //创建用户
    public void createUser(Admin_UserPojo user) {
        if (user != null && user.getEmail() != null && user.getPassword() != null)
            userDaoImple.save(user);
    }

    //判断当前用户创建的活动是否达到上限
    public boolean isFull(String userEmail) {
        Admin_UserPojo userPojo = userDaoImple.queryUserByEmail(userEmail);

        //如果得到的所有活动字段都不为空  则该用户创建的活动已达上线
        return (userPojo.getActivity01() != null && userPojo.getActivity02() != null && userPojo.getActivity03() != null
                && userPojo.getActivity04() != null && userPojo.getActivity05() != null);
    }
}
