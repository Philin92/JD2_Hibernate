package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getInstance().getTestSession();
    }

    @Test
    public void createInstance(){

        User user = new User();

        user.setFirstName("User");
        user.setLastName("LastName");
        user.setAge(35);

        UserDetails userDetails = new UserDetails();

        userDetails.setEmail("example@gmail.com");
        userDetails.setNickName("philin");
        userDetails.setPassword("123456");
        userDetails.setUser(user);

        user.setUserDetails(userDetails);

        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.saveOrUpdate(userDetails);
            session.getTransaction().commit();

            assertTrue(user.getId()>0);
            assertTrue(userDetails.getId()>0);
            assertEquals(user.getId(),userDetails.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @After
    public void tearDown() throws Exception {
        if(session!=null && session.isOpen()){
            session.close();
            session = null;
        }
    }
}