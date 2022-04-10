package voteSystem.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/19 0019 17:07
 */
@Component
public class Judge{
    String judgeAccount;
    String judgePassword;

    @Override
    public String toString() {
        return "Judge{" +
                "judgeAccount='" + judgeAccount + '\'' +
                ", judgePassword='" + judgePassword + '\'' +
                '}';
    }

    public Judge() {
    }

    public Judge(String judgeAccount, String judgePassword) {
        this.judgeAccount = judgeAccount;
        this.judgePassword = judgePassword;
    }

    public String getJudgeAccount() {
        return judgeAccount;
    }

    public void setJudgeAccount(String judgeAccount) {
        this.judgeAccount = judgeAccount;
    }

    public String getJudgePassword() {
        return judgePassword;
    }

    public void setJudgePassword(String judgePassword) {
        this.judgePassword = judgePassword;
    }
}
