package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class EmployeeTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getInstance().getTestSession();
    }

    @Test
    public void createInstance(){
        Employee employee1 = new Employee();
        employee1.setFirstName("Victor");
        employee1.setLastName("Victorov");
        employee1.setCellPhone("11213312");

        Employee employee2 = new Employee();
        employee2.setFirstName("Victo2");
        employee2.setLastName("Victoro2");
        employee2.setCellPhone("11213312");

        Employee employee3 = new Employee();
        employee3.setFirstName("Victor3");
        employee3.setLastName("Victorov3");
        employee3.setCellPhone("1121322");

        Department department1 = new Department();
        department1.setDepartmentName("department1");
        department1.setEmployees(Set.of(employee1,employee2));
        employee1.setDepartment(department1);
        employee2.setDepartment(department1);

        Department department2 = new Department();
        department2.setDepartmentName("department2");
        department2.setEmployees(Set.of(employee3));
        employee3.setDepartment(department2);

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setAddress(new Address("Minsk","Lenina","1",101));
        employeeDetails.setPosition("employee");
        employeeDetails.setPrivateNr("el");
        employeeDetails.setEmployee(employee1);
        employee1.setEmployeeDetails(employeeDetails);

        EmployeeDetails employeeDetails2 = new EmployeeDetails();
        employeeDetails2.setAddress(new Address("Minsk","Lenina","2",102));
        employeeDetails2.setPosition("employee2");
        employeeDetails2.setPrivateNr("el2");
        employeeDetails2.setEmployee(employee2);
        employee2.setEmployeeDetails(employeeDetails2);

        EmployeeDetails employeeDetails3 = new EmployeeDetails();
        employeeDetails3.setAddress(new Address("Minsk","Lenina","3",103));
        employeeDetails3.setPosition("employee3");
        employeeDetails3.setPrivateNr("el3");
        employeeDetails3.setEmployee(employee3);
        employee3.setEmployeeDetails(employeeDetails3);

        try {
            session.beginTransaction();
            session.saveOrUpdate(employee1);
            session.saveOrUpdate(employee2);
            session.saveOrUpdate(employee3);
            session.getTransaction().commit();
            assertTrue(employee1.getId()>0);
            assertTrue(employee2.getId()>0);
            assertTrue(employee3.getId()>0);
        } catch (Exception e) {
            e.printStackTrace();
            if (session!=null) {
                session.getTransaction().rollback();
            }

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