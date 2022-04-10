package voteSystem.Util;



import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * JavaMail发送邮件: java版本-与web无关
 * 前提是QQ邮箱里帐号设置要开启POP3/SMTP协议
 *
 *
 */
@Component
public class MailUtil {
    private static MailUtil mailUtil;
    @PostConstruct
    public void init(){
        mailUtil = this;
    }


    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        //vlswovrcnwahedia
        //原先:izwrippxjgeiifig
        ts.connect("smtp.qq.com", "1719423645", "vlswovrcnwahedia");
        // 创建邮件
        Message message = createSimpleMail(session);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */

    public static MimeMessage createSimpleMail(Session session) throws Exception {
        //  获取6为随机验证码
        String[] letters = new String[] {
                "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
                "A","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
                "0","1","2","3","4","5","6","7","8","9"};
        String stringBuilder ="";
        for (int i = 0; i < 6; i++) {
            stringBuilder = stringBuilder + letters[(int)Math.floor(Math.random()*letters.length)];
        }

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("1719423645@qq.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("1719423645@qq.com"));
        // 邮件的标题
        message.setSubject("欢迎注册voteSystem");
        // 邮件的文本内容
        message.setContent("欢迎您注册voteSystem,账号注册验证码为:"+stringBuilder+" (一分钟有效) ,请勿回复此邮箱", "text/html;charset=UTF-8");

        // 返回创建好的邮件对象
        return message;
    }
}
