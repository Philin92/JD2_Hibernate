package by.pvt.pojo;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Person implements Serializable {

    private static final long serialVersionUID = 2069432879157801749L;

    private String id;
    private String name;
    private String secondName;
    private int age;
    private Date dateOfBirth;

    private Address address;

    private List<String> title;

    public Person() {
    }

    public Person(String id, String name, String secondName, int age, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(secondName)
                .append(age)
                .append(dateOfBirth)
                .append(address)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Person)){
            return false;
        }

        Person that = (Person)obj;

        return new EqualsBuilder()
                .append(this.id,that.id)
                .append(this.name,that.name)
                .append(this.secondName,that.secondName)
                .append(this.age,that.age)
                .append(this.dateOfBirth,that.dateOfBirth)
                .append(this.address,that.address)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id",id)
                .append("name",name)
                .append("secondName",secondName)
                .append("age",age)
                .append("date",dateOfBirth)
                .append("address",address)
                .toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }
}
