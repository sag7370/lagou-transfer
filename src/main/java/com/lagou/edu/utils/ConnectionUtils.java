package com.lagou.edu.utils;

/*
 一些声明信息
 Description: <br/>
 date: 2023/8/19 21:05<br/>
 @author SAg <br/>
 version 1.0
 since JDK 1.8
*/



import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: ConnectionUtils <br/>
 * Description: <br/>
 * date: 2023/8/19 21:05<br/>
 *
 * @author SAg <br/>
 */
public class ConnectionUtils {

    private ConnectionUtils() {

    }

    private static ConnectionUtils connectionUtils = new ConnectionUtils();

    public static ConnectionUtils getInstance() {
        return connectionUtils;
    }

    /**
     * 存储当前线程的sql链接
     */
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前线程的Connection
     *
     * @return
     */
    public Connection getCurrentThreadConnection() throws SQLException {
        // 判断当前线程中是否已经绑定链接，如果没有绑定，需要从连接池中获取一个链接绑定到当前线程
        Connection connection = connectionThreadLocal.get();
        if ( connection == null) {
            // 从连接池拿连接并绑定到当前线程
            connection = DruidUtils.getInstance().getConnection();
            //绑定当前链接
            connectionThreadLocal.set(connection);
        }
        return connection;
    }
}
