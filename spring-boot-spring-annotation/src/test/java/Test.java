import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.com.service.UserService;

public class Test {

    private ApplicationContext applicationContext;

    @Before
    public void befrom() {
        String xmlPath = "/applicationContext.xml";
        applicationContext = new ClassPathXmlApplicationContext(xmlPath);
    }

    @org.junit.Test
    public void demo1() {
        UserService userService = (UserService) applicationContext
                .getBean("userService");
        userService.addUser();
        userService.updateUser();
    }
}
