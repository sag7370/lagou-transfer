package com.lagou.edu.factory;

/*
 一些声明信息
 Description: <br/>
 date: 2023/8/20 20:32<br/>
 @author SAg <br/>
 version 1.0
 since JDK 1.8
*/

import com.lagou.edu.utils.ConnectionUtils;

/**
 * ClassName: CreateBeanFactory <br/>
 * Description: <br/>
 * date: 2023/8/20 20:32<br/>
 *
 * @author SAg <br/>
 */
public class CreateBeanFactory {

    public static ConnectionUtils getInstanceStatic() {
        return new ConnectionUtils();
    }

    public ConnectionUtils getInstance() {
        return new ConnectionUtils();
    }
}
