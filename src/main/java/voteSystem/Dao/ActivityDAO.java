package voteSystem.Dao;

import voteSystem.pojo.ActivityPojo;
import voteSystem.pojo.Judge;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/19 0019 16:59
 */
public interface ActivityDAO {
    //创建活动
    boolean createActivity(ActivityPojo activityPojo);

    //删除某活动
    boolean delteActivity(String activityName);

    //临时增加评委
    int addJudge(String activityName,Judge judge);

    //取得某活动的信息
    ActivityPojo getActivityInfo(String activityName);

    //检查评委是否属于本次活动
    boolean checkJudge(Judge judge,ActivityPojo activityPojo);

    //投票
    void increOptionCount(ActivityPojo activityPojo, String optionName);

}
