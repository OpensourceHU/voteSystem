package voteSystem.pojo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description 投票活动类pojo
 * @date 2020/7/19 0019 16:37
 */
@ComponentScan
@Component
public class ActivityPojo {

    String activityName;

    String desc;

    public ArrayList<Judge> judges = new ArrayList<Judge>();

    public ArrayList<Option> options = new ArrayList<Option>();


    public ActivityPojo() {
    }

    @Override
    public String toString() {
        return "ActivityPojo{" +
                "activityName='" + activityName + '\'' +
                ", desc='" + desc + '\'' +
                ", judges=" + judges +
                ", options=" + options +
                '}';
    }

    public ActivityPojo(String activityName, String desc, ArrayList<Judge> judges, ArrayList<Option> options) {
        this.activityName = activityName;
        this.desc = desc;
        this.judges = judges;
        this.options = options;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Judge> getJudges() {
        return judges;
    }

    public void setJudges(ArrayList<Judge> judges) {
        this.judges = judges;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }
}
