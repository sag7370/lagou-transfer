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

    private TransactionManager() {

    }

    private static TransactionManager transactionManager = new TransactionManager();

    public static TransactionManager getInstance() {
        return transactionManager;
    }

    /**
     * 开启事务控制
     *
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConnection().setAutoCommit(false);
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public void commit() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConnection().commit();
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConnection().rollback();
    }
}
