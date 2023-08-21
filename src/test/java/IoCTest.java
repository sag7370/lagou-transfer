/*
 一些声明信息
 Description: <br/>
 date: 2023/8/20 18:47<br/>
 @author SAg <br/>
 version 1.0
 since JDK 1.8
*/

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.utils.ConnectionUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * ClassName: IoCTest <br/>
 * Description: Java环境下启动IoC容器<br/>
 * date: 2023/8/20 18:47<br/>
 *
 * @author SAg <br/>
 */
public class IoCTest {

    @Test
    public void testIoC() {
        // 1. classpath (SE应用下推荐使用)
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 2. fileSystem 不推荐使用
        // ApplicationContext applicationContext = new FileSystemXmlApplicationContext("文件系统的绝对路径");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao);
    }

    /**
     * 静态方法 Bean
     */
    @Test
    public void testStaticBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ConnectionUtils connectionUtils = (ConnectionUtils) applicationContext.getBean("connectionUtils");
        System.out.println(connectionUtils);
    }

    /**
     * 实例化 Bean
     */
    @Test
    public void testInstanceBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ConnectionUtils connectionUtils = (ConnectionUtils) applicationContext.getBean("connectionUtils");
        System.out.println(connectionUtils);
    }
}
