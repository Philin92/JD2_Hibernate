package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class EmployerTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getInstance().getTestSession();
    }

    @Test
    public void createInstance(){
        Employer employer = new Employer();

        employer.setName("Ivan");
        employer.setCorporateForNumber("34123431425");

        try {
            session.beginTransaction();
            session.saveOrUpdate(employer);
            session.getTransaction().commit();

            assertNotNull(employer.getId());
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @After
    public void tearDown() throws Exception {
        if(session!=null&&session.isOpen()){
            session.close();
            session = null;
        }
    }
}