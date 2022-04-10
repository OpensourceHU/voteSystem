package voteSystem.Controller;

import voteSystem.Dao.UserDaoImple;
import voteSystem.Service.UserService;
import voteSystem.Util.DigestUtil;
import voteSystem.pojo.Admin_UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @class_name：RegistServlet
 * @comments: 注册请求处理
 * @param:验证码校验
 * @return: jsp跳转
 * @author:OpensourceHU
 * @createtime:2019年2月22日
 */
@Controller

public class RegistServlet extends HttpServlet {

    @Autowired
    private UserService userService;
    @Autowired
    private Admin_UserPojo userPojo;
    @Autowired
    private UserDaoImple userDaoImple;

    private static final long serialVersionUID = 1L;

    @Override
    @RequestMapping("/registered")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取session中的验证码
        String sessionCode = (String) req.getSession().getAttribute("code");
        System.out.println(sessionCode);

        if (sessionCode != null) {
            //  获取页面提交的验证码
            String inputCode = req.getParameter("code");
            System.out.println("页面提交的验证码:" + inputCode);
            if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
                //  验证成功，跳转成功页面
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                System.out.println("页面提交:" + email + "密码" + password);
                //注册
                userPojo.setEmail(email);
                String digestPassword = "";
                try {
                    digestPassword = DigestUtil.EncoderByMd5(password);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                userPojo.setPassword(digestPassword);
                userService.createUser(userPojo);
                //跳转
                req.setAttribute("email", email);
                req.getRequestDispatcher("/static/success.html").forward(req, resp);
            } else {
                //  验证失败
                req.getRequestDispatcher("/static/register.html").forward(req, resp);
            }
        } else {
            //  验证失败
            req.getRequestDispatcher("/static/register.html").forward(req, resp);
        }
        //  移除session中的验证码
        req.removeAttribute("code");
    }

    /**
     * 重置密码的servlet
     *
     * @param request
     * @param response
     */
    @RequestMapping("/resetPassword")
    protected void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Admin_UserPojo userPojo = userDaoImple.queryUserByEmail(email);
        if (userPojo != null) {
            userService.resetPassword(userPojo, password);
        } else {
            request.getRequestDispatcher("/static/register.html").forward(request, response);
        }
        request.getRequestDispatcher("/static/success.html").forward(request,response);
    }


}
