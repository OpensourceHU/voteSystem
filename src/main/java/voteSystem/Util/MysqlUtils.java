package voteSystem.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description 数据库工具类
 * @date 2020/6/22 0022 13:26
 */
@Component
public class MysqlUtils {
    private static MysqlUtils mysqlUtils;
    @PostConstruct
    public void init(){
        mysqlUtils = this;
    }

    private static DataSource dataSource;

    static {//读取配置文件  从流中加载数据
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = MysqlUtils.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
