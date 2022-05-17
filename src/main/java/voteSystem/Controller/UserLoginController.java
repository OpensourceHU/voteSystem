package voteSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import voteSystem.Service.ActivityService;
import voteSystem.Service.UserService;
import voteSystem.Util.DigestUtil;
import voteSystem.pojo.Admin_UserPojo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/21 0021 9:30
 */
@Controller()
public class UserLoginController {
    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;

    @Autowired
    Admin_UserPojo admin_userPojo;

    //这里的地址
    @RequestMapping("/checkAdmin")
    protected void checkAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("inputEmail");
        String password = req.getParameter("inputPassword");
        admin_userPojo.setEmail(email);
        String byMd5 = "";
        try {
            byMd5 = DigestUtil.EncoderByMd5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        admin_userPojo.setPassword(byMd5);
        boolean access = userService.checkAdmin(admin_userPojo);
        if (access) {
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("password", byMd5);
            req.getRequestDispatcher("static/myActivityOri.html").forward(req, resp);
        } else
            req.getRequestDispatcher("static/login.html").forward(req, resp);
    }

    @RequestMapping("/checkJudge")
    protected void checkJudge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String activityName = request.getParameter("activityName");
        String account = request.getParameter("Account");
        String judgePassword = request.getParameter("JudgePassword");
        System.out.println("前台传来的评委信息:" + "  activityName:" + activityName + "-Account:" + account + "-JudgePassword:" + judgePassword);
        request.getSession().setAttribute("activityName", activityName);
        boolean access = activityService.checkJudge(activityName, account, judgePassword);
        if (access)
            request.getRequestDispatcher("static/voteView.html").forward(request, response);
        else
            request.getRequestDispatcher("static/JudgeLogin.html").forward(request, response);
    }


}
