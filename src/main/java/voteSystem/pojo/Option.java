package voteSystem.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/19 0019 17:12
 */
@Component
public class Option {
    String optionName;
    String optionCount;


    @Override
    public String toString() {
        return "Option{" +
                "optionName='" + optionName + '\'' +
                ", optionCount='" + optionCount + '\'' +
                '}';
    }

    public Option() {
    }

    public Option(String optionName, String optionCount) {
        this.optionName = optionName;
        this.optionCount = optionCount;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(String optionCount) {
        this.optionCount = optionCount;
    }
}
