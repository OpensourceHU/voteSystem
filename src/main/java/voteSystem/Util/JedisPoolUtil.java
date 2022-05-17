package voteSystem.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description jedis连接池  单例模式
 * @date 2020/7/19 0019 15:53
 */
@Component
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;


    //静态初始化
    private static JedisPoolUtil jedisPoolUtil;
    @PostConstruct
    public void init(){
        jedisPoolUtil = this;
    }

    /**
     * 获取jedisPool实例  双判空单例模式
     * @return
     */
    public static JedisPool getJedisPoolInstance() {
        if (jedisPool == null) {
            synchronized (JedisPool.class) {
                if (jedisPool == null) {
                    JedisPoolConfig poolConfig =  new JedisPoolConfig();
                    //池子里有一千个  最大空闲32
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    //最长十秒钟的申请时间
                    poolConfig.setMaxWaitMillis(1000*10);
                    //获得的连接实例都是可用的
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig,"47.100.30.75",6379);
                    return jedisPool;
                }
            }
        }
        return jedisPool;
    }

    public static void release(Jedis jedis){
        jedis.close();
    }
}
