package by.pvt.pojo;

import by.pvt.dao.DaoImpl;
import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class WorkerTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getInstance().getTestSession();
    }

    @Test
    public void createInstance(){
        Worker worker = new Worker();
        worker.setName("Worker");
        worker.setSecondName("SecondName");
        worker.setCompanyName("OOO Romashka");
        worker.setSalary(BigDecimal.valueOf(1200.00));

        try {
            session.beginTransaction();
            session.saveOrUpdate(worker);
            session.getTransaction().commit();

            assertNotNull(worker.getId());
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