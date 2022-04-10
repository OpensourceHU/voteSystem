package voteSystem.pojo;

import org.springframework.stereotype.Component;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/31 0031 20:01
 */
@Component
public class NameAndDesc {
    String name;
    String desc;

    @Override
    public String toString() {
        return "NameAndDesc{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
