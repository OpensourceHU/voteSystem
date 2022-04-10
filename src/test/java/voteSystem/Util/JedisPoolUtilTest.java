package voteSystem.Util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/8/26 0026 22:55
 */
public class JedisPoolUtilTest {

    @Test
    public void init() {
    }

    @Test
    public void getJedisPoolInstance() {
        System.out.println(JedisPoolUtil.getJedisPoolInstance().getResource());
    }

    @Test
    public void release() {
    }
}