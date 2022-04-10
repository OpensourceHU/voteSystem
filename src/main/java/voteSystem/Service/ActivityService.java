package voteSystem.Service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import voteSystem.Dao.ActivityDAOImple;
import voteSystem.Dao.UserDaoImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import voteSystem.pojo.ActivityPojo;
import voteSystem.pojo.Admin_UserPojo;
import voteSystem.pojo.Judge;
import voteSystem.pojo.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/21 0021 11:32
 */
@Component
@Service("ActivityService")
public class ActivityService {
    @Autowired
    private Admin_UserPojo admin_userPojo;

    @Autowired
    private ActivityDAOImple activityDAOImple;
    @Autowired
    private UserDaoImple userDaoImple;

    @Autowired
    private Judge judge;

    public boolean checkJudge(String activityName, String account, String password) {
        judge.setJudgeAccount(account);
        judge.setJudgePassword(password);
        ActivityPojo activityPojo = activityDAOImple.getActivityInfo(activityName);
        return activityDAOImple.checkJudge(judge, activityPojo);
    }

    /**
     * 添加裁判
     *
     * @param activityPojo
     * @param judge
     */
    public void addJudge(ActivityPojo activityPojo, Judge judge) {
        activityDAOImple.addJudge(activityPojo.getActivityName(), judge);
    }


    public void deleteJudge(String activityName,String judgeName){
        activityDAOImple.deleteJudge(activityName,judgeName);
    }

    /**
     * 增加某一选项的计数
     *
     * @param activityPojo
     * @param optionName
     */
    public void optionIncre(ActivityPojo activityPojo, String optionName) {
        activityDAOImple.increOptionCount(activityPojo, optionName);
    }

    /**
     * 查看管理员账户下创建的活动
     *
     * @return 活动类的列表  注意判空
     * @Param: email
     */
    public Set<ActivityPojo> findActivityInfoByAdminEmail(String adminEmail) {
        //先用邮箱查帐户
        Admin_UserPojo admin_userPojo = userDaoImple.queryUserByEmail(adminEmail);
        System.out.println(admin_userPojo);
        Set<ActivityPojo> set = new HashSet<ActivityPojo>();
        //查账户下各个活动的活动名
        String activity01Name = admin_userPojo.getActivity01();
        String activity02Name = admin_userPojo.getActivity02();
        String activity03Name = admin_userPojo.getActivity03();
        String activity04Name = admin_userPojo.getActivity04();
        String activity05Name = admin_userPojo.getActivity05();
        //拿着活动名找活动类
        if (activity01Name != null) {
            set.add(activityDAOImple.getActivityInfo(activity01Name));
        }
        if (activity02Name != null) {
            set.add(activityDAOImple.getActivityInfo(activity02Name));
        }
        if (activity03Name != null) {
            set.add(activityDAOImple.getActivityInfo(activity03Name));
        }
        if (activity04Name != null) {
            set.add(activityDAOImple.getActivityInfo(activity04Name));
        }
        if (activity05Name != null) {
            set.add(activityDAOImple.getActivityInfo(activity05Name));
        }
        Object[] infomation = set.toArray();
        System.err.println("查到的活动信息");
        System.err.println(Arrays.deepToString(infomation));
        return set;
    }

    /**
     * 删除某项活动
     *
     * @param adminAccount
     * @param password
     * @param activityName
     * @return
     */
    public boolean deleteActivity(String adminAccount, String password, String activityName) {
        admin_userPojo.setEmail(adminAccount);
        admin_userPojo.setPassword(password);
        return (userDaoImple.deleteActivity(admin_userPojo, activityName) && activityDAOImple.delteActivity(activityName));
    }


    /**
     * 返回指定活动的选项信息
     *
     * @param activityName
     * @return
     */
    public String findOptionInfo(String activityName) {
        System.err.println("findOptionInfo Service");
        ActivityPojo activityInfo = activityDAOImple.getActivityInfo(activityName);
        ArrayList<Option> options = activityInfo.getOptions();
        String s = JSON.toJSONString(options);
        return s;
    }

    public String findActivityInfo(String activityName) {
        if (activityName != null){
            ActivityPojo info = activityDAOImple.getActivityInfo(activityName);
            String s = JSON.toJSONString(info);
            return s;
        }
        else return "";
    }


}
