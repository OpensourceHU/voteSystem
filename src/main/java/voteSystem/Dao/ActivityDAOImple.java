package voteSystem.Dao;


import voteSystem.Util.JedisPoolUtil;
import voteSystem.pojo.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import voteSystem.pojo.ActivityPojo;
import voteSystem.pojo.Judge;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/19 0019 17:20
 */
@Repository
public class ActivityDAOImple implements ActivityDAO {
    @Autowired
    JedisPoolUtil jedisPoolUtil;


    /**
     * 通过一个activityPojo创建一个活动
     *
     * @param activityPojo
     * @return
     */
    public boolean createActivity(ActivityPojo activityPojo) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        //获取连接  取得pojo各个字段
        Jedis jedis = jedisPool.getResource();
        String activityName = activityPojo.getActivityName();
        String desc = activityPojo.getDesc();
        ArrayList<Option> options = activityPojo.getOptions();
        ArrayList<Judge> judges = activityPojo.getJudges();

        //活动名 字段设置
        jedis.hset(activityName, "activityName", activityName);

        //活动介绍 字段设置
        jedis.hset(activityName, "description", desc);

        //活动选项 字段设置
        for (int i = 0; i < options.size(); i++) {
            String optionName = options.get(i).getOptionName();
            String count = options.get(i).getOptionCount();
            String valueOfCount = count.toString();
            jedis.hset(activityName, optionName, valueOfCount);
        }
        //活动评委 字段设置
        for (int i = 0; i < judges.size(); i++) {
            String judgeAccount = judges.get(i).getJudgeAccount();
            String judgePassword = judges.get(i).getJudgePassword();
            jedis.hset(activityName, judgeAccount, judgePassword);
        }

        boolean flag = jedis.hgetAll(activityName) != null;
        jedis.close();
        return flag;
    }

    /**
     * 通过活动名称删除某项活动
     *
     * @param activityName
     * @return
     */
    public boolean delteActivity(String activityName) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPool.getResource();
        jedis.del(activityName);
        boolean flag = jedis.hgetAll(activityName).isEmpty();
        jedis.close();
        return flag;
    }

    /**
     * 输入活动名称  用Judge的pojo类添加评委
     *
     * @param activityName
     * @param judge
     * @return
     */
    public int addJudge(String activityName, Judge judge) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPool.getResource();
        String account = judge.getJudgeAccount();
        String password = judge.getJudgePassword();
        jedis.hset(activityName, account, password);
        int flag = jedis.hget(activityName, account) == password ? 1 : 0;
        jedis.close();
        return flag;
    }

    /**
     * 通过活动名与裁判名  删除该裁判
     * @param activityName
     * @param judgeAccount
     */
    public void deleteJudge(String activityName,String judgeAccount){
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPool.getResource();
        jedis.hdel(activityName,judgeAccount);
        jedis.close();
    }

    /**
     * 输入活动名称  返回活动信息
     * @param activityName
     * @return
     */
    public ActivityPojo getActivityInfo(String activityName) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

        Jedis jedis = jedisPool.getResource();
        //如果没查到  直接返回空
        if (jedis.hgetAll(activityName).isEmpty()) {
            jedis.close();
            return null;
        }

        //正则表达式匹配评委账号
        ActivityPojo activityPojo = new ActivityPojo();
        Pattern pattern = Pattern.compile("^[\\d]{6}$");
        Set<String> keySet = jedis.hkeys(activityName);
        for (String key : keySet
        ) {
            if (pattern.matcher(key).matches()) {
                //如果是评委账号 拿到其密码 加入judges中
                String judgePasswd = jedis.hget(activityName, key);
                activityPojo.judges.add(new Judge(key, judgePasswd));
                continue;
            }
            if (key.equals("description")) {
                activityPojo.setDesc(jedis.hget(activityName, key));
                continue;
            }
            if (key.equals("activityName")) {
                activityPojo.setActivityName(activityName);
                continue;
            }
            //如果都不是  则一定为选项 取出其值转为整型
            String count = jedis.hget(activityName, key);
            activityPojo.options.add(new Option(key, count));
        }
        jedis.close();
        return activityPojo;
    }


    /**
     * 增加某一选项的计数
     *
     * @param activityPojo
     * @param optionName
     */
    public void increOptionCount(ActivityPojo activityPojo, String optionName) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

        Jedis jedis = jedisPool.getResource();
        String activityName = activityPojo.getActivityName();
        jedis.hincrBy(activityName, optionName, 1);
        jedis.close();
    }

    /**
     * 依照裁判类和活动类  判断裁判能否登录当前活动
     *
     * @param judge
     * @param activityPojo
     * @return
     */
    public boolean checkJudge(Judge judge, ActivityPojo activityPojo) {
        ArrayList<Judge> judges = activityPojo.getJudges();
        String judgeAccount = judge.getJudgeAccount();
        String judgePassword = judge.getJudgePassword();

        boolean flag = false;
        for (Judge curJudge : judges
        ) {
            if (curJudge.getJudgeAccount().equals(judgeAccount) && curJudge.getJudgePassword().equals(judgePassword)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


}
