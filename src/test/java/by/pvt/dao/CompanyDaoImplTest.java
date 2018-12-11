package by.pvt.dao;

import by.pvt.pojo.Address;
import by.pvt.pojo.Company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyDaoImplTest {

    DaoImpl<Company> companyDao;

    @Before
    public void setUp(){
        companyDao = new DaoImpl<>(Company.class);
        companyDao.isTestInstance = true;
    }

    @Test
    public void saveOrUpdate(){
        Company company = new Company();
        company.setCompanyName("Romashka");
        company.setSiteUrl("www.romashka.com");
        company.setHomeAddress(new Address("Minsk","Krasivaia","20",25));
        company.setLegalAddress(new Address("Volgograd","Morskaia","44",12));

        Company company2 = companyDao.saveOrUpdate(company);
        assertNotNull(company2);
        assertEquals(company2,company);
        assertEquals(company2.getLegalAddress(),company.getLegalAddress());
    }

    @Test
    public void updateAddress(){
        Company company = companyDao.load(2L);
        //Проверять объкт на null, в company с индексом 2 объект HomeAddress = null, поэтому когда пытаемся изменить ему название города выдаёт ошибку
        /*company.getHomeAddress().setCity("Vitebsk");*/
        company.getLegalAddress().setCity("Vitebsk");
        company = companyDao.saveOrUpdate(company);
        /*assertEquals(company.getHomeAddress().getCity(),"Vitebsk");*/
        assertEquals(company.getLegalAddress().getCity(),"Vitebsk");
    }

    @Test
    public void delete(){
        companyDao.deleteById(companyDao.load(1L).getId());
        assertNull(companyDao.find(1L));
    }

    @After
    public void tearDown(){
        companyDao = null;
        companyDao.isTestInstance = false;
    }
}
