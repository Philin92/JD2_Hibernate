package by.pvt.dao;

import by.pvt.pojo.Address;
import by.pvt.pojo.Person;
import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class DaoImplTest {

    DaoImpl<Person> dao;

    @Before
    public void setUp() throws Exception {
        dao = new DaoImpl<>(Person.class);
        dao.isTestInstance = true;
    }

    @After
    public void tearDown() throws Exception {
        dao.isTestInstance = false;
        dao = null;
    }

    @Test
    public void saveOrUpdate() {

        assertNull(dao.saveOrUpdate(null));

        assertNotNull(dao.saveOrUpdate(new Person()));

        Person person = new Person();
        assertNull(person.getId());
        Person person2 = dao.saveOrUpdate(person);
        assertEquals(person, person2);
        assertNotNull(person.getId());

        person2.setSecondName("Petrova");
        Person person3 = dao.saveOrUpdate(person2);
        assertEquals("Petrova", person3.getSecondName());
    }

    @Test
    public void load() {
        try {
            dao.load(null);
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }

        try {
            dao.load("testID");
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalStateException.class);
        }

        Serializable id = dao.saveOrUpdate(new Person()).getId();
        assertNotNull(dao.load(id));
        assertNotNull(dao.load(id).getId());
    }

    @Test
    public void find() {
        assertNull(dao.find(null));
        assertNull(dao.find("testID"));
        Serializable id = dao.saveOrUpdate(new Person()).getId();
        assertNotNull(dao.find(id));
    }

    @Test
    public void updateName() {
        Person person = new Person();
        person.setName("Vasia");
        person.setSecondName("Ivanov");
        person = dao.saveOrUpdate(person);

        assertNotNull(person.getId());
        assertEquals(person.getName(),"Vasia");

        System.out.println("contains(person): " + HibernateUtil.getInstance().getTestSession().contains(person));

        dao.updateName(person.getId(),"Petia");
        assertEquals(person.getName(),"Petia");
    }

    @Test
    public void updateNameWithExceptionAndRollback(){
        Person person = new Person();
        person.setName("Vasia");
        person.setSecondName("Ivanov");
        person = dao.saveOrUpdate(person);
        assertNotNull(person.getId());
        assertEquals(person.getName(),"Vasia");

        dao.updateName(null,null);

        System.out.println("contains(person): " + HibernateUtil.getInstance().getTestSession().contains(person));

        person = dao.load(person.getId());
        dao.updateName(person.getId(),"Petia");
        assertEquals(person.getName(),"Petia");
    }

    @Test
    public void deleteById() {

        Person person = new Person();
        Serializable id = dao.saveOrUpdate(person).getId();
        assertNotNull(id);

        dao.deleteById(id);
        assertNull(dao.find(id));
        System.out.println(person);
    }

    @Test
    public void savePersonWithAdress(){
        Person person = new Person();
        person.setName("Marina");
        person.setSecondName("Ivanova");
        person.setAddress(new Address("Minsk","Stroitelei","24",5));

        person = dao.saveOrUpdate(person);
        assertNotNull(person.getId());
        assertNotNull(person.getAddress());
        assertEquals("Minsk",person.getAddress().getCity());
        System.out.println(person);
    }
}