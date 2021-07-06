import com.yty.ssm.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testspring {
    @Test
    public void run1() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ProductService productService = context.getBean("productService",ProductService.class);
        productService.findAll();
    }
    @Test
    public void run2() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ProductService productService = context.getBean("productService",ProductService.class);
        productService.findAll();
    }
}
