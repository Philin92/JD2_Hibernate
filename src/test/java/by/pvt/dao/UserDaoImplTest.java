package by.pvt.dao;

import by.pvt.pojo.User;
import by.pvt.pojo.UserDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

    @Test
    public void step3_updateUser(){

        User user = userDao.find(1L);
        user.setFirstName("Vitalik");
        user.getUserDetails().setEmail("newEmail@gmail.com");
        userDao.saveOrUpdate(user);

        User user1 = userDao.load(1L);
        assertEquals("Vitalik",user1.getFirstName());
        assertEquals("newEmail@gmail.com",user1.getUserDetails().getEmail());

    }

    @Test
    public void step4_deleteUser(){

        User user = userDao.load(1L);
        userDao.deleteById(user.getId());

        User user2 = userDao.find(1L);

        assertNull(user2);
    }

    @After
    public void tearDown(){
        userDao = null;
        userDao.isTestInstance = false;
    }
}
