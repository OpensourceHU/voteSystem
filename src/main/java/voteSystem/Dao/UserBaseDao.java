package voteSystem.Dao;

import voteSystem.Util.MysqlUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author OpensourceHU
 * @version V1.0
 * @Description
 * @date 2020/7/20 0020 21:09
 */
@Repository
public class UserBaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 增删改操作  返回值代表影响的条数(失败返回-1)
     *
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object... args) {
        Connection connection = MysqlUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /***
     * 查询返回一个Java bean的SQL语句
     * @param sql
     * @param type 该值为返回的类型
     * @param args
     * @param <T>
     * @return
     */
    public <T> T queryForOne(String sql, Class<T> type, Object... args) {
        Connection connection = MysqlUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
