package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BranchTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getInstance().getTestSession();
    }

    @Test
    public void createIstance(){
        Branch branch = new Branch();
        branch.setBranchDirector("BranchDirector");
        branch.setBranchName("BranchName");
        branch.setCompanyName("OOO Vitebsk");
        branch.setLegalAddress(new Address("Minsk","Lenina","21",1));

        try {
            session.beginTransaction();
            session.saveOrUpdate(branch);
            session.getTransaction().commit();
            assertNotNull(branch.getId());
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