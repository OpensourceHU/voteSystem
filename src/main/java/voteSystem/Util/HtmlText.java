package voteSystem.Util;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/23 0023 20:25
 */
@Component
public class HtmlText {
    private static HtmlText htmlText;
    @PostConstruct
    public void init(){
        htmlText = this;
    }



    public static String html(String code) {

        String html = "Email地址验证<br/>"+
                "这封邮件是由【voteSystem】发送的。<br/>"+
                "你收到这封邮件是【voteSystem】进行新用户注册或者用户修改Email使用这个地址。<br/>"+
                "账号激活声明<br/>"+
                "请将下面的验证码输入到提示框即可：<h3 style='color:red;'>" + code + "</h3><br/>";
        return html;
    }
}
