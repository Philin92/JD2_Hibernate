package by.pvt.dao;

import by.pvt.pojo.User;
import by.pvt.pojo.UserDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserDaoImplTest {

    DaoImpl<User> userDao;

    @Before
    public void setUp(){
        userDao = new DaoImpl<>(User.class);
        userDao.isTestInstance = true;
    }

    @Test
    public void step1_createNewUser(){
        User user = new User();
        user.setFirstName("Alex");
        user.setLastName("Jackson");
        user.setAge(29);
        UserDetails userDetails = new UserDetails();
        userDetails.setPassword("password");
        userDetails.setNickName("wolf");
        userDetails.setEmail("wolf@gmail.com");
        userDetails.setUser(user);
        user.setUserDetails(userDetails);

        userDao.saveOrUpdate(user);

        assertNotNull(user.getId());
    }

    @After
    public void tearDown(){
        userDao = null;
        userDao.isTestInstance = false;
    }
}
