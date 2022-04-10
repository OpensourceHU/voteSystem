package voteSystem.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/23 0023 20:24
 */
@Component
public class RandomUtil {
    private static RandomUtil randomUtil;
    @PostConstruct
    public void init(){
        randomUtil = this;
    }

    //  生成6位数随机验证码

    public static String getRandom() {
        String[] letters = new String[]{
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
                "A", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + letters[(int) Math.floor(Math.random() * letters.length)];
        }
        return code;
    }
}
