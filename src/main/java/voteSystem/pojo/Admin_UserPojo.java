package voteSystem.pojo;

import org.springframework.stereotype.Component;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/20 0020 16:07
 */
@Component
public class Admin_UserPojo {
    String email;
    String password;
    String activity01;
    String activity02;
    String activity03;
    String activity04;
    String activity05;

    @Override
    public String toString() {
        return "Admin_UserPojo{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", activity01='" + activity01 + '\'' +
                ", activity02='" + activity02 + '\'' +
                ", activity03='" + activity03 + '\'' +
                ", activity04='" + activity04 + '\'' +
                ", activity05='" + activity05 + '\'' +
                '}';
    }

    public Admin_UserPojo() {
    }

    public Admin_UserPojo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivity01() {
        return activity01;
    }

    public void setActivity01(String activity01) {
        this.activity01 = activity01;
    }

    public String getActivity02() {
        return activity02;
    }

    public void setActivity02(String activity02) {
        this.activity02 = activity02;
    }

    public String getActivity03() {
        return activity03;
    }

    public void setActivity03(String activity03) {
        this.activity03 = activity03;
    }

    public String getActivity04() {
        return activity04;
    }

    public void setActivity04(String activity04) {
        this.activity04 = activity04;
    }

    public String getActivity05() {
        return activity05;
    }

    public void setActivity05(String activity05) {
        this.activity05 = activity05;
    }
}
