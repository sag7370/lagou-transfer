package com.lagou.edu.utils;

/*
 一些声明信息
 Description: <br/>
 date: 2023/8/19 21:30<br/>
 @author SAg <br/>
 version 1.0
 since JDK 1.8
*/

import java.sql.SQLException;

/**
 * ClassName: TransactionManager <br/>
 * Description: 负责手动事务的开启，提交，回滚 <br/>
 * date: 2023/8/19 21:30<br/>
 *
 * @author SAg <br/>
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }


    /**
     * 开启事务控制
     *
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConnection().setAutoCommit(false);
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConnection().commit();
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConnection().rollback();
    }
}
