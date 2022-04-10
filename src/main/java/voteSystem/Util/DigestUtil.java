package voteSystem.Util;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/8/21 0021 22:26
 */

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5摘要算法
 */
@Component
public class DigestUtil {
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串 获取字节流转化成32位utf-8字符
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    public boolean checkpassword(String newpasswd, String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return EncoderByMd5(newpasswd).equals(oldpasswd);
    }
}