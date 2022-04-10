package voteSystem.Controller;

import com.alibaba.fastjson.JSON;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import voteSystem.Service.ActivityService;
import voteSystem.Service.UserService;
import voteSystem.pojo.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/27 0027 21:58
 */
@Controller
public class AdminUserServlet extends HttpServlet {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    ActivityPojo activityPojo;

    @Autowired
    Admin_UserPojo userPojo;

    @RequestMapping("/deleteActivityServlet")
    public String deleteActivityServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        String email = (String) req.getSession().getAttribute("email");
        String password = (String) req.getSession().getAttribute("password");
        String deleteActivityName = req.getParameter("deleteActivityName");
        boolean flag = activityService.deleteActivity(email, password, deleteActivityName);
        if (flag)
            return "myActivityOri";
        else {
            res.sendError(500, "删除活动失败");
            return "myActivityOri";
        }
    }


    @RequestMapping("/createActivityServlet")
    public String createActivityServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");
        //session中取得当前用户的信息
        String email = (String) req.getSession().getAttribute("email");
        String password = (String) req.getSession().getAttribute("password");
        userPojo.setPassword(password);
        userPojo.setEmail(email);
        //首先判断该用户是否还能再创建活动
        if (userService.isFull(email) == true)
            return "myActivityOri";

        //取得各数据段
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String[] options = req.getParameterValues("option");
        String[] accounts = req.getParameterValues("account");
        String[] passwords = req.getParameterValues("password");
        System.err.println("开始创建活动");


        //设置activityPojo的各个字段
        //名称描述字段
        activityPojo.setActivityName(name);
        activityPojo.setDesc(desc);
        //选项字段
        ArrayList<Option> optionArr = new ArrayList<Option>();
        for (String s : options
        ) {
            Option newOption = new Option();
            newOption.setOptionName(s);
            newOption.setOptionCount("0");
            optionArr.add(newOption);
        }
        activityPojo.setOptions(optionArr);
        //评委字段
        ArrayList<Judge> judgeArr = new ArrayList<Judge>();
        int i = 0;
        for (String curAccount : accounts
        ) {
            Judge newJudge = new Judge();
            newJudge.setJudgeAccount(curAccount);
            newJudge.setJudgePassword(passwords[i++]);
            judgeArr.add(newJudge);
        }
        activityPojo.setJudges(judgeArr);

        System.out.println(activityPojo);
        System.out.println(userPojo);

        //调用addActivity() 完成写入
        userService.addActivity(userPojo, activityPojo);
        return "myActivityOri";
    }

    @RequestMapping("/findNameandDescServlet")
    public String findNameandDesc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("收到请求");
        //解决乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String email = (String) request.getSession().getAttribute("email");
        //找到该账户下活动类集合
        Set<ActivityPojo> activityInfo = activityService.findActivityInfoByAdminEmail(email);

        //活动类中提取出想要的活动名和描述
        ArrayList<NameAndDesc> arr = new ArrayList<NameAndDesc>();
        for (ActivityPojo curActivity : activityInfo
        ) {
            if (curActivity != null) {
                //活动类判空   不为空则取活动名和描述
                NameAndDesc nameAndDesc = new NameAndDesc();
                nameAndDesc.setName(curActivity.getActivityName());
                nameAndDesc.setDesc(curActivity.getDesc());
                arr.add(nameAndDesc);
            }
        }

        //转成json字符串
        String s = JSON.toJSONString(arr);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.close();
        return "myActivityOri";
    }


    /**
     * 找到指定项目的所有信息并返回JSON字符串
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findActivityInfo4Admin")
    public void findActivityInfo4Admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String activityName = request.getParameter("activityName");
        String decodeName =  URLDecoder.decode(activityName,"utf-8");
        System.out.println(decodeName);
        String activityInfo = activityService.findActivityInfo(decodeName);
        System.out.println(activityInfo);
        response.getWriter().write(activityInfo);
        response.getWriter().close();
    }

    @RequestMapping("/deleteJudge")
    public String deleteJudge(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String judgeName = request.getParameter("judgeName");
        String activityName = request.getParameter("activityName");
        activityService.deleteJudge(activityName,judgeName);
        return "adminActivityView";
    }

}
