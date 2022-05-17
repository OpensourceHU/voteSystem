package voteSystem.Controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import voteSystem.Dao.ActivityDAOImple;
import voteSystem.Service.ActivityService;
import voteSystem.pojo.ActivityPojo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/8/3 0003 9:34
 */
@Controller
@RequestMapping("/vote")
public class VoteServlet {
    @Autowired
    ActivityDAOImple activityDAOImple;

    @Autowired
    ActivityService activityService;

    @RequestMapping("/getOptionInfo")
    public void getOptionInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String activityName = (String) req.getSession().getAttribute("activityName");
        System.out.println("开始处理选项信息请求");
        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //找信息
        ActivityPojo activityInfo = activityDAOImple.getActivityInfo(activityName);
        String info = JSON.toJSONString(activityInfo);
        //转成json字符串
        System.err.println("返回的活动信息");
        System.err.println(info);
        PrintWriter writer = resp.getWriter();
        writer.write(info);
        writer.close();

    }

    @RequestMapping("/sendInfo")
    public String sendInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        String chosen = request.getParameter("optionsRadios");
        System.out.println(chosen);
        if (chosen != null){
            String activityName = (String) request.getSession().getAttribute("activityName");
            ActivityPojo activityInfo = activityDAOImple.getActivityInfo(activityName);
            activityDAOImple.increOptionCount(activityInfo, chosen);
        }
        return "JudgeActivityView";
    }

    @RequestMapping("/ShowInfo4Judge")
    public void ShowInfo4Judge(HttpServletResponse response,HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=UTF-8");//返回的是txt文本文件
        String activityName = (String) request.getSession().getAttribute("activityName");
        String info = activityService.findOptionInfo(activityName);
        response.getWriter().write(info);
        response.getWriter().close();
    }
}
