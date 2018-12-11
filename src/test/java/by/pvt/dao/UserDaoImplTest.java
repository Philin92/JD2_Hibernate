package by.pvt.dao;

import by.pvt.pojo.User;
import by.pvt.pojo.UserDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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

    @Test
    public void step2_findUser(){
       User user = userDao.find(1L);
       assertNotNull(user);
       assertNotNull(user.getUserDetails());
    }

    @After
    public void tearDown(){
        userDao = null;
        userDao.isTestInstance = false;
    }
}
