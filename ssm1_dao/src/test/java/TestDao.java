import com.yty.ssm.dao.*;
import com.yty.ssm.domain.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDao {
    @Test
    public void run1() throws Exception {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        ProductDao product= session.getMapper(ProductDao.class);
        List<Product> all = product.findAll();
        for (Product p : all) {
            System.out.println(p);
        }
        session.close();
        is.close();
    }
    @Test
    public void run2() throws Exception {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        MemberDao memberDao= session.getMapper(MemberDao.class);
        Member member = memberDao.findByMemberId(1);
        session.close();
        is.close();
    }
    @Test
    public void run3() throws Exception {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        TravellerDao travellerDao= session.getMapper(TravellerDao.class);
        List<Traveller> id = travellerDao.findByOrdersId(1);
        session.close();
        is.close();
    }
    @Test
    public void run4() throws Exception {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        userDao.addRoleToUser(1,3);
        session.commit();
        session.close();
        is.close();
    }
    @Test
    public void run5() throws Exception {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        RoleDao roleDao = session.getMapper(RoleDao.class);
        Role role = roleDao.findById(1);

        session.close();
        is.close();
    }
}
