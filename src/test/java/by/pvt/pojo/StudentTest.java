package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getInstance().getTestSession();
    }

    @Test
    public void createInstanse(){
        Student student = new Student();
        student.setName("Student");
        student.setSecondName("SecondName");
        student.setUniversity("BNTU");
        student.setFaculty("Energy");
        student.setCourseYear((short) 3);

        try {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            assertNotNull(student.getId());
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